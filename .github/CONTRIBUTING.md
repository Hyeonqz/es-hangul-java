# Contributing to ES-Hangul Java

es-hangul-java 프로젝트에 기여해주셔서 감사합니다! 이 문서는 프로젝트에 기여하는 방법을 안내합니다.

## 목차
- [행동 강령](#행동-강령)
- [시작하기](#시작하기)
- [개발 환경 설정](#개발-환경-설정)
- [기여 방법](#기여-방법)
- [코딩 스타일](#코딩-스타일)
- [커밋 메시지 가이드](#커밋-메시지-가이드)
- [Pull Request 프로세스](#pull-request-프로세스)
- [이슈 작성 가이드](#이슈-작성-가이드)

## 행동 강령

이 프로젝트는 모든 참여자가 존중받는 환경을 만들기 위해 노력합니다. 참여함으로써 귀하는 다음을 준수하는 것에 동의합니다:
- 존중하고 포용적인 언어 사용
- 다른 관점과 경험 존중
- 건설적인 비판을 우아하게 수용
- 커뮤니티에 가장 좋은 것에 집중
- 다른 커뮤니티 구성원에 대한 공감 표시

## 시작하기

1. **프로젝트 이해하기**
   - [README.md](../README.md)를 읽고 프로젝트의 목적과 기능을 이해합니다.
   - 원본 JavaScript 라이브러리 [toss/es-hangul](https://github.com/toss/es-hangul)을 숙지합니다.

2. **이슈 찾기**
   - [Issues 페이지](../../issues)에서 `good first issue` 또는 `help wanted` 라벨이 붙은 이슈를 찾아보세요.
   - 작업하고 싶은 이슈가 있다면 댓글로 의사를 표시해주세요.

## 개발 환경 설정

### 필수 요구사항
- JDK 21 이상
- Gradle 8.14 이상
- Git

### 프로젝트 클론 및 빌드

```bash
# 저장소 클론
git clone https://github.com/Hyeonqz/es-hangul-java.git
cd es-hangul-java

# 빌드 및 테스트
./gradlew clean build

# 특정 모듈만 테스트
./gradlew :es-hangul-core:test
```

### 프로젝트 구조
```
es-hangul-java/
├── es-hangul-core/                   # 핵심 라이브러리
├── es-hangul-examples/               # 사용 예제
└── es-hangul-spring-boot-starter/    # Spring Boot 통합
```

## 기여 방법

### 1. Fork 및 Clone
```bash
# Fork 후 본인의 저장소를 클론
git clone https://github.com/YOUR_USERNAME/es-hangul-java.git
cd es-hangul-java

# 원본 저장소를 upstream으로 추가
git remote add upstream https://github.com/Hyeonqz/es-hangul-java.git
```

### 2. 브랜치 생성
```bash
# 최신 변경사항 가져오기
git checkout master
git pull upstream master

# 새 브랜치 생성
git checkout -b feature/your-feature-name
# 또는
git checkout -b fix/your-bug-fix
```

브랜치 명명 규칙:
- `feature/기능명`: 새로운 기능 추가
- `fix/버그명`: 버그 수정
- `docs/문서명`: 문서 수정
- `refactor/대상`: 리팩토링
- `test/테스트명`: 테스트 추가/수정

### 3. 코드 작성
- 원본 JavaScript 라이브러리의 동작과 일치하도록 구현합니다.
- 테스트 코드를 반드시 작성합니다.
- 코딩 스타일 가이드를 준수합니다.

### 4. 테스트
```bash
# 모든 테스트 실행
./gradlew test

# 특정 모듈 테스트
./gradlew :es-hangul-core:test

# 테스트 커버리지 확인
./gradlew jacocoTestReport
```

### 5. 커밋
```bash
git add .
git commit -m "feat: 새로운 기능 추가"
```

### 6. Push 및 Pull Request
```bash
git push origin feature/your-feature-name
```
GitHub에서 Pull Request를 생성합니다.

## 코딩 스타일

### Java 코딩 규칙
- **네이밍**
  - 클래스: PascalCase (예: `HangulUtils`)
  - 메서드/변수: camelCase (예: `getChoseong`)
  - 상수: UPPER_SNAKE_CASE (예: `HANGUL_START_UNICODE`)
  - 패키지: 소문자 (예: `io.github.hyeonqz.eshangul`)

- **코드 구조**
  - 한 메서드는 30줄을 넘지 않도록 합니다.
  - 가독성을 위해 적절한 공백과 줄바꿈을 사용합니다.
  - 매직 넘버 대신 상수를 사용합니다.

- **주석**
  - Javadoc 스타일로 public API를 문서화합니다.
  - 복잡한 로직에는 설명 주석을 추가합니다.
  - 원본 JavaScript 라이브러리의 함수명을 주석으로 명시합니다.

```java
/**
 * 주어진 한글 문자열에서 초성을 추출합니다.
 *
 * @param text 초성을 추출할 한글 문자열
 * @return 추출된 초성 문자열
 * @throws IllegalArgumentException text가 null이거나 빈 문자열인 경우
 *
 * @see <a href="https://github.com/toss/es-hangul">Original: getChoseong</a>
 */
public static String extractChoseong(String text) {
    // 구현
}
```

### 테스트 코드
- JUnit 5를 사용합니다.
- Given-When-Then 패턴을 권장합니다.
- 테스트 메서드명은 `test_메서드명_조건_예상결과` 형식을 따릅니다.

```java
@Test
void test_extractChoseong_validHangul_returnsChoseong() {
    // Given
    String input = "한글";

    // When
    String result = HangulUtils.extractChoseong(input);

    // Then
    assertEquals("ㅎㄱ", result);
}
```

## 커밋 메시지 가이드

### 커밋 메시지 형식
```
<type>(<scope>): <subject>

<body>

<footer>
```

### Type
- `feat`: 새로운 기능 추가
- `fix`: 버그 수정
- `docs`: 문서 수정
- `style`: 코드 포맷팅, 세미콜론 누락 등 (코드 변경 없음)
- `refactor`: 코드 리팩토링
- `test`: 테스트 코드 추가/수정
- `chore`: 빌드 작업, 패키지 매니저 설정 등

### 예시
```bash
feat(core): 초성 추출 기능 추가

한글 문자열에서 초성만을 추출하는 extractChoseong 메서드를 구현했습니다.
원본 JavaScript 라이브러리의 getChoseong 함수와 동일한 동작을 합니다.

Closes #123
```

## Pull Request 프로세스

1. **PR 생성 전 체크리스트**
   - [ ] 모든 테스트가 통과하는지 확인
   - [ ] 코딩 스타일 가이드를 준수했는지 확인
   - [ ] 관련 문서를 업데이트했는지 확인
   - [ ] 커밋 메시지가 가이드를 따르는지 확인

2. **PR 작성**
   - PR 템플릿을 채워주세요.
   - 변경사항을 명확하게 설명해주세요.
   - 관련 이슈를 링크해주세요.
   - 스크린샷이나 GIF를 추가하면 더 좋습니다.

3. **코드 리뷰**
   - 메인테이너가 코드 리뷰를 진행합니다.
   - 요청된 변경사항이 있다면 수정 후 다시 푸시해주세요.
   - 리뷰어의 피드백에 성실히 응답해주세요.

4. **병합**
   - 모든 리뷰가 승인되고 테스트가 통과하면 병합됩니다.
   - Squash merge를 기본으로 사용합니다.

## 이슈 작성 가이드

### 버그 리포트
- [Bug Report 템플릿](.github/ISSUE_TEMPLATE/bug_report.md)을 사용하세요.
- 재현 가능한 최소한의 코드를 제공해주세요.
- 환경 정보(OS, Java 버전 등)를 명시해주세요.

### 기능 제안
- [Feature Request 템플릿](.github/ISSUE_TEMPLATE/feature_request.md)을 사용하세요.
- 원본 JavaScript 라이브러리의 해당 기능을 링크해주세요.
- 사용 예시 코드를 제공해주세요.

### 질문
- 제목에 [Question] 태그를 붙여주세요.
- 가능한 한 구체적으로 질문해주세요.
- 시도해본 것들을 공유해주세요.

## 라이센스

기여하신 코드는 프로젝트의 [MIT 라이센스](../LICENSE.md)를 따릅니다.

## 도움이 필요하신가요?

- 이슈에 질문을 남겨주세요.
- [Discussions](../../discussions)에서 토론해주세요.
- 메인테이너에게 연락주세요.

다시 한번 기여해주셔서 감사합니다! 🙏
