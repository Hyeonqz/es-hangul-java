package io.github.hyeonqz.eshangul.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HangulAssemblerTest {

    @Test
    @DisplayName("assemble: [ㅇ, ㅏ, ㅂ, ㅓ, ㅈ, ㅣ] -> 아버지")
    void testAssemble_아버지() {
        List<String> fragments = Arrays.asList("ㅇ", "ㅏ", "ㅂ", "ㅓ", "ㅈ", "ㅣ");
        String result = HangulAssembler.assemble(fragments);
        assertEquals("아버지", result);
    }

    @Test
    @DisplayName("assemble: [아버지가, ' ', 방ㅇ, ㅔ, ' ', 들ㅇ, ㅓ갑니다] -> 아버지가 방에 들어갑니다")
    void testAssemble_sentence() {
        List<String> fragments = Arrays.asList("아버지가", " ", "방ㅇ", "ㅔ ", "들ㅇ", "ㅓ갑니다");
        String result = HangulAssembler.assemble(fragments);
        assertEquals("아버지가 방에 들어갑니다", result);
    }

    @Test
    @DisplayName("assemble: [아버지가, ' ', 방에, ' ', 들어갑니다] -> 아버지가 방에 들어갑니다")
    void testAssemble_alreadyComplete() {
        List<String> fragments = Arrays.asList("아버지가", " ", "방에 ", "들어갑니다");
        String result = HangulAssembler.assemble(fragments);
        assertEquals("아버지가 방에 들어갑니다", result);
    }

    @Test
    @DisplayName("assemble: [ㄱ, ㅏ, ㅂ, ㅅ] -> 값")
    void testAssemble_값() {
        List<String> fragments = Arrays.asList("ㄱ", "ㅏ", "ㅂ", "ㅅ");
        String result = HangulAssembler.assemble(fragments);
        assertEquals("값", result);
    }

    @Test
    @DisplayName("assemble: [ㅌ, ㅗ, ㅅ, ㅡ] -> 토스")
    void testAssemble_토스() {
        List<String> fragments = Arrays.asList("ㅌ", "ㅗ", "ㅅ", "ㅡ");
        String result = HangulAssembler.assemble(fragments);
        assertEquals("토스", result);
    }

    @Test
    @DisplayName("assemble: 빈 리스트 -> 빈 문자열")
    void testAssemble_empty() {
        List<String> fragments = Arrays.asList();
        String result = HangulAssembler.assemble(fragments);
        assertEquals("", result);
    }

    @Test
    @DisplayName("assemble: [ㅗ, ㅏ] -> ㅘ (겹모음 조합)")
    void testAssemble_combineVowels() {
        List<String> fragments = Arrays.asList("ㅗ", "ㅏ");
        String result = HangulAssembler.assemble(fragments);
        assertEquals("ㅘ", result);
    }
}
