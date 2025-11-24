package io.github.hyeonqz.eshangul.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HangulDisassemblerTest {

    @Test
    @DisplayName("disassemble: 값 -> ㄱㅏㅂㅅ")
    void testDisassemble_값() {
        String result = HangulDisassembler.disassemble("값");
        assertEquals("ㄱㅏㅂㅅ", result);
    }

    @Test
    @DisplayName("disassemble: 토스 -> ㅌㅗㅅㅡ")
    void testDisassemble_토스() {
        String result = HangulDisassembler.disassemble("토스");
        assertEquals("ㅌㅗㅅㅡ", result);
    }

    @Test
    @DisplayName("disassemble: 한글 -> ㅎㅏㄴㄱㅡㄹ")
    void testDisassemble_한글() {
        String result = HangulDisassembler.disassemble("한글");
        assertEquals("ㅎㅏㄴㄱㅡㄹ", result);
    }

    @Test
    @DisplayName("disassemble: 사과 -> ㅅㅏㄱㅘ")
    void testDisassemble_사과() {
        String result = HangulDisassembler.disassemble("사과");
        assertEquals("ㅅㅏㄱㅘ", result);
    }

    @Test
    @DisplayName("disassemble: 빈 문자열 -> 빈 문자열")
    void testDisassemble_empty() {
        String result = HangulDisassembler.disassemble("");
        assertEquals("", result);
    }

    @Test
    @DisplayName("disassembleToGroups: 값 -> [[ㄱ, ㅏ, ㅂ, ㅅ]]")
    void testDisassembleToGroups_값() {
        List<List<String>> result = HangulDisassembler.disassembleToGroups("값");

        assertEquals(1, result.size());
        assertEquals(List.of("ㄱ", "ㅏ", "ㅂ", "ㅅ"), result.get(0));
    }

    @Test
    @DisplayName("disassembleToGroups: 토스 -> [[ㅌ, ㅗ], [ㅅ, ㅡ]]")
    void testDisassembleToGroups_토스() {
        List<List<String>> result = HangulDisassembler.disassembleToGroups("토스");

        assertEquals(2, result.size());
        assertEquals(List.of("ㅌ", "ㅗ"), result.get(0));
        assertEquals(List.of("ㅅ", "ㅡ"), result.get(1));
    }

    @Test
    @DisplayName("disassembleToGroups: 한글자모 분해")
    void testDisassembleToGroups_jamo() {
        List<List<String>> result = HangulDisassembler.disassembleToGroups("ㄱㅏ");

        assertEquals(2, result.size());
        assertEquals(List.of("ㄱ"), result.get(0));
        assertEquals(List.of("ㅏ"), result.get(1));
    }

    @Test
    @DisplayName("disassembleToGroups: 겹자음, 겹모음 분해")
    void testDisassembleToGroups_compound() {
        List<List<String>> result = HangulDisassembler.disassembleToGroups("ㄳㅘ");

        assertEquals(2, result.size());
        assertEquals(List.of("ㄱ", "ㅅ"), result.get(0));
        assertEquals(List.of("ㅗ", "ㅏ"), result.get(1));
    }
}
