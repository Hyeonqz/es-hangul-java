package io.github.hyeonqz.eshangul.spring.service;

import io.github.hyeonqz.eshangul.romanizer.HangulRomanizer;
import io.github.hyeonqz.eshangul.romanizer.RomanizedCharacter;
import io.github.hyeonqz.eshangul.romanizer.RomanizedCharacter.ConsonantAssimilation;
import io.github.hyeonqz.eshangul.romanizer.RomanizedCharacter.Type;

/**
 * 한글 로마자 변환을 위한 통합 서비스
 * <p>
 * 국립국어원 로마자 표기법 기반의 한글→영문 변환 기능을 제공합니다.
 * </p>
 *
 * <h2>주요 기능</h2>
 * <ul>
 *   <li>기본 로마자 변환</li>
 *   <li>자음 동화 옵션 지원 (순행/역행)</li>
 *   <li>단어 타입별 변환 (일반/체언/합성어/지명/인명)</li>
 *   <li>편의 메소드 제공</li>
 * </ul>
 *
 * @see HangulRomanizer
 * @see RomanizedCharacter
 */
public class RomanizeService {
    private final HangulRomanizer hangulRomanizer = new HangulRomanizer();

    // ==================== 기본 변환 메소드 ====================

    /**
     * 한글을 로마자로 변환합니다 (기본 옵션 사용).
     * <p>
     * 기본값: 역행 동화(Regressive), 일반 단어(Typical)
     * </p>
     *
     * @param hangul 변환할 한글 문자열
     * @return 로마자로 변환된 문자열
     * @throws NullPointerException hangul이 null인 경우
     *
     * @example
     * <pre>
     * romanize("안녕하세요")  // "Annyeonghaseyo"
     * romanize("대한민국")    // "Daehanmin-guk"
     * </pre>
     */
    public String romanize(String hangul) {
        return hangulRomanizer.romanize(hangul);
    }

    /**
     * 한글을 로마자로 변환합니다 (자음 동화 옵션 지정).
     *
     * @param hangul 변환할 한글 문자열
     * @param consonantAssimilation 자음 동화 타입 (Progressive: 순행, Regressive: 역행)
     * @return 로마자로 변환된 문자열
     * @throws NullPointerException hangul이 null인 경우
     *
     * @example
     * <pre>
     * romanize("신라면", ConsonantAssimilation.Regressive)   // "Sillamyeon"
     * romanize("신라면", ConsonantAssimilation.Progressive)  // "Sinnamyeon"
     * </pre>
     */
    public String romanize(String hangul, ConsonantAssimilation consonantAssimilation) {
        return hangulRomanizer.romanize(hangul, consonantAssimilation);
    }

    /**
     * 한글을 로마자로 변환합니다 (단어 타입 지정).
     *
     * @param hangul 변환할 한글 문자열
     * @param type 단어 타입 (Typical/Substantives/Compound/District/Name/NameTypical)
     * @return 로마자로 변환된 문자열
     * @throws NullPointerException hangul이 null인 경우
     *
     * @example
     * <pre>
     * romanize("각하", Type.Typical)        // "Gaka"
     * romanize("각하", Type.Substantives)   // "Gakha"
     * romanize("강남구", Type.District)      // "Gangnam-gu"
     * </pre>
     */
    public String romanize(String hangul, Type type) {
        return hangulRomanizer.romanize(hangul, type);
    }

    /**
     * 한글을 로마자로 변환합니다 (모든 옵션 지정).
     *
     * @param hangul 변환할 한글 문자열
     * @param type 단어 타입
     * @param consonantAssimilation 자음 동화 타입
     * @return 로마자로 변환된 문자열
     * @throws NullPointerException hangul이 null인 경우
     *
     * @example
     * <pre>
     * romanize("생산량", Type.Typical, ConsonantAssimilation.Regressive)   // "Saengsallyang"
     * romanize("생산량", Type.Typical, ConsonantAssimilation.Progressive)  // "Saengsannyang"
     * </pre>
     */
    public String romanize(String hangul, Type type, ConsonantAssimilation consonantAssimilation) {
        return hangulRomanizer.romanize(hangul, type, consonantAssimilation);
    }

    // ==================== 자음 동화 편의 메소드 ====================

    /**
     * 순행 동화 방식으로 한글을 로마자로 변환합니다.
     * <p>
     * 순행 동화: 앞 음절의 영향을 받는 방식
     * </p>
     *
     * @param hangul 변환할 한글 문자열
     * @return 로마자로 변환된 문자열
     *
     * @example
     * <pre>
     * romanizeProgressive("신라면")  // "Sinnamyeon"
     * </pre>
     */
    public String romanizeProgressive(String hangul) {
        return hangulRomanizer.romanize(hangul, ConsonantAssimilation.Progressive);
    }

