package io.github.hyeonqz.eshangul.validator;

import static io.github.hyeonqz.eshangul.constants.HangulConstants.*;

/**
 * 한글 자모의 유효성을 검증하는 유틸리티 클래스
 */
public final class HangulValidator {

    private HangulValidator() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * 인자로 받은 문자가 초성으로 위치할 수 있는 문자인지 검사합니다.
     *
     * @param character 검사할 문자
     * @return 초성이 될 수 있으면 true
     * @example
     * canBeChoseong("ㄱ") // true
     * canBeChoseong("ㅃ") // true
     * canBeChoseong("ㄱㅅ") // false
     * canBeChoseong("ㅏ") // false
     */
    public static boolean canBeChoseong(String character) {
        if (character == null || character.isEmpty()) {
            return false;
        }
        return CHOSEONGS.contains(character);
    }

    /**
     * 인자로 받은 문자가 중성으로 위치할 수 있는 문자인지 검사합니다.
     *
     * @param character 검사할 문자
     * @return 중성이 될 수 있으면 true
     * @example
     * canBeJungseong("ㅏ") // true
     * canBeJungseong("ㅗㅏ") // true
     * canBeJungseong("ㅘ") // true
     * canBeJungseong("ㄱ") // false
     */
    public static boolean canBeJungseong(String character) {
        if (character == null || character.isEmpty()) {
            return false;
        }

        // 단일 이중모음 문자인 경우 (ㅘ, ㅝ 등)
        if (DISASSEMBLED_VOWELS_BY_VOWEL.containsKey(character)) {
            return true;
        }

        // 분해된 이중모음 문자인 경우 (ㅗㅏ, ㅜㅓ 등)
        if (DISASSEMBLED_VOWELS_BY_VOWEL.containsValue(character)) {
            return true;
        }

        return JUNGSEONGS.contains(character);
    }

    /**
     * 인자로 받은 문자가 종성으로 위치할 수 있는 문자인지 검사합니다.
     *
     * @param character 검사할 문자
     * @return 종성이 될 수 있으면 true
     * @example
     * canBeJongseong("ㄱ") // true
     * canBeJongseong("ㄱㅅ") // true
     * canBeJongseong("ㅎㄹ") // false
     * canBeJongseong("ㅏ") // false
     */
    public static boolean canBeJongseong(String character) {
        if (character == null) {
            return false;
        }
        return JONGSEONGS.contains(character);
    }
}
