package io.github.hyeonqz.eshangul.characters;

/**
 * 분해된 한글 문자를 나타내는 클래스
 */
public record DisassembledCharacter(
        String choseong,
        String jungseong,
        String jongseong) {

}
