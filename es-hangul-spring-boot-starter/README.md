# ES-Hangul Spring Boot Starter

Spring Boot 애플리케이션에서 ES-Hangul을 쉽게 사용할 수 있도록 하는 Starter입니다.

## 설치

### Gradle
```gradle
dependencies {
    implementation 'io.github.hyeonqz:es-hangul-spring-boot-starter:1.0.0'
}
```

### Maven
```xml
<dependency>
    <groupId>io.github.hyeonqz</groupId>
    <artifactId>es-hangul-spring-boot-starter</artifactId>
    <version>1.0.0</version>
</dependency>
```

## 사용법

의존성을 추가하면 `HangulService`가 자동으로 Bean으로 등록됩니다.

### Controller에서 사용

```java
@RestController
@RequestMapping("/api/hangul")
public class HangulController {

    private final HangulService hangulService;

    public HangulController(HangulService hangulService) {
        this.hangulService = hangulService;
    }

    @GetMapping("/josa")
    public String addJosa(@RequestParam String word, @RequestParam String josa) {
        HangulJosa.JosaOption option = HangulJosa.JosaOption.valueOf(josa);
        return hangulService.josa(word, option);
    }

    @GetMapping("/disassemble")
    public String disassemble(@RequestParam String text) {
        return hangulService.disassemble(text);
    }

    @PostMapping("/assemble")
    public String assemble(@RequestBody List<String> fragments) {
        return hangulService.assemble(fragments);
    }
}
```

### Service에서 사용

```java
@Service
public class MessageService {

    private final HangulService hangulService;

    public MessageService(HangulService hangulService) {
        this.hangulService = hangulService;
    }

    public String createMessage(String name, String action) {
        String nameWithJosa = hangulService.josa(name, HangulJosa.JosaOption.이_가);
        String actionWithJosa = hangulService.josa(action, HangulJosa.JosaOption.을_를);
        return nameWithJosa + " " + actionWithJosa + " 합니다.";
    }
}
```

## 제공되는 기능

`HangulService`는 다음 메서드들을 제공합니다:

### 조립
- `assemble(List<String> fragments)`: 자모를 한글로 조립

### 분해
- `disassemble(String str)`: 한글을 자모로 분해
- `disassembleToGroups(String str)`: 한글을 그룹별로 분해

### 조사
- `josa(String word, JosaOption option)`: 적절한 조사 선택

### 받침
- `hasBatchim(String str)`: 받침 유무 확인
- `hasBatchim(String str, BatchimType type)`: 받침 타입별 확인

### 검증
- `canBeChoseong(String character)`: 초성 가능 여부
- `canBeJungseong(String character)`: 중성 가능 여부
- `canBeJongseong(String character)`: 종성 가능 여부

## 자동 구성

이 Starter는 Spring Boot의 자동 구성 기능을 사용합니다. 별도의 설정 없이 의존성만 추가하면 자동으로 `HangulService` Bean이 등록됩니다.

커스텀 구성이 필요한 경우 직접 Bean을 정의할 수 있습니다:

```java
@Configuration
public class CustomHangulConfig {

    @Bean
    public HangulService customHangulService() {
        return new HangulService();
    }
}
```
