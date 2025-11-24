# ES-Hangul Examples

이 모듈은 ES-Hangul Java 라이브러리의 다양한 사용 예시를 제공합니다.

## 예시 목록

### 1. HangulAssemblerExample
한글 조립 기능의 사용법을 보여줍니다.
- 자모를 완성된 한글로 조립
- 부분 조립된 문장 완성

### 2. HangulDisassemblerExample  
한글 분해 기능의 사용법을 보여줍니다.
- 한글을 자모로 분해
- 그룹별 분해

### 3. HangulJosaExample
조사 처리 기능의 사용법을 보여줍니다.
- 받침 유무에 따른 적절한 조사 선택
- 다양한 조사 옵션 사용

### 4. HangulBatchimExample
받침 처리 기능의 사용법을 보여줍니다.
- 받침 유무 확인
- 받침 추가/제거

### 5. HangulValidatorExample
한글 검증 기능의 사용법을 보여줍니다.
- 한글 문자 검증
- 완성된 한글 검증
- 자음/모음 검증

### 6. ComprehensiveExample
모든 기능을 종합적으로 사용하는 예시입니다.
- 실용적인 사용 사례
- 동적 문장 생성

## 실행 방법

각 예시 클래스는 독립적으로 실행 가능한 main 메서드를 포함하고 있습니다.

```bash
# Gradle을 사용한 실행
./gradlew :es-hangul-examples:run

# 또는 IDE에서 각 클래스의 main 메서드를 직접 실행
```

## 의존성

이 모듈은 `es-hangul-core` 모듈에 의존합니다.
