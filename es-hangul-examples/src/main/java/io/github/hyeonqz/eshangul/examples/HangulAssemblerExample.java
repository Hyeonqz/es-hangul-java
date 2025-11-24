package io.github.hyeonqz.eshangul.examples;

import io.github.hyeonqz.eshangul.core.HangulAssembler;

import java.util.Arrays;

/**
 * 한글 조립 예시
 */
public class HangulAssemblerExample {

    public static void main(String[] args) {
        // 자모 조립
        String result1 = HangulAssembler.assemble(Arrays.asList("ㅇ", "ㅏ", "ㅂ", "ㅓ", "ㅈ", "ㅣ"));
        System.out.println("자모 조립: " + result1); // "아버지"

        // 문장 조립
        String result2 = HangulAssembler.assemble(Arrays.asList("아버지가", " ", "방ㅇ", "ㅔ ", "들ㅇ", "ㅓ갑니다"));
        System.out.println("문장 조립: " + result2); // "아버지가 방에 들어갑니다"

        // 복잡한 조립
        String result3 = HangulAssembler.assemble(Arrays.asList("ㅎ", "ㅏ", "ㄴ", "ㄱ", "ㅡ", "ㄹ"));
        System.out.println("복잡한 조립: " + result3); // "한글"
    }
}
