package io.github.hyeonqz.eshangul.spring;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * ES-Hangul Spring Boot 자동 구성
 */
@AutoConfiguration
public class HangulAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public HangulService hangulService() {
        return new HangulService();
    }
}
