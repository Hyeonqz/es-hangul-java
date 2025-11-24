package io.github.hyeonqz.eshangul.core;

import static io.github.hyeonqz.eshangul.constants.HangulConstants.DISASSEMBLED_VOWELS_BY_VOWEL;

/**
 * 한글 모음 처리 유틸리티 클래스
 */
public final class HangulVowel {

    private HangulVowel() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * 두 개의 모음을 받아 합성하여 겹모음을 생성합니다.
     * 올바른 한글 규칙으로 합성할 수 없는 모음들이라면 단순 결합합니다.
     *
     * @param vowel1 첫 번째 모음
     * @param vowel2 두 번째 모음
     * @return 합성된 모음
     * @example
     * combineVowels("ㅗ", "ㅏ") // "ㅘ"
     * combineVowels("ㅗ", "ㅐ") // "ㅙ"
     * combineVowels("ㅗ", "ㅛ") // "ㅗㅛ"
     */
    public static String combineVowels(String vowel1, String vowel2) {
        String combined = vowel1 + vowel2;

        return DISASSEMBLED_VOWELS_BY_VOWEL.entrySet().stream()
                .filter(entry -> entry.getValue().equals(combined))
                .map(entry -> entry.getKey())
                .findFirst()
                .orElse(combined);
    }
}
