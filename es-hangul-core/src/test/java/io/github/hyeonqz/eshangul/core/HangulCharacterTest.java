package io.github.hyeonqz.eshangul.core;

import io.github.hyeonqz.eshangul.characters.DisassembledCharacter;
import io.github.hyeonqz.eshangul.characters.HangulCharacter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HangulCharacterTest {

    @Test
    @DisplayName("disassembleCompleteCharacter: 값 -> ㄱ, ㅏ, ㅂㅅ")
    void testDisassembleCompleteCharacter_값() {
        DisassembledCharacter result = HangulCharacter.disassembleCompleteCharacter("값");

        assertNotNull(result);
        assertEquals("ㄱ", result.choseong());
        assertEquals("ㅏ", result.jungseong());
        assertEquals("ㅂㅅ", result.jongseong());
    }

    @Test
    @DisplayName("disassembleCompleteCharacter: 리 -> ㄹ, ㅣ, ''")
    void testDisassembleCompleteCharacter_리() {
        DisassembledCharacter result = HangulCharacter.disassembleCompleteCharacter("리");

        assertNotNull(result);
        assertEquals("ㄹ", result.choseong());
        assertEquals("ㅣ", result.jungseong());
        assertEquals("", result.jongseong());
    }

    @Test
    @DisplayName("disassembleCompleteCharacter: 빚 -> ㅂ, ㅣ, ㅈ")
    void testDisassembleCompleteCharacter_빚() {
        DisassembledCharacter result = HangulCharacter.disassembleCompleteCharacter("빚");

        assertNotNull(result);
        assertEquals("ㅂ", result.choseong());
        assertEquals("ㅣ", result.jungseong());
        assertEquals("ㅈ", result.jongseong());
    }

    @Test
    @DisplayName("disassembleCompleteCharacter: 박 -> ㅂ, ㅏ, ㄱ")
    void testDisassembleCompleteCharacter_박() {
        DisassembledCharacter result = HangulCharacter.disassembleCompleteCharacter("박");

        assertNotNull(result);
        assertEquals("ㅂ", result.choseong());
        assertEquals("ㅏ", result.jungseong());
        assertEquals("ㄱ", result.jongseong());
    }

    @Test
    @DisplayName("disassembleCompleteCharacter: 완전한 한글이 아니면 null 반환")
    void testDisassembleCompleteCharacter_notCompleteHangul() {
        assertNull(HangulCharacter.disassembleCompleteCharacter("ㄱ"));
        assertNull(HangulCharacter.disassembleCompleteCharacter("ㅏ"));
        assertNull(HangulCharacter.disassembleCompleteCharacter("a"));
    }

    @Test
    @DisplayName("combineCharacter: ㄱ + ㅏ + ㅂㅅ -> 값")
    void testCombineCharacter_값() {
        String result = HangulCharacter.combineCharacter("ㄱ", "ㅏ", "ㅂㅅ");
        assertEquals("값", result);
    }

    @Test
    @DisplayName("combineCharacter: ㅌ + ㅗ + '' -> 토")
    void testCombineCharacter_토() {
        String result = HangulCharacter.combineCharacter("ㅌ", "ㅗ", "");
        assertEquals("토", result);
    }

    @Test
    @DisplayName("combineCharacter: ㅌ + ㅗ -> 토 (종성 생략)")
    void testCombineCharacter_토_noJongseong() {
        String result = HangulCharacter.combineCharacter("ㅌ", "ㅗ");
        assertEquals("토", result);
    }

    @Test
    @DisplayName("combineCharacter: 잘못된 자모는 예외 발생")
    void testCombineCharacter_invalidCharacters() {
        assertThrows(IllegalArgumentException.class, () ->
                HangulCharacter.combineCharacter("ㅏ", "ㅏ", "")
        );
    }
}
