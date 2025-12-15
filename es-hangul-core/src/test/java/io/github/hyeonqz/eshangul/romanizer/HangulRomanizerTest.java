package io.github.hyeonqz.eshangul.romanizer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for hangulRomanizer.
 * Based on the original korean-romanizer tests.
 */
@DisplayName("한글 로마자 변환 테스트")
class hangulRomanizerTest {

	private HangulRomanizer hangulRomanizer = new HangulRomanizer();

	@Test
	@DisplayName("RomanizedCharacter 기본 동작 테스트")
	void testRomanizedCharacter() {
		RomanizedCharacter c = new RomanizedCharacter(' ');
		assertNull(c.getChosung());
		assertNull(c.getJungsung());
		assertNull(c.getJongsung());
		assertEquals(' ', c.getCharacter());
		assertEquals(" ", c.toString());
		assertEquals(" ", c.getRomanizedString());
		assertFalse(c.isKoreanCharacter());

		assertEquals('닭', new RomanizedCharacter(RomanizedCharacter.Chosung.ㄷ, RomanizedCharacter.Jungsung.ㅏ, RomanizedCharacter.Jongsung.ㄺ).getCharacter());
		assertEquals('소', new RomanizedCharacter(RomanizedCharacter.Chosung.ㅅ, RomanizedCharacter.Jungsung.ㅗ, RomanizedCharacter.Jongsung.NONE).getCharacter());

		RomanizedCharacter ga = new RomanizedCharacter('가');
		RomanizedCharacter na = new RomanizedCharacter('나');
		RomanizedCharacter na2 = new RomanizedCharacter('나');

		assertTrue(ga.compareTo(na) < 0);
		assertEquals(0, na.compareTo(na2));
		assertEquals(na, na2);
		assertEquals(Character.hashCode('가'), ga.hashCode());
		assertEquals("가", ga.toString());
	}

	@Test
	@DisplayName("예외 처리 테스트")
	void testException() {
		Throwable exception = assertThrows(NullPointerException.class, () -> hangulRomanizer.romanize(null));
		assertEquals("String should not be null.", exception.getMessage());
		assertEquals("", hangulRomanizer.romanize(""));
	}

	@Test
	@DisplayName("자음 동화 옵션 테스트")
	void testByConsonantAssimilation() {
		// Regressive vs Progressive
		assertEquals("Baengno", hangulRomanizer.romanize("백로", RomanizedCharacter.ConsonantAssimilation.Regressive));
		assertEquals("Baengno", hangulRomanizer.romanize("백로", RomanizedCharacter.ConsonantAssimilation.Progressive));

		assertEquals("Digeullieul", hangulRomanizer.romanize("디귿리을", RomanizedCharacter.ConsonantAssimilation.Regressive));
		assertEquals("Digeunnieul", hangulRomanizer.romanize("디귿리을", RomanizedCharacter.ConsonantAssimilation.Progressive));

		assertEquals("Amnokgang", hangulRomanizer.romanize("압록강", RomanizedCharacter.ConsonantAssimilation.Regressive));
		assertEquals("Amnokgang", hangulRomanizer.romanize("압록강", RomanizedCharacter.ConsonantAssimilation.Progressive));

		assertEquals("Wangsimni", hangulRomanizer.romanize("왕십리", RomanizedCharacter.ConsonantAssimilation.Regressive));
		assertEquals("Wangsimni", hangulRomanizer.romanize("왕십리", RomanizedCharacter.ConsonantAssimilation.Progressive));

		assertEquals("Hyeomnyeok", hangulRomanizer.romanize("협력", RomanizedCharacter.ConsonantAssimilation.Regressive));
		assertEquals("Hyeomnyeok", hangulRomanizer.romanize("협력", RomanizedCharacter.ConsonantAssimilation.Progressive));

		assertEquals("Seollal", hangulRomanizer.romanize("설날", RomanizedCharacter.ConsonantAssimilation.Regressive));
		assertEquals("Seollal", hangulRomanizer.romanize("설날", RomanizedCharacter.ConsonantAssimilation.Progressive));

		assertEquals("Saengsallyang", hangulRomanizer.romanize("생산량", RomanizedCharacter.ConsonantAssimilation.Regressive));
		assertEquals("Saengsannyang", hangulRomanizer.romanize("생산량", RomanizedCharacter.ConsonantAssimilation.Progressive));

		assertEquals("Sillamyeon", hangulRomanizer.romanize("신라면", RomanizedCharacter.ConsonantAssimilation.Regressive));
		assertEquals("Sinnamyeon", hangulRomanizer.romanize("신라면", RomanizedCharacter.ConsonantAssimilation.Progressive));

		assertEquals("Wonsimnyeok", hangulRomanizer.romanize("원심력", RomanizedCharacter.ConsonantAssimilation.Regressive));
		assertEquals("Wonsimnyeok", hangulRomanizer.romanize("원심력", RomanizedCharacter.ConsonantAssimilation.Progressive));

		assertEquals("Mangna", hangulRomanizer.romanize("망라", RomanizedCharacter.ConsonantAssimilation.Regressive));
		assertEquals("Mangna", hangulRomanizer.romanize("망라", RomanizedCharacter.ConsonantAssimilation.Progressive));
	}

