package io.github.hyeonqz.eshangul.characters;

import static io.github.hyeonqz.eshangul.constants.HangulConstants.*;
import static io.github.hyeonqz.eshangul.validator.HangulValidator.*;

/**
 * 한글 문자의 분해와 조립을 담당하는 유틸리티 클래스
 */
public final class HangulCharacter {

    private HangulCharacter() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * 완전한 한글 문자를 초성, 중성, 종성으로 분리합니다.
     *
     * @param letter 분리하고자 하는 완전한 한글 문자
     * @return 분리된 초성, 중성, 종성 정보. 완성된 한글이 아니면 null
     * @example
     * disassembleCompleteCharacter("값") // DisassembledCharacter(choseong="ㄱ", jungseong="ㅏ", jongseong="ㅂㅅ")
     * disassembleCompleteCharacter("리") // DisassembledCharacter(choseong="ㄹ", jungseong="ㅣ", jongseong="")
     * disassembleCompleteCharacter("ㄱ") // null
     */
    public static DisassembledCharacter disassembleCompleteCharacter(String letter) {
        if (letter == null || letter.length() != 1) {
            return null;
        }

        int charCode = letter.charAt(0);

        boolean isCompleteHangul = COMPLETE_HANGUL_START_CHARCODE <= charCode &&
                charCode <= COMPLETE_HANGUL_END_CHARCODE;

        if (!isCompleteHangul) {
            return null;
        }

        int hangulCode = charCode - COMPLETE_HANGUL_START_CHARCODE;

        int jongseongIndex = hangulCode % NUMBER_OF_JONGSEONG;
        int jungseongIndex = ((hangulCode - jongseongIndex) / NUMBER_OF_JONGSEONG) % NUMBER_OF_JUNGSEONG;
        int choseongIndex = (hangulCode - jongseongIndex) / NUMBER_OF_JONGSEONG / NUMBER_OF_JUNGSEONG;

        return new DisassembledCharacter(
                CHOSEONGS.get(choseongIndex),
                JUNGSEONGS.get(jungseongIndex),
                JONGSEONGS.get(jongseongIndex)
        );
    }

    /**
     * 초성, 중성, 종성을 받아 하나의 한글 문자를 반환합니다.
     *
     * @param choseong 초성
     * @param jungseong 중성
     * @param jongseong 종성 (빈 문자열 가능)
     * @return 조립된 한글 문자
     * @throws IllegalArgumentException 유효하지 않은 한글 자모인 경우
     * @example
     * combineCharacter("ㄱ", "ㅏ", "ㅂㅅ") // "값"
     * combineCharacter("ㅌ", "ㅗ", "") // "토"
     * combineCharacter("ㅌ", "ㅗ") // "토"
     */
    public static String combineCharacter(String choseong, String jungseong, String jongseong) {
        if (jongseong == null) {
            jongseong = "";
        }

        if (!canBeChoseong(choseong) || !canBeJungseong(jungseong) || !canBeJongseong(jongseong)) {
            throw new IllegalArgumentException(
                    String.format("Invalid hangul characters: %s, %s, %s", choseong, jungseong, jongseong)
            );
        }

        int numOfJungseongs = JUNGSEONGS.size();
        int numOfJongseongs = JONGSEONGS.size();

        int choseongIndex = CHOSEONGS.indexOf(choseong);
        int jungseongIndex = JUNGSEONGS.indexOf(jungseong);
        int jongseongIndex = JONGSEONGS.indexOf(jongseong);

        int choseongOfTargetConsonant = choseongIndex * numOfJungseongs * numOfJongseongs;
        int choseongOfTargetVowel = jungseongIndex * numOfJongseongs;

        int unicode = COMPLETE_HANGUL_START_CHARCODE + choseongOfTargetConsonant +
                choseongOfTargetVowel + jongseongIndex;

        return String.valueOf((char) unicode);
    }

    /**
     * 초성과 중성만으로 한글 문자를 조립합니다.
     *
     * @param choseong 초성
     * @param jungseong 중성
     * @return 조립된 한글 문자
     */
    public static String combineCharacter(String choseong, String jungseong) {
        return combineCharacter(choseong, jungseong, "");
    }
}
