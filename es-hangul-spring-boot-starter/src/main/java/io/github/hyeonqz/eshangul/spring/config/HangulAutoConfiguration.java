package io.github.hyeonqz.eshangul.spring.config;

import io.github.hyeonqz.eshangul.spring.service.HangulService;
import io.github.hyeonqz.eshangul.spring.service.RomanizeService;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * ES-Hangul Spring Boot 자동 구성
 * <p>
 * 한글 처리 및 로마자 변환 서비스를 자동으로 Bean으로 등록합니다.
 * </p>
 *
 * <h3>Register Bean</h3>
 * <ul>
 *   <li>{@link HangulService}: 한글 조립, 분해, 조사, 받침, 검증 기능</li>
 *   <li>{@link RomanizeService}: 한글 로마자 변환 기능</li>
 * </ul>
 *
 * @see HangulService
 * @see RomanizeService
 */
@AutoConfiguration
public class HangulAutoConfiguration {

    /**
     * 한글 처리 서비스 Bean을 등록합니다.
     * <p>
     * 사용자가 직접 Bean을 등록하지 않은 경우에만 자동으로 등록됩니다.
     * </p>
     *
     * @return HangulService 인스턴스
     */
    @Bean
    @ConditionalOnMissingBean
    public HangulService hangulService() {
        return new HangulService();
    }

    /**
     * 한글 로마자 변환 서비스 Bean을 등록합니다.
     * <p>
     * 사용자가 직접 Bean을 등록하지 않은 경우에만 자동으로 등록됩니다.
     * </p>
     *
     * @return RomanizeService 인스턴스
     */
    @Bean
    @ConditionalOnMissingBean
    public RomanizeService romanizeService() {
        return new RomanizeService();
    }
}
