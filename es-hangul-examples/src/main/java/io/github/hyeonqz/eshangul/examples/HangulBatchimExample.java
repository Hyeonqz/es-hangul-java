package io.github.hyeonqz.eshangul.examples;

import io.github.hyeonqz.eshangul.core.HangulBatchim;

/**
 * 한글 받침 처리 예시
 */
public class HangulBatchimExample {

    public static void main(String[] args) {
        // 받침 유무 확인
        boolean result1 = HangulBatchim.hasBatchim("값");
        System.out.println("'값'에 받침이 있는가? " + result1); // true

        boolean result2 = HangulBatchim.hasBatchim("토");
        System.out.println("'토'에 받침이 있는가? " + result2); // false

        // 받침 타입별 확인
        boolean result3 = HangulBatchim.hasBatchim("갑", HangulBatchim.BatchimType.SINGLE);
        System.out.println("'갑'에 단일 받침이 있는가? " + result3); // true

        boolean result4 = HangulBatchim.hasBatchim("값", HangulBatchim.BatchimType.DOUBLE);
        System.out.println("'값'에 쌍받침이 있는가? " + result4); // true
    }
}