    /**
     * 역행 동화 방식으로 한글을 로마자로 변환합니다.
     * <p>
     * 역행 동화: 뒤 음절의 영향을 받는 방식 (기본값)
     * </p>
     *
     * @param hangul 변환할 한글 문자열
     * @return 로마자로 변환된 문자열
     *
     * @example
     * <pre>
     * romanizeRegressive("신라면")  // "Sillamyeon"
     * </pre>
     */
    public String romanizeRegressive(String hangul) {
        return hangulRomanizer.romanize(hangul, ConsonantAssimilation.Regressive);
    }

    // ==================== 단어 타입별 편의 메소드 ====================

    /**
     * 체언(명사) 규칙을 적용하여 한글을 로마자로 변환합니다.
     * <p>
     * 체언: 'ㄱ, ㄷ, ㅂ' 뒤에 'ㅎ'이 따를 때 'ㅎ'을 밝혀 적습니다.
     * </p>
     *
     * @param hangul 변환할 한글 문자열
     * @return 로마자로 변환된 문자열
     *
     * @example
     * <pre>
     * romanizeSubstantives("각하")  // "Gakha" (cf. 일반: "Gaka")
     * romanizeSubstantives("법학")  // "Beophak" (cf. 일반: "Beopak")
     * </pre>
     */
    public String romanizeSubstantives(String hangul) {
        return hangulRomanizer.romanize(hangul, Type.Substantives);
    }

    /**
     * 합성어 규칙을 적용하여 한글을 로마자로 변환합니다.
     * <p>
     * 합성어: 앞 단어 끝이 자음이고 뒤 단어 첫 음절이 'ㅑ, ㅕ, ㅛ, ㅠ, ㅣ'로 시작하면 'ㄴ'을 첨가합니다.
     * </p>
     *
     * @param hangul 변환할 한글 문자열
     * @return 로마자로 변환된 문자열
     *
     * @example
     * <pre>
     * romanizeCompound("색연필")  // "Saengnyeonpil" (cf. 일반: "Saegyeonpil")
     * romanizeCompound("콩엿")    // "Kongnyeot" (cf. 일반: "Kong-yeot")
     * </pre>
     */
    public String romanizeCompound(String hangul) {
        return hangulRomanizer.romanize(hangul, Type.Compound);
    }

    /**
     * 지명 규칙을 적용하여 한글을 로마자로 변환합니다.
     * <p>
     * 지명: 행정구역 단위(시, 군, 구, 읍, 면, 동, 리, 가, 로, 길 등)를 하이픈(-)으로 구분합니다.
     * </p>
     *
     * @param hangul 변환할 지명 문자열
     * @return 로마자로 변환된 문자열
     *
     * @example
     * <pre>
     * romanizeDistrict("강남구")       // "Gangnam-gu"
     * romanizeDistrict("종로2가")      // "Jongno 2-ga"
     * romanizeDistrict("성남대로2번길") // "Seongnam-daero 2beon-gil"
     * </pre>
     */
    public String romanizeDistrict(String hangul) {
        return hangulRomanizer.romanize(hangul, Type.District);
    }

    /**
     * 인명 규칙을 적용하여 한글을 로마자로 변환합니다.
     * <p>
     * 인명: 성과 이름을 공백으로 구분합니다.
     * 음운 변화 없이 원음 그대로 표기합니다.
     * </p>
     *
     * @param hangul 변환할 인명 문자열
     * @return 로마자로 변환된 문자열
     *
     * @example
     * <pre>
     * romanizeName("이지은")     // "I Jieun"
     * romanizeName("제갈공명")   // "Jegal Gongmyeong"
     * </pre>
     */
    public String romanizeName(String hangul) {
        return hangulRomanizer.romanize(hangul, Type.Name);
    }

    /**
     * 인명을 흔한 성씨 표기법에 따라 로마자로 변환합니다.
     * <p>
     * 흔한 성씨: 관용적으로 많이 사용되는 성씨 표기를 적용합니다.
     * 예) 박→Park, 김→Kim, 이→Lee, 최→Choi 등
     * </p>
     *
     * @param hangul 변환할 인명 문자열
     * @return 로마자로 변환된 문자열
     *
     * @example
     * <pre>
     * romanizeNameTypical("박보검")  // "Park Bogeom" (cf. 일반: "Bak Bogeom")
     * romanizeNameTypical("김민재")  // "Kim Minjae" (cf. 일반: "Gim Minjae")
     * romanizeNameTypical("이순신")  // "Lee Sunsin" (cf. 일반: "I Sunsin")
     * </pre>
     */
    public String romanizeNameTypical(String hangul) {
        return hangulRomanizer.romanize(hangul, Type.NameTypical);
    }

