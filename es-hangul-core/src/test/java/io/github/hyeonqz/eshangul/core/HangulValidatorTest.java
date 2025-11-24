package io.github.hyeonqz.eshangul.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.github.hyeonqz.eshangul.validator.HangulValidator.*;
import static org.junit.jupiter.api.Assertions.*;

class HangulValidatorTest {

    @Test
    @DisplayName("canBeChoseong: ㄱ -> true")
    void testCanBeChoseong_ㄱ() {
        assertTrue(canBeChoseong("ㄱ"));
    }

    @Test
    @DisplayName("canBeChoseong: ㅃ -> true")
    void testCanBeChoseong_ㅃ() {
        assertTrue(canBeChoseong("ㅃ"));
    }

    @Test
    @DisplayName("canBeChoseong: ㄱㅅ -> false")
    void testCanBeChoseong_ㄱㅅ() {
        assertFalse(canBeChoseong("ㄱㅅ"));
    }

    @Test
    @DisplayName("canBeChoseong: ㅏ -> false")
    void testCanBeChoseong_ㅏ() {
        assertFalse(canBeChoseong("ㅏ"));
    }

    @Test
    @DisplayName("canBeChoseong: 가 -> false")
    void testCanBeChoseong_가() {
        assertFalse(canBeChoseong("가"));
    }

    @Test
    @DisplayName("canBeJungseong: ㅏ -> true")
    void testCanBeJungseong_ㅏ() {
        assertTrue(canBeJungseong("ㅏ"));
    }

    @Test
    @DisplayName("canBeJungseong: ㅗㅏ -> true")
    void testCanBeJungseong_ㅗㅏ() {
        assertTrue(canBeJungseong("ㅗㅏ"));
    }

    @Test
    @DisplayName("canBeJungseong: ㅘ -> true")
    void testCanBeJungseong_ㅘ() {
        assertTrue(canBeJungseong("ㅘ"));
    }

    @Test
    @DisplayName("canBeJungseong: ㅏㅗ -> false")
    void testCanBeJungseong_ㅏㅗ() {
        assertFalse(canBeJungseong("ㅏㅗ"));
    }

    @Test
    @DisplayName("canBeJungseong: ㄱ -> false")
    void testCanBeJungseong_ㄱ() {
        assertFalse(canBeJungseong("ㄱ"));
    }

    @Test
    @DisplayName("canBeJungseong: 가 -> false")
    void testCanBeJungseong_가() {
        assertFalse(canBeJungseong("가"));
    }

    @Test
    @DisplayName("canBeJongseong: ㄱ -> true")
    void testCanBeJongseong_ㄱ() {
        assertTrue(canBeJongseong("ㄱ"));
    }

    @Test
    @DisplayName("canBeJongseong: ㄱㅅ -> true")
    void testCanBeJongseong_ㄱㅅ() {
        assertTrue(canBeJongseong("ㄱㅅ"));
    }

    @Test
    @DisplayName("canBeJongseong: '' (빈 문자열) -> true")
    void testCanBeJongseong_empty() {
        assertTrue(canBeJongseong(""));
    }

    @Test
    @DisplayName("canBeJongseong: ㅎㄹ -> false")
    void testCanBeJongseong_ㅎㄹ() {
        assertFalse(canBeJongseong("ㅎㄹ"));
    }

    @Test
    @DisplayName("canBeJongseong: 가 -> false")
    void testCanBeJongseong_가() {
        assertFalse(canBeJongseong("가"));
    }

    @Test
    @DisplayName("canBeJongseong: ㅏ -> false")
    void testCanBeJongseong_ㅏ() {
        assertFalse(canBeJongseong("ㅏ"));
    }
}
