# ES-Hangul Java

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Java Version](https://img.shields.io/badge/Java-17%2B-blue.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.0%2B-brightgreen.svg)](https://spring.io/projects/spring-boot)

> A Java/Spring Boot port of [Toss's es-hangul](https://github.com/toss/es-hangul)  
> Modern Korean text processing library with intuitive API

## About

This library is a **Java implementation** inspired by Toss's excellent [es-hangul](https://github.com/toss/es-hangul) JavaScript library.
We aim to bring the same powerful Korean text processing capabilities to the Java ecosystem.

### Relationship to Original Project

- **Original Library**: [es-hangul](https://github.com/toss/es-hangul) by [Toss](https://github.com/toss)
- **Original License**: MIT
- **This Project**: Independent Java implementation maintaining API compatibility where possible
- **Not Affiliated**: This is an independent community project and is not officially affiliated with Toss

## Features

- **한글 조립 (Hangul Assembly)**: 자모를 완성된 한글로 조립
- **한글 분해 (Hangul Disassembly)**: 한글을 자모 단위로 분해
- **조사 처리 (Josa Processing)**: 받침 유무에 따른 적절한 조사 선택
- **받침 처리 (Batchim Processing)**: 받침 추가, 제거, 확인
- **한글 검증 (Hangul Validation)**: 한글 문자 및 완성도 검증
- **초성/중성/종성 처리**: 개별 자모 요소 처리
- **한글 로마자 변환 (Hangul Romanization)**: 국립국어원 로마자 표기법 기반 한글→영문 변환

## Installation

### Core Library (Gradle)
```gradle
dependencies {
    implementation 'io.github.hyeonqz:es-hangul-core:1.0.0'
}
```

### Spring Boot Starter (Gradle)
```gradle
dependencies {
    implementation 'io.github.hyeonqz:es-hangul-spring-boot-starter:1.0.0'
}
```

### Maven
```xml
<!-- Core Library -->
<dependency>
    <groupId>io.github.hyeonqz</groupId>
    <artifactId>es-hangul-core</artifactId>
    <version>1.0.0</version>
</dependency>

<!-- Spring Boot Starter -->
<dependency>
    <groupId>io.github.hyeonqz</groupId>
    <artifactId>es-hangul-spring-boot-starter</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Quick Start

### For Spring Boot Applications

Spring Boot Starter를 사용하면 `HangulService`와 `RomanizeService`가 자동으로 Bean으로 등록됩니다.

```java
@RestController
public class HangulController {

    private final HangulService hangulService;
    private final RomanizeService romanizeService;

    public HangulController(HangulService hangulService, RomanizeService romanizeService) {
        this.hangulService = hangulService;
        this.romanizeService = romanizeService;
    }

    @GetMapping("/josa")
    public String addJosa(@RequestParam String word) {
        return hangulService.josa(word, HangulJosa.JosaOption.이_가);
    }

    @GetMapping("/romanize")
    public String romanize(@RequestParam String hangul) {
        return romanizeService.romanize(hangul);
    }

    @GetMapping("/romanize/name")
    public String romanizeName(@RequestParam String name) {
        return romanizeService.romanizeNameTypical(name);
    }

    @GetMapping("/romanize/district")
    public String romanizeDistrict(@RequestParam String district) {
        return romanizeService.romanizeDistrict(district);
    }
}
```

### For Plain Java Applications
    <groupId>io.github.hyeonqz</groupId>
    <artifactId>es-hangul-core</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Quick Start

### 한글 조립 (Assembly)
```java
import io.github.hyeonqz.eshangul.core.HangulAssembler;
import java.util.Arrays;

// 자모를 한글로 조립
String result = HangulAssembler.assemble(Arrays.asList("ㅇ", "ㅏ", "ㅂ", "ㅓ", "ㅈ", "ㅣ"));
System.out.println(result); // "아버지"

// 부분 조립된 문장 완성
String sentence = HangulAssembler.assemble(Arrays.asList("아버지가", " ", "방ㅇ", "ㅔ ", "들ㅇ", "ㅓ갑니다"));
System.out.println(sentence); // "아버지가 방에 들어갑니다"
```

### 한글 분해 (Disassembly)
```java
import io.github.hyeonqz.eshangul.core.HangulDisassembler;

// 한글을 자모로 분해
String disassembled = HangulDisassembler.disassemble("안녕하세요");
System.out.println(disassembled); // "ㅇㅏㄴㄴㅕㅇㅎㅏㅅㅔㅇㅛ"

// 그룹별 분해
List<List<String>> groups = HangulDisassembler.disassembleToGroups("값");
System.out.println(groups); // [["ㄱ", "ㅏ", "ㅂ", "ㅅ"]]
```

### 조사 처리 (Josa)
```java
import io.github.hyeonqz.eshangul.core.HangulJosa;

// 받침에 따른 적절한 조사 선택
String result1 = HangulJosa.josa("사과", HangulJosa.JosaOption.이_가);
System.out.println(result1); // "사과가"

String result2 = HangulJosa.josa("책", HangulJosa.JosaOption.이_가);
System.out.println(result2); // "책이"

String result3 = HangulJosa.josa("물", HangulJosa.JosaOption.을_를);
System.out.println(result3); // "물을"
```

### 받침 처리 (Batchim)
```java
import io.github.hyeonqz.eshangul.core.HangulBatchim;

// 받침 유무 확인
boolean hasBatchim = HangulBatchim.hasBatchim("값");
System.out.println(hasBatchim); // true

// 받침 타입별 확인
boolean hasSingle = HangulBatchim.hasBatchim("갑", HangulBatchim.BatchimType.SINGLE);
boolean hasDouble = HangulBatchim.hasBatchim("값", HangulBatchim.BatchimType.DOUBLE);
System.out.println(hasSingle); // true
System.out.println(hasDouble); // true
```

### 한글 검증 (Validation)
```java
import io.github.hyeonqz.eshangul.validator.HangulValidator;

// 초성/중성/종성 가능 여부 확인
boolean canBeChoseong = HangulValidator.canBeChoseong("ㄱ");
boolean canBeJungseong = HangulValidator.canBeJungseong("ㅏ");
boolean canBeJongseong = HangulValidator.canBeJongseong("ㄱ");
System.out.println(canBeChoseong); // true
System.out.println(canBeJungseong); // true
System.out.println(canBeJongseong); // true
```

### 한글 로마자 변환 (Romanization)
```java
import io.github.hyeonqz.eshangul.romanizer.HangulRomanizer;
import io.github.hyeonqz.eshangul.romanizer.RomanizedCharacter;

// 기본 로마자 변환
String result1 = HangulRomanizer.romanize("안녕하세요");
System.out.println(result1); // "Annyeonghaseyo"

String result2 = HangulRomanizer.romanize("대한민국");
System.out.println(result2); // "Daehanmin-guk"

// 자음 동화 옵션
String regressive = HangulRomanizer.romanize("신라면", RomanizedCharacter.ConsonantAssimilation.Regressive);
System.out.println(regressive); // "Sillamyeon"

String progressive = HangulRomanizer.romanize("신라면", RomanizedCharacter.ConsonantAssimilation.Progressive);
System.out.println(progressive); // "Sinnamyeon"

// 지명 변환
String district = HangulRomanizer.romanize("강남구", RomanizedCharacter.Type.District);
System.out.println(district); // "Gangnam-gu"

// 인명 변환 (흔한 성씨 표기)
String name = HangulRomanizer.romanize("박보검", RomanizedCharacter.Type.NameTypical);
System.out.println(name); // "Park Bogeom"
```

## Examples

더 많은 예시는 [es-hangul-examples](./es-hangul-examples/src/main/java/io/github/hyeonqz/eshangul/examples/) 모듈을 참고하세요.

### 실행 가능한 예시

```bash
# 간단한 예시 실행
cd es-hangul-examples
java SimpleExample.java

# 또는 Gradle을 사용하여 실행
./gradlew :es-hangul-examples:run
```

### 예시 파일 목록

- `HangulAssemblerExample.java`: 한글 조립 기능 예시
- `HangulDisassemblerExample.java`: 한글 분해 기능 예시
- `HangulJosaExample.java`: 조사 처리 기능 예시
- `HangulBatchimExample.java`: 받침 처리 기능 예시
- `HangulValidatorExample.java`: 한글 검증 기능 예시
- `HangulRomanizerExample.java`: 한글 로마자 변환 기능 예시
- `ComprehensiveExample.java`: 종합 사용 예시
- `SimpleExample.java`: 기본 사용법 안내

## API Reference

### Core Classes

- `HangulAssembler`: 한글 조립 기능
- `HangulDisassembler`: 한글 분해 기능
- `HangulJosa`: 조사 처리 기능
- `HangulBatchim`: 받침 처리 기능
- `HangulValidator`: 한글 검증 기능
- `HangulRomanizer`: 한글 로마자 변환 기능

### Supported Josa Options

- `이_가`: 이/가
- `을_를`: 을/를
- `은_는`: 은/는
- `으로_로`: 으로/로
- `와_과`: 와/과
- `이나_나`: 이나/나

### Romanization Options

#### Consonant Assimilation (자음 동화)
- `Progressive`: 순행 동화
- `Regressive`: 역행 동화 (기본값)

#### Word Types (단어 타입)
- `Typical`: 일반 단어 (기본값)
- `Substantives`: 체언 (받침 뒤 'ㅎ'을 밝혀 적기)
- `Compound`: 합성어 (ㄴ 첨가)
- `District`: 지명 (행정구역 표기)
- `Name`: 인명 (성과 이름 분리)
- `NameTypical`: 인명 (흔한 성씨 표기법 적용)

## Credits

This project is inspired by and maintains API compatibility with:
- [es-hangul](https://github.com/toss/es-hangul) - Original JavaScript implementation by Toss team
- Special thanks to the Toss Frontend Chapter for their excellent work on Korean text processing

The romanization feature is based on:
- [korean-romanizer](https://github.com/crizin/korean-romanizer) - Korean romanization library by crizin
- Implements the National Korean Language Romanization standard

## Thanks to


## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

The original [es-hangul](https://github.com/toss/es-hangul) library is also licensed under the MIT License.