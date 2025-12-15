package io.github.hyeonqz.eshangul.spring.service;

import io.github.hyeonqz.eshangul.romanizer.RomanizedCharacter.ConsonantAssimilation;
import io.github.hyeonqz.eshangul.romanizer.RomanizedCharacter.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * RomanizeService 테스트
 */
@DisplayName("RomanizeService 테스트")
class RomanizeServiceTest {

    private RomanizeService romanizeService;

    @BeforeEach
    void setUp() {
        romanizeService = new RomanizeService();
    }

    @Test
    @DisplayName("기본 로마자 변환 테스트")
    void testBasicRomanize() {
        assertEquals("Annyeonghaseyo", romanizeService.romanize("안녕하세요"));
        assertEquals("Daehanmin-guk", romanizeService.romanize("대한민국"));
        assertEquals("Han-geul", romanizeService.romanize("한글"));
    }

    @Test
    @DisplayName("자음 동화 옵션 테스트")
    void testConsonantAssimilation() {
        assertEquals("Sillamyeon", romanizeService.romanize("신라면", ConsonantAssimilation.Regressive));
        assertEquals("Sinnamyeon", romanizeService.romanize("신라면", ConsonantAssimilation.Progressive));
    }

    @Test
    @DisplayName("단어 타입별 변환 테스트")
    void testByType() {
        // 체언
        assertEquals("Gakha", romanizeService.romanize("각하", Type.Substantives));

        // 합성어
        assertEquals("Saengnyeonpil", romanizeService.romanize("색연필", Type.Compound));

        // 지명
        assertEquals("Gangnam-gu", romanizeService.romanize("강남구", Type.District));

        // 인명
        assertEquals("I Jieun", romanizeService.romanize("이지은", Type.Name));
        assertEquals("Park Bogeom", romanizeService.romanize("박보검", Type.NameTypical));
    }

    @Test
    @DisplayName("편의 메소드 테스트 - 자음 동화")
    void testConvenienceMethods() {
        assertEquals("Sinnamyeon", romanizeService.romanizeProgressive("신라면"));
        assertEquals("Sillamyeon", romanizeService.romanizeRegressive("신라면"));
    }

    @Test
    @DisplayName("편의 메소드 테스트 - 타입별")
    void testTypeConvenienceMethods() {
        assertEquals("Gakha", romanizeService.romanizeSubstantives("각하"));
        assertEquals("Saengnyeonpil", romanizeService.romanizeCompound("색연필"));
        assertEquals("Gangnam-gu", romanizeService.romanizeDistrict("강남구"));
        assertEquals("I Jieun", romanizeService.romanizeName("이지은"));
        assertEquals("Park Bogeom", romanizeService.romanizeNameTypical("박보검"));
    }

    @Test
    @DisplayName("조합 편의 메소드 테스트")
    void testCombinedConvenienceMethods() {
        assertNotNull(romanizeService.romanizeDistrictProgressive("신림동"));
        assertNotNull(romanizeService.romanizeDistrictRegressive("신림동"));
        assertNotNull(romanizeService.romanizeCompoundProgressive("색연필"));
        assertNotNull(romanizeService.romanizeCompoundRegressive("색연필"));
    }

    @Test
    @DisplayName("romanizeAll 메소드 테스트")
    void testRomanizeAll() {
        RomanizeService.RomanizationResult result = romanizeService.romanizeAll("박보검");

        assertNotNull(result);
        assertEquals("Bakbogeom", result.getTypical());
        assertEquals("Bak Bogeom", result.getName());
        assertEquals("Park Bogeom", result.getNameTypical());

        // toString 테스트
        assertNotNull(result.toString());
        assertTrue(result.toString().contains("Park Bogeom"));
    }

    @Test
    @DisplayName("null 입력 예외 테스트")
    void testNullInput() {
        assertThrows(NullPointerException.class, () -> romanizeService.romanize(null));
        assertThrows(NullPointerException.class, () -> romanizeService.romanizeName(null));
        assertThrows(NullPointerException.class, () -> romanizeService.romanizeDistrict(null));
    }

    @Test
    @DisplayName("빈 문자열 테스트")
    void testEmptyString() {
        assertEquals("", romanizeService.romanize(""));
    }
}