    // ==================== 조합 편의 메소드 ====================

    /**
     * 지명을 순행 동화 방식으로 로마자로 변환합니다.
     *
     * @param hangul 변환할 지명 문자열
     * @return 로마자로 변환된 문자열
     *
     * @example
     * <pre>
     * romanizeDistrictProgressive("신림동")  // "Sinnim-dong"
     * </pre>
     */
    public String romanizeDistrictProgressive(String hangul) {
        return hangulRomanizer.romanize(hangul, Type.District, ConsonantAssimilation.Progressive);
    }

    /**
     * 지명을 역행 동화 방식으로 로마자로 변환합니다.
     *
     * @param hangul 변환할 지명 문자열
     * @return 로마자로 변환된 문자열
     *
     * @example
     * <pre>
     * romanizeDistrictRegressive("신림동")  // "Sillim-dong"
     * </pre>
     */
    public String romanizeDistrictRegressive(String hangul) {
        return hangulRomanizer.romanize(hangul, Type.District, ConsonantAssimilation.Regressive);
    }

    /**
     * 합성어를 순행 동화 방식으로 로마자로 변환합니다.
     *
     * @param hangul 변환할 합성어 문자열
     * @return 로마자로 변환된 문자열
     */
    public String romanizeCompoundProgressive(String hangul) {
        return hangulRomanizer.romanize(hangul, Type.Compound, ConsonantAssimilation.Progressive);
    }

    /**
     * 합성어를 역행 동화 방식으로 로마자로 변환합니다.
     *
     * @param hangul 변환할 합성어 문자열
     * @return 로마자로 변환된 문자열
     */
    public String romanizeCompoundRegressive(String hangul) {
        return hangulRomanizer.romanize(hangul, Type.Compound, ConsonantAssimilation.Regressive);
    }

    // ==================== 유틸리티 메소드 ====================

    /**
     * 한글 문자열을 여러 타입으로 변환한 결과를 반환합니다.
     * <p>
     * 디버깅이나 비교 목적으로 유용합니다.
     * </p>
     *
     * @param hangul 변환할 한글 문자열
     * @return 타입별 변환 결과를 담은 RomanizationResult 객체
     *
     * @example
     * <pre>
     * RomanizationResult result = romanizeAll("박보검");
     * result.getTypical()      // "Bakbogeom"
     * result.getName()         // "Bak Bogeom"
     * result.getNameTypical()  // "Park Bogeom"
     * </pre>
     */
    public RomanizationResult romanizeAll(String hangul) {
        return new RomanizationResult(
            hangulRomanizer.romanize(hangul, Type.Typical),
            hangulRomanizer.romanize(hangul, Type.Substantives),
            hangulRomanizer.romanize(hangul, Type.Compound),
            hangulRomanizer.romanize(hangul, Type.District),
            hangulRomanizer.romanize(hangul, Type.Name),
            hangulRomanizer.romanize(hangul, Type.NameTypical)
        );
    }

    /**
     * 타입별 로마자 변환 결과를 담는 클래스
     */
    public static class RomanizationResult {
        private final String typical;
        private final String substantives;
        private final String compound;
        private final String district;
        private final String name;
        private final String nameTypical;

        public RomanizationResult(String typical, String substantives, String compound,
                                 String district, String name, String nameTypical) {
            this.typical = typical;
            this.substantives = substantives;
            this.compound = compound;
            this.district = district;
            this.name = name;
            this.nameTypical = nameTypical;
        }

        /** 일반 단어 타입 변환 결과 */
        public String getTypical() { return typical; }

        /** 체언 타입 변환 결과 */
        public String getSubstantives() { return substantives; }

        /** 합성어 타입 변환 결과 */
        public String getCompound() { return compound; }

        /** 지명 타입 변환 결과 */
        public String getDistrict() { return district; }

        /** 인명 타입 변환 결과 */
        public String getName() { return name; }

        /** 흔한 성씨 표기 인명 타입 변환 결과 */
        public String getNameTypical() { return nameTypical; }

        @Override
        public String toString() {
            return "RomanizationResult{" +
                    "typical='" + typical + '\'' +
                    ", substantives='" + substantives + '\'' +
                    ", compound='" + compound + '\'' +
                    ", district='" + district + '\'' +
                    ", name='" + name + '\'' +
                    ", nameTypical='" + nameTypical + '\'' +
                    '}';
        }
    }
}
