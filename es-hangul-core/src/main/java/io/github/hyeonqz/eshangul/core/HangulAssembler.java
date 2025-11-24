package io.github.hyeonqz.eshangul.core;

import io.github.hyeonqz.eshangul.characters.HangulCharacter;

import java.util.List;

import static io.github.hyeonqz.eshangul.utils.HangulUtils.*;
import static io.github.hyeonqz.eshangul.validator.HangulValidator.*;

/**
 * 한글 조립 유틸리티 클래스
 */
public final class HangulAssembler {

    private HangulAssembler() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * 인자로 받은 배열에 담긴 한글 문장과 문자를 한글 규칙에 맞게 합성합니다.
     *
     * @param fragments 한글 문자와 문장을 담고 있는 배열
     * @return 합성된 한글 문자열
     * @example
     * assemble(Arrays.asList("아버지가", " ", "방ㅇ", "ㅔ ", "들ㅇ", "ㅓ갑니다")) // "아버지가 방에 들어갑니다"
     * assemble(Arrays.asList("ㅇ", "ㅏ", "ㅂ", "ㅓ", "ㅈ", "ㅣ")) // "아버지"
     */
    public static String assemble(List<String> fragments) {
        String joined = String.join("", fragments);
        String disassembled = HangulDisassembler.disassemble(joined);
        String[] chars = disassembled.split("");

        if (chars.length == 0) {
            return "";
        }

        String result = chars[0];
        for (int i = 1; i < chars.length; i++) {
            result = binaryAssemble(result, chars[i]);
        }

        return result;
    }

    /**
     * 한글 문장과 한글 문자 하나를 합성합니다.
     *
     * @param source 한글 문장
     * @param nextCharacter 한글 문자
     * @return 합성된 문자열
     */
    private static String binaryAssemble(String source, String nextCharacter) {
        if (source.isEmpty()) {
            return nextCharacter;
        }

        String[] sourceChars = source.split("");
        String lastCharacter = sourceChars[sourceChars.length - 1];
        String rest = source.substring(0, source.length() - 1);

        boolean needJoinString = isBlank(lastCharacter) || isBlank(nextCharacter);

        if (needJoinString) {
            return rest + lastCharacter + nextCharacter;
        } else {
            return rest + binaryAssembleCharacters(lastCharacter, nextCharacter);
        }
    }

    /**
     * 두 개의 한글 문자를 합성합니다.
     *
     * @param source 소스 문자
     * @param nextCharacter 다음 문자
     * @return 합성된 문자열
     */
    private static String binaryAssembleCharacters(String source, String nextCharacter) {
        assertCondition(
                isHangulCharacter(source) || isHangulAlphabet(source),
                String.format("Invalid source character: %s. Source must be one character.", source)
        );
        assertCondition(
                isHangulAlphabet(nextCharacter),
                String.format("Invalid next character: %s. Next character must be one of the choseong, jungseong, or jongseong.", nextCharacter)
        );

        List<List<String>> sourceJamosList = HangulDisassembler.disassembleToGroups(source);
        if (sourceJamosList.isEmpty()) {
            return source + nextCharacter;
        }

        List<String> sourceJamos = sourceJamosList.get(0);

        // 단일 자모인 경우
        if (sourceJamos.size() == 1) {
            return binaryAssembleAlphabets(sourceJamos.get(0), nextCharacter);
        }

        // 마지막 자모와 나머지 분리
        String lastJamo = sourceJamos.get(sourceJamos.size() - 1);
        List<String> restJamos = sourceJamos.subList(0, sourceJamos.size() - 1);
        String secondaryLastJamo = restJamos.size() > 0 ? restJamos.get(restJamos.size() - 1) : "";

        // 연음 법칙 처리 (자음 + 모음)
        boolean needLinking = canBeChoseong(lastJamo) && canBeJungseong(nextCharacter);
        if (needLinking) {
            String removedLast = HangulModifier.removeLastCharacter(source);
            return removedLast + HangulCharacter.combineCharacter(lastJamo, nextCharacter);
        }

        String choseong = restJamos.size() > 0 ? restJamos.get(0) : "";

        // 1. 중성 결합 (lastJamo + nextCharacter가 중성이 되는 경우)
        if (canBeJungseong(lastJamo + nextCharacter)) {
            if (restJamos.isEmpty()) {
                return HangulVowel.combineVowels(lastJamo, nextCharacter);
            }
            String jungseong = HangulVowel.combineVowels(lastJamo, nextCharacter);
            return HangulCharacter.combineCharacter(choseong, jungseong);
        }

        // 2. 이중 중성 + 종성 추가 (예: ㄱ + ㅗ + ㅏ -> 과, 과 + ㄴ -> 관)
        if (restJamos.size() >= 2) {
            if (canBeJungseong(secondaryLastJamo + lastJamo) && canBeJongseong(nextCharacter)) {
                String jungseong = secondaryLastJamo + lastJamo;
                return HangulCharacter.combineCharacter(choseong, jungseong, nextCharacter);
            }
        }

        // 3. 종성 추가 (예: 가 + ㅂ -> 갑)
        if (canBeJungseong(lastJamo) && canBeJongseong(nextCharacter)) {
            return HangulCharacter.combineCharacter(choseong, lastJamo, nextCharacter);
        }

        // 4. 쌍받침 처리 (예: 갑 + ㅅ -> 값)
        if (restJamos.size() >= 2) {
            String jungseong;
            // restJamos가 [ㄱ, ㅏ] 또는 [ㄱ, ㅗ, ㅏ] 형태
            if (restJamos.size() >= 3 && canBeJungseong(restJamos.get(1) + restJamos.get(2))) {
                jungseong = restJamos.get(1) + restJamos.get(2);
            } else {
                jungseong = restJamos.get(1);
            }

            String lastConsonant = lastJamo;
            if (HangulBatchim.hasBatchim(source, HangulBatchim.BatchimType.SINGLE) &&
                canBeJongseong(lastConsonant + nextCharacter)) {
                return HangulCharacter.combineCharacter(choseong, jungseong, lastConsonant + nextCharacter);
            }
        }

        return source + nextCharacter;
    }

    /**
     * 두 개의 한글 자모를 합칩니다.
     *
     * @param source 소스 자모
     * @param nextCharacter 다음 자모
     * @return 합성된 문자열
     */
    private static String binaryAssembleAlphabets(String source, String nextCharacter) {
        if (canBeJungseong(source + nextCharacter)) {
            return HangulVowel.combineVowels(source, nextCharacter);
        }

        boolean isConsonantSource = !canBeJungseong(source);
        if (isConsonantSource && canBeJungseong(nextCharacter)) {
            return HangulCharacter.combineCharacter(source, nextCharacter);
        }

        return source + nextCharacter;
    }
}
