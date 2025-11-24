package io.github.hyeonqz.eshangul.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HangulChoseongTest {

    @Test
    @DisplayName("getChoseong: 사과 -> ㅅㄱ")
    void testGetChoseong_사과() {
        String result = HangulChoseong.getChoseong("사과");
        assertEquals("ㅅㄱ", result);
    }

    @Test
    @DisplayName("getChoseong: 띄어 쓰기 -> ㄸㅇ ㅆㄱ")
    void testGetChoseong_띄어쓰기() {
        String result = HangulChoseong.getChoseong("띄어 쓰기");
        assertEquals("ㄸㅇ ㅆㄱ", result);
    }

    @Test
    @DisplayName("getChoseong: 프론트엔드 -> ㅍㄹㅌㅇㄷ")
    void testGetChoseong_프론트엔드() {
        String result = HangulChoseong.getChoseong("프론트엔드");
        assertEquals("ㅍㄹㅌㅇㄷ", result);
    }

    @Test
    @DisplayName("getChoseong: 토스 -> ㅌㅅ")
    void testGetChoseong_토스() {
        String result = HangulChoseong.getChoseong("토스");
        assertEquals("ㅌㅅ", result);
    }

    @Test
    @DisplayName("getChoseong: 빈 문자열 -> 빈 문자열")
    void testGetChoseong_empty() {
        String result = HangulChoseong.getChoseong("");
        assertEquals("", result);
    }
}
