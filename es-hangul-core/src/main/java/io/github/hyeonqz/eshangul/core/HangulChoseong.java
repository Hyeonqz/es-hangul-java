package io.github.hyeonqz.eshangul.core;

import java.text.Normalizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.github.hyeonqz.eshangul.constants.HangulConstants.CHOSEONGS;

/**
 * 한글 초성 관련 유틸리티 클래스
 */
public final class HangulChoseong {

    private HangulChoseong() {
        throw new UnsupportedOperationException("Utility class");
    }

    // NFD 초성 범위
    private static final int NFD_CHOSEONG_START = 0x1100;
    private static final int NFD_CHOSEONG_END = 0x1112;

    // NFD 초성과 NFC 자음만 남기고 제거하는 정규식
    private static final Pattern EXTRACT_CHOSEONG_PATTERN = Pattern.compile(
            String.format("[^\\u%04x-\\u%04xㄱ-ㅎ\\s]+", NFD_CHOSEONG_START, NFD_CHOSEONG_END)
    );

    // NFD 초성만 선택하는 정규식
    private static final Pattern CHOOSE_NFD_CHOSEONG_PATTERN = Pattern.compile(
            String.format("[\\u%04x-\\u%04x]", NFD_CHOSEONG_START, NFD_CHOSEONG_END)
    );

    /**
     * 단어에서 초성을 추출합니다.
     *
     * @param word 초성을 추출할 단어
     * @return 추출된 초성
     * @example
     * getChoseong("사과") // "ㅅㄱ"
     * getChoseong("띄어 쓰기") // "ㄸㅇ ㅆㄱ"
     */
    public static String getChoseong(String word) {
        // NFD로 정규화 (한글을 자모로 분해)
        String normalized = Normalizer.normalize(word, Normalizer.Form.NFD);

        // NFD 초성과 NFC 자음 외 문자 제거
        Matcher matcher = EXTRACT_CHOSEONG_PATTERN.matcher(normalized);
        String extracted = matcher.replaceAll("");

        // NFD 초성을 NFC 자음으로 변환
        StringBuilder result = new StringBuilder();
        for (char c : extracted.toCharArray()) {
            if (c >= NFD_CHOSEONG_START && c <= NFD_CHOSEONG_END) {
                int index = c - NFD_CHOSEONG_START;
                result.append(CHOSEONGS.get(index));
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }
}
