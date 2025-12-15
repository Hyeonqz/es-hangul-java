package io.github.hyeonqz.eshangul.examples;

import io.github.hyeonqz.eshangul.romanizer.HangulRomanizer;
import io.github.hyeonqz.eshangul.romanizer.RomanizedCharacter;

/**
 * Example usage of HangulRomanizer.
 * Demonstrates Korean to Roman character conversion with various options.
 */
public class HangulRomanizerExample {

	public static void main(String[] args) {
		HangulRomanizer romanizer = new HangulRomanizer();

		System.out.println("=== 한글 로마자 변환 예제 ===\n");

		// 1. 기본 변환
		System.out.println("1. 기본 변환 (Default Romanization)");
		System.out.println("   한글 -> " + romanizer.romanize("한글"));
		System.out.println("   안녕하세요 -> " + romanizer.romanize("안녕하세요"));
		System.out.println("   대한민국 -> " + romanizer.romanize("대한민국"));
		System.out.println();

		// 2. 자음 동화 옵션
		System.out.println("2. 자음 동화 옵션 (Consonant Assimilation)");
		System.out.println("   신라면 (Regressive) -> " +
			romanizer.romanize("신라면", RomanizedCharacter.ConsonantAssimilation.Regressive));
		System.out.println("   신라면 (Progressive) -> " +
			romanizer.romanize("신라면", RomanizedCharacter.ConsonantAssimilation.Progressive));
		System.out.println("   생산량 (Regressive) -> " +
			romanizer.romanize("생산량", RomanizedCharacter.ConsonantAssimilation.Regressive));
		System.out.println("   생산량 (Progressive) -> " +
			romanizer.romanize("생산량", RomanizedCharacter.ConsonantAssimilation.Progressive));
		System.out.println();

		// 3. 체언 (Substantives)
		System.out.println("3. 체언 타입 (Substantives Type)");
		System.out.println("   각하 (Typical) -> " +
			romanizer.romanize("각하", RomanizedCharacter.Type.Typical));
		System.out.println("   각하 (Substantives) -> " +
			romanizer.romanize("각하", RomanizedCharacter.Type.Substantives));
		System.out.println("   법학 (Typical) -> " +
			romanizer.romanize("법학", RomanizedCharacter.Type.Typical));
		System.out.println("   법학 (Substantives) -> " +
			romanizer.romanize("법학", RomanizedCharacter.Type.Substantives));
		System.out.println();

		// 4. 합성어 (Compound)
		System.out.println("4. 합성어 타입 (Compound Type)");
		System.out.println("   색연필 (Typical) -> " +
			romanizer.romanize("색연필", RomanizedCharacter.Type.Typical));
		System.out.println("   색연필 (Compound) -> " +
			romanizer.romanize("색연필", RomanizedCharacter.Type.Compound));
		System.out.println("   콩엿 (Typical) -> " +
			romanizer.romanize("콩엿", RomanizedCharacter.Type.Typical));
		System.out.println("   콩엿 (Compound) -> " +
			romanizer.romanize("콩엿", RomanizedCharacter.Type.Compound));
		System.out.println();

		// 5. 지명 (District)
		System.out.println("5. 지명 타입 (District Type)");
		System.out.println("   종로2가 (Typical) -> " +
			romanizer.romanize("종로2가", RomanizedCharacter.Type.Typical));
		System.out.println("   종로2가 (District) -> " +
			romanizer.romanize("종로2가", RomanizedCharacter.Type.District));
		System.out.println("   성남대로2번길 (Typical) -> " +
			romanizer.romanize("성남대로2번길", RomanizedCharacter.Type.Typical));
		System.out.println("   성남대로2번길 (District) -> " +
			romanizer.romanize("성남대로2번길", RomanizedCharacter.Type.District));
		System.out.println("   강남구 (District) -> " +
			romanizer.romanize("강남구", RomanizedCharacter.Type.District));
		System.out.println();

		// 6. 인명 (Name)
		System.out.println("6. 인명 타입 (Name Type)");
		System.out.println("   이지은 (Typical) -> " +
			romanizer.romanize("이지은", RomanizedCharacter.Type.Typical));
		System.out.println("   이지은 (Name) -> " +
			romanizer.romanize("이지은", RomanizedCharacter.Type.Name));
		System.out.println("   제갈공명 (Typical) -> " +
			romanizer.romanize("제갈공명", RomanizedCharacter.Type.Typical));
		System.out.println("   제갈공명 (Name) -> " +
			romanizer.romanize("제갈공명", RomanizedCharacter.Type.Name));
		System.out.println();

		// 7. 인명 - 흔한 성씨 표기 (NameTypical)
		System.out.println("7. 흔한 성씨 표기 (NameTypical Type)");
		System.out.println("   박보검 (Name) -> " +
			romanizer.romanize("박보검", RomanizedCharacter.Type.Name));
		System.out.println("   박보검 (NameTypical) -> " +
			romanizer.romanize("박보검", RomanizedCharacter.Type.NameTypical));
		System.out.println("   김민재 (Name) -> " +
			romanizer.romanize("김민재", RomanizedCharacter.Type.Name));
		System.out.println("   김민재 (NameTypical) -> " +
			romanizer.romanize("김민재", RomanizedCharacter.Type.NameTypical));
		System.out.println("   이순신 (Name) -> " +
			romanizer.romanize("이순신", RomanizedCharacter.Type.Name));
		System.out.println("   이순신 (NameTypical) -> " +
			romanizer.romanize("이순신", RomanizedCharacter.Type.NameTypical));
		System.out.println();

		// 8. 혼합 문자열
		System.out.println("8. 혼합 문자열 (Mixed Content)");
		System.out.println("   Hello 한글 -> " + romanizer.romanize("Hello 한글"));
		System.out.println("   Java 프로그래밍 -> " + romanizer.romanize("Java 프로그래밍"));
		System.out.println("   2024년 대한민국 -> " + romanizer.romanize("2024년 대한민국"));
		System.out.println();

		// 9. 실제 사용 예시
		System.out.println("9. 실제 사용 예시 (Real World Examples)");
		System.out.println("   서울특별시 강남구 (District) -> " +
			romanizer.romanize("서울특별시 강남구", RomanizedCharacter.Type.District));
		System.out.println("   세종대왕 (NameTypical) -> " +
			romanizer.romanize("세종대왕", RomanizedCharacter.Type.NameTypical));
		System.out.println("   삼성전자 -> " + romanizer.romanize("삼성전자"));
		System.out.println("   비빔밥 -> " + romanizer.romanize("비빔밥"));
		System.out.println("   태권도 -> " + romanizer.romanize("태권도"));
		System.out.println();

		System.out.println("=== 예제 종료 ===");
	}
}
