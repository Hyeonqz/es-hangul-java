package io.github.hyeonqz.eshangul.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.github.hyeonqz.eshangul.core.HangulJosa.*;
import static org.junit.jupiter.api.Assertions.*;

class HangulJosaTest {

    @Test
    @DisplayName("josa: 사과 + 이/가 -> 사과가")
    void testJosa_사과가() {
        String result = josa("사과", JosaOption.이_가);
        assertEquals("사과가", result);
    }

    @Test
    @DisplayName("josa: 책 + 이/가 -> 책이")
    void testJosa_책이() {
        String result = josa("책", JosaOption.이_가);
        assertEquals("책이", result);
    }

    @Test
    @DisplayName("josa: 토스 + 은/는 -> 토스는")
    void testJosa_토스는() {
        String result = josa("토스", JosaOption.은_는);
        assertEquals("토스는", result);
    }

    @Test
    @DisplayName("josa: 집 + 을/를 -> 집을")
    void testJosa_집을() {
        String result = josa("집", JosaOption.을_를);
        assertEquals("집을", result);
    }

    @Test
    @DisplayName("josa: 학교 + 을/를 -> 학교를")
    void testJosa_학교를() {
        String result = josa("학교", JosaOption.을_를);
        assertEquals("학교를", result);
    }

    @Test
    @DisplayName("josa: 집 + 으로/로 -> 집으로")
    void testJosa_집으로() {
        String result = josa("집", JosaOption.으로_로);
        assertEquals("집으로", result);
    }

    @Test
    @DisplayName("josa: 학교 + 으로/로 -> 학교로")
    void testJosa_학교로() {
        String result = josa("학교", JosaOption.으로_로);
        assertEquals("학교로", result);
    }

    @Test
    @DisplayName("josa: 서울 + 으로/로 -> 서울로 (ㄹ 받침은 '로')")
    void testJosa_서울로() {
        String result = josa("서울", JosaOption.으로_로);
        assertEquals("서울로", result);
    }

    @Test
    @DisplayName("josa: 사과 + 와/과 -> 사과와")
    void testJosa_사과와() {
        String result = josa("사과", JosaOption.와_과);
        assertEquals("사과와", result);
    }

    @Test
    @DisplayName("josa: 책 + 와/과 -> 책과")
    void testJosa_책과() {
        String result = josa("책", JosaOption.와_과);
        assertEquals("책과", result);
    }

    @Test
    @DisplayName("josa: 영희 + 아/야 -> 영희야")
    void testJosa_영희야() {
        String result = josa("영희", JosaOption.아_야);
        assertEquals("영희야", result);
    }

    @Test
    @DisplayName("josa: 철수 + 아/야 -> 철수야")
    void testJosa_철수야() {
        String result = josa("철수", JosaOption.아_야);
        assertEquals("철수야", result);
    }

    @Test
    @DisplayName("josa: 빈 문자열 -> 빈 문자열")
    void testJosa_empty() {
        String result = josa("", JosaOption.이_가);
        assertEquals("", result);
    }

    @Test
    @DisplayName("josaPicker: 사과 -> 가")
    void testJosaPicker_사과() {
        String result = josaPicker("사과", JosaOption.이_가);
        assertEquals("가", result);
    }

    @Test
    @DisplayName("josaPicker: 책 -> 이")
    void testJosaPicker_책() {
        String result = josaPicker("책", JosaOption.이_가);
        assertEquals("이", result);
    }

    @Test
    @DisplayName("josaPicker: 서울 + 으로/로 -> 로 (ㄹ 받침)")
    void testJosaPicker_서울() {
        String result = josaPicker("서울", JosaOption.으로_로);
        assertEquals("로", result);
    }

    @Test
    @DisplayName("josa: 영문자 처리 - ABC + 이/가 -> ABC가")
    void testJosa_alphabet() {
        String result = josa("ABC", JosaOption.이_가);
        assertEquals("ABC가", result);
    }

    @Test
    @DisplayName("josa: 영문자 처리 - BTS + 은/는 -> BTS는")
    void testJosa_alphabetBTS() {
        String result = josa("BTS", JosaOption.은_는);
        assertEquals("BTS는", result);
    }
}
