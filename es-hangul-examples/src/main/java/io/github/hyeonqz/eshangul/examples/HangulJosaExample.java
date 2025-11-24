package io.github.hyeonqz.eshangul.examples;

import io.github.hyeonqz.eshangul.core.HangulJosa;

/**
 * 한글 조사 처리 예시
 */
public class HangulJosaExample {

    public static void main(String[] args) {
        // 이/가 조사
        String result1 = HangulJosa.josa("사과", HangulJosa.JosaOption.이_가);
        System.out.println("사과 + 이/가: " + result1); // "사과가"

        String result2 = HangulJosa.josa("책", HangulJosa.JosaOption.이_가);
        System.out.println("책 + 이/가: " + result2); // "책이"

        // 을/를 조사
        String result3 = HangulJosa.josa("물", HangulJosa.JosaOption.을_를);
        System.out.println("물 + 을/를: " + result3); // "물을"

        String result4 = HangulJosa.josa("사과", HangulJosa.JosaOption.을_를);
        System.out.println("사과 + 을/를: " + result4); // "사과를"

        // 은/는 조사
        String result5 = HangulJosa.josa("학교", HangulJosa.JosaOption.은_는);
        System.out.println("학교 + 은/는: " + result5); // "학교는"

        // 으로/로 조사
        String result6 = HangulJosa.josa("집", HangulJosa.JosaOption.으로_로);
        System.out.println("집 + 으로/로: " + result6); // "집으로"
    }
}
