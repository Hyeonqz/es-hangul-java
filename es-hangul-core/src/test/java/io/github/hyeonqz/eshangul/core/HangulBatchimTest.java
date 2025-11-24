package io.github.hyeonqz.eshangul.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static io.github.hyeonqz.eshangul.core.HangulBatchim.*;
import static org.junit.jupiter.api.Assertions.*;

class HangulBatchimTest {

    @Nested
    @DisplayName("받침이 있다고 판단되는 경우")
    class HasBatchimTrue {

        @Test
        @DisplayName("값 문자에서 받침이 있으므로 true를 반환한다")
        void test값() {
            assertTrue(hasBatchim("값"));
        }

        @Test
        @DisplayName("공 문자에서 받침이 있으므로 true를 반환한다")
        void test공() {
            assertTrue(hasBatchim("공"));
        }

        @Test
        @DisplayName("읊 문자에서 받침이 있으므로 true를 반환한다")
        void test읊() {
            assertTrue(hasBatchim("읊"));
        }
    }

    @Nested
    @DisplayName("받침이 없다고 판단되는 경우")
    class HasBatchimFalse {

        @Test
        @DisplayName("토 문자에서 받침이 없으므로 false를 반환한다")
        void test토() {
            assertFalse(hasBatchim("토"));
        }

        @Test
        @DisplayName("서 문자에서 받침이 없으므로 false를 반환한다")
        void test서() {
            assertFalse(hasBatchim("서"));
        }

        @Test
        @DisplayName("빈 문자열은 받침이 없으므로 false를 반환한다")
        void testEmpty() {
            assertFalse(hasBatchim(""));
        }
    }

    @Nested
    @DisplayName("완성된 한글이 아닌 경우")
    class NotCompleteHangul {

        @Test
        @DisplayName("한글이 자음 또는 모음으로만 구성된 경우 false를 반환한다")
        void testJamo() {
            assertFalse(hasBatchim("ㄱ"));
            assertFalse(hasBatchim("ㅏ"));
        }

        @Test
        @DisplayName("한글 외의 문자를 입력하면 false를 반환한다")
        void testNonHangul() {
            assertFalse(hasBatchim("cat"));
            assertFalse(hasBatchim("!"));
        }
    }

    @Nested
    @DisplayName("홑받침")
    class SingleBatchim {

        @Test
        @DisplayName("홑받침을 받으면 true를 반환한다")
        void testSingleBatchimTrue() {
            assertTrue(hasBatchim("공", BatchimType.SINGLE));
            assertTrue(hasBatchim("핫", BatchimType.SINGLE));
            assertTrue(hasBatchim("양", BatchimType.SINGLE));
            assertTrue(hasBatchim("신", BatchimType.SINGLE));
            assertTrue(hasBatchim("확", BatchimType.SINGLE));
        }

        @Test
        @DisplayName("겹받침을 받으면 false를 반환한다")
        void testDoubleBatchimFalse() {
            assertFalse(hasBatchim("값", BatchimType.SINGLE));
            assertFalse(hasBatchim("읊", BatchimType.SINGLE));
            assertFalse(hasBatchim("웱", BatchimType.SINGLE));
        }

        @Test
        @DisplayName("받침이 없는 문자를 받으면 false를 반환한다")
        void testNoBatchimFalse() {
            assertFalse(hasBatchim("토", BatchimType.SINGLE));
            assertFalse(hasBatchim("서", BatchimType.SINGLE));
            assertFalse(hasBatchim("와", BatchimType.SINGLE));
        }

        @Test
        @DisplayName("한글 외의 문자를 입력하면 false를 반환한다")
        void testNonHangulFalse() {
            assertFalse(hasBatchim("cat", BatchimType.SINGLE));
            assertFalse(hasBatchim("", BatchimType.SINGLE));
            assertFalse(hasBatchim("?", BatchimType.SINGLE));
        }
    }

    @Nested
    @DisplayName("겹받침")
    class DoubleBatchim {

        @Test
        @DisplayName("겹받침을 받으면 true를 반환한다")
        void testDoubleBatchimTrue() {
            assertTrue(hasBatchim("값", BatchimType.DOUBLE));
            assertTrue(hasBatchim("읊", BatchimType.DOUBLE));
            assertTrue(hasBatchim("웱", BatchimType.DOUBLE));
        }

        @Test
        @DisplayName("홑받침을 받으면 false를 반환한다")
        void testSingleBatchimFalse() {
            assertFalse(hasBatchim("공", BatchimType.DOUBLE));
            assertFalse(hasBatchim("핫", BatchimType.DOUBLE));
            assertFalse(hasBatchim("양", BatchimType.DOUBLE));
            assertFalse(hasBatchim("신", BatchimType.DOUBLE));
            assertFalse(hasBatchim("확", BatchimType.DOUBLE));
        }

        @Test
        @DisplayName("받침이 없는 문자를 받으면 false를 반환한다")
        void testNoBatchimFalse() {
            assertFalse(hasBatchim("토", BatchimType.DOUBLE));
            assertFalse(hasBatchim("서", BatchimType.DOUBLE));
            assertFalse(hasBatchim("와", BatchimType.DOUBLE));
        }

        @Test
        @DisplayName("한글 외의 문자를 입력하면 false를 반환한다")
        void testNonHangulFalse() {
            assertFalse(hasBatchim("cat", BatchimType.DOUBLE));
            assertFalse(hasBatchim("", BatchimType.DOUBLE));
            assertFalse(hasBatchim("?", BatchimType.DOUBLE));
        }
    }
}