	@Test
	@DisplayName("단어 타입별 변환 테스트")
	void testByType() {
		// Substantives (체언)
		assertEquals("Gaka", hangulRomanizer.romanize("각하", RomanizedCharacter.Type.Typical));
		assertEquals("Gakha", hangulRomanizer.romanize("각하", RomanizedCharacter.Type.Substantives));
		assertEquals("Matang", hangulRomanizer.romanize("맏항", RomanizedCharacter.Type.Typical));
		assertEquals("Mathang", hangulRomanizer.romanize("맏항", RomanizedCharacter.Type.Substantives));
		assertEquals("Beopak", hangulRomanizer.romanize("법학", RomanizedCharacter.Type.Typical));
		assertEquals("Beophak", hangulRomanizer.romanize("법학", RomanizedCharacter.Type.Substantives));

		// Compound (합성어)
		assertEquals("Saegyeonpil", hangulRomanizer.romanize("색연필", RomanizedCharacter.Type.Typical));
		assertEquals("Saengnyeonpil", hangulRomanizer.romanize("색연필", RomanizedCharacter.Type.Compound));
		assertEquals("Kong-yeot", hangulRomanizer.romanize("콩엿", RomanizedCharacter.Type.Typical));
		assertEquals("Kongnyeot", hangulRomanizer.romanize("콩엿", RomanizedCharacter.Type.Compound));

		// District (지명)
		assertEquals("Jongno2Ga", hangulRomanizer.romanize("종로2가", RomanizedCharacter.Type.Typical));
		assertEquals("Jongno 2-ga", hangulRomanizer.romanize("종로2가", RomanizedCharacter.Type.District));
		assertEquals("Seongnamdaero2Beon-gil", hangulRomanizer.romanize("성남대로2번길", RomanizedCharacter.Type.Typical));
		assertEquals("Seongnam-daero 2beon-gil", hangulRomanizer.romanize("성남대로2번길", RomanizedCharacter.Type.District));

		// Name (인명)
		assertEquals("Ijieun", hangulRomanizer.romanize("이지은", RomanizedCharacter.Type.Typical));
		assertEquals("I Jieun", hangulRomanizer.romanize("이지은", RomanizedCharacter.Type.Name));
		assertEquals("Jegalgongmyeong", hangulRomanizer.romanize("제갈공명", RomanizedCharacter.Type.Typical));
		assertEquals("Jegal Gongmyeong", hangulRomanizer.romanize("제갈공명", RomanizedCharacter.Type.Name));
		assertEquals("Bakwayobi", hangulRomanizer.romanize("박화요비", RomanizedCharacter.Type.Typical));
		assertEquals("Bak Hwayobi", hangulRomanizer.romanize("박화요비", RomanizedCharacter.Type.Name));
	}

	@Test
	@DisplayName("기타 변환 테스트")
	void testMisc() {
		assertEquals("Anta", hangulRomanizer.romanize("않다"));
		assertEquals("Han-geul", hangulRomanizer.romanize("한글"));
		assertEquals("Daehanmin-guk", hangulRomanizer.romanize("대한민국"));
	}

	@Test
	@DisplayName("기본 romanize() 메소드 테스트")
	void testBasicRomanize() {
		assertEquals("Annyeonghaseyo", hangulRomanizer.romanize("안녕하세요"));
		assertEquals("Saranghae", hangulRomanizer.romanize("사랑해"));
		assertEquals("Gamsahamnida", hangulRomanizer.romanize("감사합니다"));
	}

	@Test
	@DisplayName("Type만 지정하는 romanize() 메소드 테스트")
	void testRomanizeWithType() {
		assertEquals("Bak Jimin", hangulRomanizer.romanize("박지민", RomanizedCharacter.Type.Name));
		assertEquals("Gangnam-gu", hangulRomanizer.romanize("강남구", RomanizedCharacter.Type.District));
	}

	@Test
	@DisplayName("ConsonantAssimilation만 지정하는 romanize() 메소드 테스트")
	void testRomanizeWithConsonantAssimilation() {
		assertEquals("Sillamyeon", hangulRomanizer.romanize("신라면", RomanizedCharacter.ConsonantAssimilation.Regressive));
		assertEquals("Sinnamyeon", hangulRomanizer.romanize("신라면", RomanizedCharacter.ConsonantAssimilation.Progressive));
	}

	@Test
	@DisplayName("숫자와 한글 혼합 테스트")
	void testMixedWithNumbers() {
		assertEquals("Songpa-gu", hangulRomanizer.romanize("송파구", RomanizedCharacter.Type.District));
		assertEquals("Sejong-daero", hangulRomanizer.romanize("세종대로", RomanizedCharacter.Type.District));
	}

	@Test
	@DisplayName("영문자와 한글 혼합 테스트")
	void testMixedWithEnglish() {
		assertEquals("Hello Han-geul", hangulRomanizer.romanize("Hello 한글"));
		assertEquals("JavaWa Han-geul", hangulRomanizer.romanize("Java와 한글"));
	}

	@Test
	@DisplayName("특수문자 포함 테스트")
	void testWithSpecialCharacters() {
		assertEquals("Annyeong! Ban-gawo.", hangulRomanizer.romanize("안녕! 반가워."));
		assertEquals("Han-geul (韓Geul)", hangulRomanizer.romanize("한글 (韓글)"));
	}

	@Test
	@DisplayName("NameTypical 타입 테스트")
	void testNameTypical() {
		assertEquals("Park Bogeom", hangulRomanizer.romanize("박보검", RomanizedCharacter.Type.NameTypical));
		assertEquals("Kim Minjae", hangulRomanizer.romanize("김민재", RomanizedCharacter.Type.NameTypical));
		assertEquals("Lee Jieun", hangulRomanizer.romanize("이지은", RomanizedCharacter.Type.NameTypical));
	}
}
