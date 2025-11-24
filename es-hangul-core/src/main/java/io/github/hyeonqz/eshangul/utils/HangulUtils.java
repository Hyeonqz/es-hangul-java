package io.github.hyeonqz.eshangul.utils;

/**
 * 한글 처리를 위한 기본 유틸리티 메서드 모음
 */
public final class HangulUtils {

    private HangulUtils() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * 한 글자가 완성된 한글인지 확인합니다.
     *
     * @param character 확인할 문자
     * @return 완성된 한글이면 true
     */
    public static boolean isHangulCharacter(String character) {
        if (character == null || character.length() != 1) {
            return false;
        }
        return character.matches("^[가-힣]$");
    }

    /**
     * 한 글자가 한글 자음 또는 모음인지 확인합니다.
     *
     * @param character 확인할 문자
     * @return 한글 자음/모음이면 true
     */
    public static boolean isHangulAlphabet(String character) {
        if (character == null || character.length() != 1) {
            return false;
        }
        return character.matches("^[ㄱ-ㅣ]$");
    }

    /**
     * 문자열이 한글(완성형 또는 자모)로만 이루어져 있는지 확인합니다.
     *
     * @param str 확인할 문자열
     * @return 한글로만 이루어져 있으면 true
     */
    public static boolean isHangul(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        return str.matches("^[가-힣ㄱ-ㅣ\\s]+$");
    }

    /**
     * 한글인지 검증하고, 아니면 예외를 발생시킵니다.
     *
     * @param str 확인할 문자열
     * @param message 에러 메시지
     * @throws IllegalArgumentException 한글이 아닌 경우
     */
    public static void assertHangul(String str, String message) {
        if (!isHangul(str)) {
            String errorMessage = message != null ? message :
                    String.format("%s is not a valid hangul string", str);
            throw new IllegalArgumentException(errorMessage);
        }
    }

    /**
     * 한글인지 검증하고, 아니면 예외를 발생시킵니다.
     *
     * @param str 확인할 문자열
     * @throws IllegalArgumentException 한글이 아닌 경우
     */
    public static void assertHangul(String str) {
        assertHangul(str, null);
    }

    /**
     * 문자가 공백인지 확인합니다.
     *
     * @param character 확인할 문자
     * @return 공백이면 true
     */
    public static boolean isBlank(String character) {
        if (character == null || character.isEmpty()) {
            return false;
        }
        return character.matches("^\\s$");
    }

    /**
     * 조건을 검증하고, 거짓이면 예외를 발생시킵니다.
     *
     * @param condition 검증할 조건
     * @param errorMessage 에러 메시지
     * @throws IllegalArgumentException 조건이 거짓인 경우
     */
    public static void assertCondition(boolean condition, String errorMessage) {
        if (!condition) {
            String message = errorMessage != null ? errorMessage : "Invalid condition";
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 여러 문자열을 합칩니다.
     *
     * @param args 합칠 문자열들
     * @return 합쳐진 문자열
     */
    public static String joinString(String... args) {
        return String.join("", args);
    }
}
