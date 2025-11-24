package io.github.hyeonqz.eshangul.core;

import io.github.hyeonqz.eshangul.characters.HangulCharacter;

import java.util.List;

import static io.github.hyeonqz.eshangul.validator.HangulValidator.*;

/**
 * 한글 문자열 수정 유틸리티 클래스
 */
public final class HangulModifier {

    private HangulModifier() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * 한글 문자열에서 가장 마지막 문자 하나를 제거하여 반환합니다.
     *
     * @param words 한글 문자열
     * @return 마지막 문자가 제거된 문자열
     * @example
     * removeLastCharacter("안녕하세요 값") // "안녕하세요 갑"
     * removeLastCharacter("프론트엔드") // "프론트엔ㄷ"
     * removeLastCharacter("일요일") // "일요이"
     */
    public static String removeLastCharacter(String words) {
        if (words == null || words.isEmpty())
            return "";

        String lastCharacter = String.valueOf(words.charAt(words.length() - 1));
        List<List<String>> disassembleLastCharacter = HangulDisassembler.disassembleToGroups(lastCharacter);

        if (disassembleLastCharacter.isEmpty() || disassembleLastCharacter.get(0).isEmpty())
            return words.substring(0, words.length() - 1);

        List<String> lastCharacterJamos = disassembleLastCharacter.get(0);

        // 마지막 자모 제거
        if (lastCharacterJamos.size() <= 1)
            return words.substring(0, words.length() - 1);

        List<String> lastCharacterWithoutLastAlphabet = lastCharacterJamos.subList(0, lastCharacterJamos.size() - 1);

        String result;
        if (lastCharacterWithoutLastAlphabet.size() <= 3) {
            if (lastCharacterWithoutLastAlphabet.size() == 1) {
                result = lastCharacterWithoutLastAlphabet.get(0);
            } else if (lastCharacterWithoutLastAlphabet.size() == 2) {
                String first = lastCharacterWithoutLastAlphabet.get(0);
                String middle = lastCharacterWithoutLastAlphabet.get(1);
                result = HangulCharacter.combineCharacter(first, middle);
            } else { // size == 3
                String first = lastCharacterWithoutLastAlphabet.get(0);
                String middle = lastCharacterWithoutLastAlphabet.get(1);
                String last = lastCharacterWithoutLastAlphabet.get(2);

                if (canBeJungseong(last)) {
                    result = HangulCharacter.combineCharacter(first, middle + last);
                } else {
                    result = HangulCharacter.combineCharacter(first, middle, last);
                }
            }
        } else { // size == 4
            String first = lastCharacterWithoutLastAlphabet.get(0);
            String firstJungseong = lastCharacterWithoutLastAlphabet.get(1);
            String secondJungseong = lastCharacterWithoutLastAlphabet.get(2);
            String firstJongseong = lastCharacterWithoutLastAlphabet.get(3);

            result = HangulCharacter.combineCharacter(
                    first,
                    firstJungseong + secondJungseong,
                    firstJongseong
            );
        }

        return words.substring(0, words.length() - 1) + result;
    }
}
