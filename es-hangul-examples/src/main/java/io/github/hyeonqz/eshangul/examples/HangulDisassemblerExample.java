package io.github.hyeonqz.eshangul.examples;

import io.github.hyeonqz.eshangul.core.HangulDisassembler;

import java.util.List;

/**
 * 한글 분해 예시
 */
public class HangulDisassemblerExample {

    public static void main(String[] args) {
        // 기본 분해
        String result1 = HangulDisassembler.disassemble("안녕하세요");
        System.out.println("기본 분해: " + result1); // "ㅇㅏㄴㄴㅕㅇㅎㅏㅅㅔㅇㅛ"

        // 그룹별 분해
        List<List<String>> result2 = HangulDisassembler.disassembleToGroups("값");
        System.out.println("그룹별 분해: " + result2); // [["ㄱ", "ㅏ", "ㅂ", "ㅅ"]]

        // 복합 문자 분해
        List<List<String>> result3 = HangulDisassembler.disassembleToGroups("토스");
        System.out.println("복합 문자 분해: " + result3); // [["ㅌ", "ㅗ"], ["ㅅ", "ㅡ"]]
    }
}
