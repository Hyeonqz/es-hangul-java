package io.github.hyeonqz.eshangul.constants;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 한글 처리에 필요한 상수들을 정의한 클래스
 */
public final class HangulConstants {

    private HangulConstants() {
        throw new UnsupportedOperationException("Utility class");
    }

    // 완성형 한글 범위
    public static final int COMPLETE_HANGUL_START_CHARCODE = '가';
    public static final int COMPLETE_HANGUL_END_CHARCODE = '힣';

    // 한글 자모 개수
    public static final int NUMBER_OF_JONGSEONG = 28;
    public static final int NUMBER_OF_JUNGSEONG = 21;

    /**
     * 초성으로 올 수 있는 한글 글자
     */
    public static final List<String> CHOSEONGS = List.of(
            "ㄱ", "ㄲ", "ㄴ", "ㄷ", "ㄸ", "ㄹ", "ㅁ", "ㅂ", "ㅃ",
            "ㅅ", "ㅆ", "ㅇ", "ㅈ", "ㅉ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ"
    );

    /**
     * 중성으로 올 수 있는 한글 글자
     */
    public static final List<String> JUNGSEONGS = List.of(
            "ㅏ", "ㅐ", "ㅑ", "ㅒ", "ㅓ", "ㅔ", "ㅕ", "ㅖ", "ㅗ", "ㅘ",
            "ㅙ", "ㅚ", "ㅛ", "ㅜ", "ㅝ", "ㅞ", "ㅟ", "ㅠ", "ㅡ", "ㅢ", "ㅣ"
    );

    /**
     * 종성으로 올 수 있는 한글 글자 (분해된 형태)
     */
    public static final List<String> JONGSEONGS = List.of(
            "", "ㄱ", "ㄲ", "ㄱㅅ", "ㄴ", "ㄴㅈ", "ㄴㅎ", "ㄷ", "ㄹ", "ㄹㄱ",
            "ㄹㅁ", "ㄹㅂ", "ㄹㅅ", "ㄹㅌ", "ㄹㅍ", "ㄹㅎ", "ㅁ", "ㅂ", "ㅂㅅ",
            "ㅅ", "ㅆ", "ㅇ", "ㅈ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ"
    );

    /**
     * 자음 분해 맵
     * 예: ㄳ -> ㄱㅅ
     */
    public static final Map<String, String> DISASSEMBLED_CONSONANTS_BY_CONSONANT;

    static {
        Map<String, String> map = new HashMap<>();
        map.put("", "");
        map.put("ㄱ", "ㄱ");
        map.put("ㄲ", "ㄲ");
        map.put("ㄳ", "ㄱㅅ");
        map.put("ㄴ", "ㄴ");
        map.put("ㄵ", "ㄴㅈ");
        map.put("ㄶ", "ㄴㅎ");
        map.put("ㄷ", "ㄷ");
        map.put("ㄸ", "ㄸ");
        map.put("ㄹ", "ㄹ");
        map.put("ㄺ", "ㄹㄱ");
        map.put("ㄻ", "ㄹㅁ");
        map.put("ㄼ", "ㄹㅂ");
        map.put("ㄽ", "ㄹㅅ");
        map.put("ㄾ", "ㄹㅌ");
        map.put("ㄿ", "ㄹㅍ");
        map.put("ㅀ", "ㄹㅎ");
        map.put("ㅁ", "ㅁ");
        map.put("ㅂ", "ㅂ");
        map.put("ㅃ", "ㅃ");
        map.put("ㅄ", "ㅂㅅ");
        map.put("ㅅ", "ㅅ");
        map.put("ㅆ", "ㅆ");
        map.put("ㅇ", "ㅇ");
        map.put("ㅈ", "ㅈ");
        map.put("ㅉ", "ㅉ");
        map.put("ㅊ", "ㅊ");
        map.put("ㅋ", "ㅋ");
        map.put("ㅌ", "ㅌ");
        map.put("ㅍ", "ㅍ");
        map.put("ㅎ", "ㅎ");
        DISASSEMBLED_CONSONANTS_BY_CONSONANT = Collections.unmodifiableMap(map);
    }

    /**
     * 모음 분해 맵
     * 예: ㅘ -> ㅗㅏ
     */
    public static final Map<String, String> DISASSEMBLED_VOWELS_BY_VOWEL;

    static {
        Map<String, String> map = new HashMap<>();
        map.put("ㅏ", "ㅏ");
        map.put("ㅐ", "ㅐ");
        map.put("ㅑ", "ㅑ");
        map.put("ㅒ", "ㅒ");
        map.put("ㅓ", "ㅓ");
        map.put("ㅔ", "ㅔ");
        map.put("ㅕ", "ㅕ");
        map.put("ㅖ", "ㅖ");
        map.put("ㅗ", "ㅗ");
        map.put("ㅘ", "ㅗㅏ");
        map.put("ㅙ", "ㅗㅐ");
        map.put("ㅚ", "ㅗㅣ");
        map.put("ㅛ", "ㅛ");
        map.put("ㅜ", "ㅜ");
        map.put("ㅝ", "ㅜㅓ");
        map.put("ㅞ", "ㅜㅔ");
        map.put("ㅟ", "ㅜㅣ");
        map.put("ㅠ", "ㅠ");
        map.put("ㅡ", "ㅡ");
        map.put("ㅢ", "ㅡㅣ");
        map.put("ㅣ", "ㅣ");
        DISASSEMBLED_VOWELS_BY_VOWEL = Collections.unmodifiableMap(map);
    }

    /**
     * 알파벳을 한글 발음으로 변환하는 맵
     */
    public static final Map<String, String> ALPHABET_TO_KOREAN;

    static {
        Map<String, String> map = new HashMap<>();
        map.put("A", "에이");
        map.put("B", "비");
        map.put("C", "씨");
        map.put("D", "디");
        map.put("E", "이");
        map.put("F", "에프");
        map.put("G", "지");
        map.put("H", "에이치");
        map.put("I", "아이");
        map.put("J", "제이");
        map.put("K", "케이");
        map.put("L", "엘");
        map.put("M", "엠");
        map.put("N", "엔");
        map.put("O", "오");
        map.put("P", "피");
        map.put("Q", "큐");
        map.put("R", "알");
        map.put("S", "에스");
        map.put("T", "티");
        map.put("U", "유");
        map.put("V", "브이");
        map.put("W", "더블유");
        map.put("X", "엑스");
        map.put("Y", "와이");
        map.put("Z", "지");
        ALPHABET_TO_KOREAN = Collections.unmodifiableMap(map);
    }

    /**
     * 한글 숫자 단위
     */
    public static final List<String> HANGUL_DIGITS = List.of(
            "", "만", "억", "조", "경", "해", "자", "양", "구", "간",
            "정", "재", "극", "항하사", "아승기", "나유타", "불가사의",
            "무량대수", "겁", "업"
    );

    public static final int HANGUL_DIGITS_MAX = HANGUL_DIGITS.size() * 4;

    /**
     * 한글 숫자
     */
    public static final List<String> HANGUL_NUMBERS = List.of(
            "", "일", "이", "삼", "사", "오", "육", "칠", "팔", "구"
    );

    /**
     * 한글 숫자 (소수점용)
     */
    public static final List<String> HANGUL_NUMBERS_FOR_DECIMAL = List.of(
            "영", "일", "이", "삼", "사", "오", "육", "칠", "팔", "구"
    );

    /**
     * 한글 자릿수
     */
    public static final List<String> HANGUL_CARDINAL = List.of(
            "", "십", "백", "천"
    );
}
