package io.github.hyeonqz.eshangul.core;

import static io.github.hyeonqz.eshangul.constants.HangulConstants.*;

/**
 * 한글 받침 관련 유틸리티 클래스
 */
public final class HangulBatchim {

    private HangulBatchim() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * 받침 타입
     */
    public enum BatchimType {
        SINGLE,  // 단일 받침
        DOUBLE   // 쌍받침
    }

    /**
     * 한글 문자열의 마지막 글자가 받침이 있는지 확인합니다.
     *
     * @param str 확인할 문자열
     * @return 받침이 있으면 true
     * @example
     * hasBatchim("값") // true
     * hasBatchim("토") // false
     */
    public static boolean hasBatchim(String str) {
        return hasBatchim(str, null);
    }

    /**
     * 한글 문자열의 마지막 글자가 특정 타입의 받침이 있는지 확인합니다.
     *
     * @param str 확인할 문자열
     * @param type 받침 타입 (null이면 모든 타입 체크)
     * @return 받침이 있으면 true
     * @example
     * hasBatchim("갑", BatchimType.SINGLE) // true
     * hasBatchim("값", BatchimType.SINGLE) // false
     * hasBatchim("값", BatchimType.DOUBLE) // true
     */
    public static boolean hasBatchim(String str, BatchimType type) {
        if (str == null || str.isEmpty()) {
            return false;
        }

        char lastChar = str.charAt(str.length() - 1);
        int charCode = lastChar;

        boolean isNotCompleteHangul = charCode < COMPLETE_HANGUL_START_CHARCODE ||
                charCode > COMPLETE_HANGUL_END_CHARCODE;

        if (isNotCompleteHangul) {
            return false;
        }

        int batchimCode = (charCode - COMPLETE_HANGUL_START_CHARCODE) % NUMBER_OF_JONGSEONG;
        int batchimLength = JONGSEONGS.get(batchimCode).length();

        if (type == BatchimType.SINGLE) {
            return batchimLength == 1;
        } else if (type == BatchimType.DOUBLE) {
            return batchimLength == 2;
        } else {
            return batchimCode > 0;
        }
    }
}
