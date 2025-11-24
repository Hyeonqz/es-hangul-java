package io.github.hyeonqz.eshangul.examples;

/**
 * ES-Hangul Java 간단한 사용 예시
 * 
 * 이 예시는 컴파일 없이 사용법을 보여주는 문서용 코드입니다.
 */
public class SimpleExample {

    public static void main(String[] args) {
        System.out.println("=== ES-Hangul Java 사용 예시 ===");
        
        // 실제 사용 시에는 다음과 같이 import 합니다:
        // import io.github.hyeonqz.eshangul.core.*;
        // import io.github.hyeonqz.eshangul.validator.HangulValidator;
        
        System.out.println("1. 한글 조립 예시:");
        System.out.println("   HangulAssembler.assemble(Arrays.asList(\"ㅇ\", \"ㅏ\", \"ㅂ\", \"ㅓ\", \"ㅈ\", \"ㅣ\"))");
        System.out.println("   결과: 아버지");
        
        System.out.println("\n2. 한글 분해 예시:");
        System.out.println("   HangulDisassembler.disassemble(\"안녕하세요\")");
        System.out.println("   결과: ㅇㅏㄴㄴㅕㅇㅎㅏㅅㅔㅇㅛ");
        
        System.out.println("\n3. 조사 처리 예시:");
        System.out.println("   HangulJosa.josa(\"사과\", HangulJosa.JosaOption.이_가)");
        System.out.println("   결과: 사과가");
        
        System.out.println("\n4. 받침 처리 예시:");
        System.out.println("   HangulBatchim.hasBatchim(\"값\")");
        System.out.println("   결과: true");
        
        System.out.println("\n5. 한글 검증 예시:");
        System.out.println("   HangulValidator.isHangul(\"안녕\")");
        System.out.println("   결과: true");
        
        System.out.println("\n더 자세한 예시는 다른 Example 클래스들을 참고하세요.");
    }
}
