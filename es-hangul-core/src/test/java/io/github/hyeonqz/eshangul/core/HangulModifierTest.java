package io.github.hyeonqz.eshangul.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HangulModifierTest {

    @Test
    @DisplayName("removeLastCharacter: 안녕하세요 값 -> 안녕하세요 갑")
    void testRemoveLastCharacter_값() {
        String result = HangulModifier.removeLastCharacter("안녕하세요 값");
        assertEquals("안녕하세요 갑", result);
    }

    @Test
    @DisplayName("removeLastCharacter: 프론트엔드 -> 프론트엔ㄷ")
    void testRemoveLastCharacter_프론트엔드() {
        String result = HangulModifier.removeLastCharacter("프론트엔드");
        assertEquals("프론트엔ㄷ", result);
    }

    @Test
    @DisplayName("removeLastCharacter: 일요일 -> 일요이")
    void testRemoveLastCharacter_일요일() {
        String result = HangulModifier.removeLastCharacter("일요일");
        assertEquals("일요이", result);
    }

    @Test
    @DisplayName("removeLastCharacter: 전화 -> 전호")
    void testRemoveLastCharacter_전화() {
        String result = HangulModifier.removeLastCharacter("전화");
        assertEquals("전호", result);
    }

    @Test
    @DisplayName("removeLastCharacter: 신세계 -> 신세ㄱ")
    void testRemoveLastCharacter_신세계() {
        String result = HangulModifier.removeLastCharacter("신세계");
        assertEquals("신세ㄱ", result);
    }

    @Test
    @DisplayName("removeLastCharacter: 빈 문자열 -> 빈 문자열")
    void testRemoveLastCharacter_empty() {
        String result = HangulModifier.removeLastCharacter("");
        assertEquals("", result);
    }

    @Test
    @DisplayName("removeLastCharacter: 한 글자에서 마지막 자모 제거 -> 초성만 남음")
    void testRemoveLastCharacter_singleChar() {
        String result = HangulModifier.removeLastCharacter("가");
        assertEquals("ㄱ", result);
    }
}
