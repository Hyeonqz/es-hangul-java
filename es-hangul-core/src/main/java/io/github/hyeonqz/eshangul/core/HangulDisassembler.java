package io.github.hyeonqz.eshangul.core;

import io.github.hyeonqz.eshangul.characters.DisassembledCharacter;
import io.github.hyeonqz.eshangul.characters.HangulCharacter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static io.github.hyeonqz.eshangul.constants.HangulConstants.*;

/**
 * 한글 분해 관련 유틸리티 클래스
 */
public final class HangulDisassembler {

    private HangulDisassembler() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * 한글 문자열을 자모 단위로 그룹화하여 분해합니다.
     *
     * @param str 분해할 한글 문자열
     * @return 각 문자를 자모로 분해한 리스트의 리스트
     * @example
     * disassembleToGroups("값") // [["ㄱ", "ㅏ", "ㅂ", "ㅅ"]]
     * disassembleToGroups("토스") // [["ㅌ", "ㅗ"], ["ㅅ", "ㅡ"]]
     */
    public static List<List<String>> disassembleToGroups(String str) {
        List<List<String>> result = new ArrayList<>();

        for (char letter : str.toCharArray()) {
            String letterStr = String.valueOf(letter);
            DisassembledCharacter disassembledComplete = HangulCharacter.disassembleCompleteCharacter(letterStr);

            if (disassembledComplete != null) {
                List<String> group = new ArrayList<>();

                // 초성 추가 (초성은 단일 문자)
                String choseong = disassembledComplete.choseong();
                for (char c : choseong.toCharArray()) {
                    group.add(String.valueOf(c));
                }

                // 중성 추가 (복합 모음 분해 필요)
                String jungseong = disassembledComplete.jungseong();
                String disassembledVowel = DISASSEMBLED_VOWELS_BY_VOWEL.getOrDefault(jungseong, jungseong);
                for (char c : disassembledVowel.toCharArray()) {
                    group.add(String.valueOf(c));
                }

                // 종성 추가 (복합 자음 분해 필요)
                String jongseong = disassembledComplete.jongseong();
                if (!jongseong.isEmpty()) {
                    String disassembledConsonant = DISASSEMBLED_CONSONANTS_BY_CONSONANT.getOrDefault(jongseong, jongseong);
                    for (char c : disassembledConsonant.toCharArray()) {
                        group.add(String.valueOf(c));
                    }
                }

                result.add(group);
                continue;
            }

            // 자음 분해
            if (DISASSEMBLED_CONSONANTS_BY_CONSONANT.containsKey(letterStr)) {
                String disassembledConsonant = DISASSEMBLED_CONSONANTS_BY_CONSONANT.get(letterStr);
                result.add(Arrays.asList(disassembledConsonant.split("")));
                continue;
            }

            // 모음 분해
            if (DISASSEMBLED_VOWELS_BY_VOWEL.containsKey(letterStr)) {
                String disassembledVowel = DISASSEMBLED_VOWELS_BY_VOWEL.get(letterStr);
                result.add(Arrays.asList(disassembledVowel.split("")));
                continue;
            }

            // 한글이 아닌 경우 그대로 추가
            result.add(List.of(letterStr));
        }

        return result;
    }

    /**
     * 한글 문자열을 완전히 분해합니다.
     *
     * @param str 분해할 한글 문자열
     * @return 분해된 자모 문자열
     * @example
     * disassemble("값") // "ㄱㅏㅂㅅ"
     * disassemble("토스") // "ㅌㅗㅅㅡ"
     */
    public static String disassemble(String str) {
        return disassembleToGroups(str).stream()
                .flatMap(List::stream)
                .collect(Collectors.joining());
    }
}
