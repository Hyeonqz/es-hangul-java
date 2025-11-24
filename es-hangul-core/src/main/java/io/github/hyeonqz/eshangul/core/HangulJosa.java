package io.github.hyeonqz.eshangul.core;

import io.github.hyeonqz.eshangul.characters.DisassembledCharacter;
import io.github.hyeonqz.eshangul.characters.HangulCharacter;

import java.util.Arrays;
import java.util.List;

import static io.github.hyeonqz.eshangul.core.HangulBatchim.hasBatchim;
import static io.github.hyeonqz.eshangul.constants.HangulConstants.ALPHABET_TO_KOREAN;

/**
 * 한글 조사 처리 유틸리티 클래스
 */
public final class HangulJosa {

    private HangulJosa() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * 조사 옵션
     */
    public enum JosaOption {
        이_가("이", "가"),
        을_를("을", "를"),
        은_는("은", "는"),
        으로_로("으로", "로"),
        와_과("와", "과"),
        이나_나("이나", "나"),
        이란_란("이란", "란"),
        아_야("아", "야"),
        이랑_랑("이랑", "랑"),
        이에요_예요("이에요", "예요"),
        으로서_로서("으로서", "로서"),
        으로써_로써("으로써", "로써"),
        으로부터_로부터("으로부터", "로부터"),
        이라_라("이라", "라");

        private final String withBatchim;
        private final String withoutBatchim;

        JosaOption(String withBatchim, String withoutBatchim) {
            this.withBatchim = withBatchim;
            this.withoutBatchim = withoutBatchim;
        }

        public String getWithBatchim() {
            return withBatchim;
        }

        public String getWithoutBatchim() {
            return withoutBatchim;
        }
    }

    // 로 조사들
    private static final List<JosaOption> RO_JOSAS = Arrays.asList(
            JosaOption.으로_로,
            JosaOption.으로서_로서,
            JosaOption.으로써_로써,
            JosaOption.으로부터_로부터
    );

    /**
     * 단어에 적절한 조사를 붙여 반환합니다.
     *
     * @param word 단어
     * @param josaOption 조사 옵션
     * @return 조사가 붙은 단어
     * @example
     * josa("사과", JosaOption.이_가) // "사과가"
     * josa("책", JosaOption.이_가) // "책이"
     * josa("토스", JosaOption.은_는) // "토스는"
     */
    public static String josa(String word, JosaOption josaOption) {
        if (word == null || word.isEmpty()) {
            return word;
        }

        // 영문자만으로 구성된 경우
        if (word.matches("^[A-Z]+$")) {
            String lastChar = String.valueOf(word.charAt(word.length() - 1));
            String koreanPronunciation = ALPHABET_TO_KOREAN.get(lastChar);
            if (koreanPronunciation != null) {
                return word + josaPicker(koreanPronunciation, josaOption);
            }
        }

        return word + josaPicker(word, josaOption);
    }

    /**
     * 단어에 맞는 조사를 선택합니다.
     *
     * @param word 단어
     * @param josaOption 조사 옵션
     * @return 선택된 조사
     */
    public static String josaPicker(String word, JosaOption josaOption) {
        if (word == null || word.isEmpty()) {
            return josaOption.getWithBatchim();
        }

        boolean hasBatchimValue = hasBatchim(word);
        int index = hasBatchimValue ? 0 : 1;

        // 종성이 ㄹ인지 확인
        boolean isJongseongRieul = false;
        if (hasBatchimValue) {
            DisassembledCharacter disassembled = HangulCharacter.disassembleCompleteCharacter(
                    String.valueOf(word.charAt(word.length() - 1))
            );
            if (disassembled != null) {
                isJongseongRieul = "ㄹ".equals(disassembled.jongseong());
            }
        }

        boolean isCaseOfRo = hasBatchimValue && isJongseongRieul && RO_JOSAS.contains(josaOption);

        if (josaOption == JosaOption.와_과 || isCaseOfRo) {
            index = index == 0 ? 1 : 0;
        }

        return index == 0 ? josaOption.getWithBatchim() : josaOption.getWithoutBatchim();
    }
}
