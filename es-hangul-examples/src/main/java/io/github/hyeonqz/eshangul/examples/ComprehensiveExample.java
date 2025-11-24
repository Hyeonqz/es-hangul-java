package io.github.hyeonqz.eshangul.examples;

import io.github.hyeonqz.eshangul.core.*;
import io.github.hyeonqz.eshangul.validator.HangulValidator;

import java.util.Arrays;

/**
 * ES-Hangul Java 종합 사용 예시
 */
public class ComprehensiveExample {

    public static void main(String[] args) {
        System.out.println("=== ES-Hangul Java 종합 예시 ===\n");

        // 1. 한글 조립
        System.out.println("1. 한글 조립");
        String assembled = HangulAssembler.assemble(Arrays.asList("ㅌ", "ㅗ", "ㅅ", "ㅡ"));
        System.out.println("   자모 조립: " + assembled);

        // 2. 한글 분해
        System.out.println("\n2. 한글 분해");
        String disassembled = HangulDisassembler.disassemble("토스");
        System.out.println("   문자 분해: " + disassembled);

        // 3. 조사 처리
        System.out.println("\n3. 조사 처리");
        String withJosa1 = HangulJosa.josa("토스", HangulJosa.JosaOption.이_가);
        String withJosa2 = HangulJosa.josa("개발자", HangulJosa.JosaOption.을_를);
        System.out.println("   토스 + 이/가: " + withJosa1);
        System.out.println("   개발자 + 을/를: " + withJosa2);

        // 4. 받침 처리
        System.out.println("\n4. 받침 처리");
        boolean hasBatchim = HangulBatchim.hasBatchim("값");
        boolean hasSingleBatchim = HangulBatchim.hasBatchim("갑", HangulBatchim.BatchimType.SINGLE);
        System.out.println("   '값'에 받침 있음: " + hasBatchim);
        System.out.println("   '갑'에 단일 받침 있음: " + hasSingleBatchim);

        // 5. 한글 검증
        System.out.println("\n5. 한글 검증");
        boolean canBeChoseong = HangulValidator.canBeChoseong("ㄱ");
        boolean canBeJungseong = HangulValidator.canBeJungseong("ㅏ");
        System.out.println("   'ㄱ'은 초성 가능: " + canBeChoseong);
        System.out.println("   'ㅏ'는 중성 가능: " + canBeJungseong);

        // 6. 실용적인 예시 - 동적 문장 생성
        System.out.println("\n6. 실용적인 예시");
        String[] names = {"김철수", "박영희", "이민수"};
        String[] actions = {"공부", "운동", "독서"};

        for (String name : names) {
            for (String action : actions) {
                String nameWithJosa = HangulJosa.josa(name, HangulJosa.JosaOption.은_는);
                String actionWithJosa = HangulJosa.josa(action, HangulJosa.JosaOption.을_를);
                String sentence = nameWithJosa + " " + actionWithJosa + " 합니다.";
                System.out.println("   " + sentence);
            }
        }
    }
}
