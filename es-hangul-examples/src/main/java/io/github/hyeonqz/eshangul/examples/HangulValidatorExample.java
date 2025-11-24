package io.github.hyeonqz.eshangul.examples;

import io.github.hyeonqz.eshangul.validator.HangulValidator;

/**
 * 한글 검증 예시
 */
public class HangulValidatorExample {

    public static void main(String[] args) {
        // 초성 가능 여부 확인
        boolean result1 = HangulValidator.canBeChoseong("ㄱ");
        System.out.println("'ㄱ'은 초성이 될 수 있는가? " + result1); // true

        boolean result2 = HangulValidator.canBeChoseong("ㅏ");
        System.out.println("'ㅏ'는 초성이 될 수 있는가? " + result2); // false

        // 중성 가능 여부 확인
        boolean result3 = HangulValidator.canBeJungseong("ㅏ");
        System.out.println("'ㅏ'는 중성이 될 수 있는가? " + result3); // true

        boolean result4 = HangulValidator.canBeJungseong("ㄱ");
        System.out.println("'ㄱ'은 중성이 될 수 있는가? " + result4); // false

        // 종성 가능 여부 확인
        boolean result5 = HangulValidator.canBeJongseong("ㄱ");
        System.out.println("'ㄱ'은 종성이 될 수 있는가? " + result5); // true

        boolean result6 = HangulValidator.canBeJongseong("ㅏ");
        System.out.println("'ㅏ'는 종성이 될 수 있는가? " + result6); // false
    }
}
