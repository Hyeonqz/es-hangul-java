package io.github.hyeonqz.eshangul.spring;

import io.github.hyeonqz.eshangul.core.HangulJosa;
import io.github.hyeonqz.eshangul.spring.config.HangulAutoConfiguration;
import io.github.hyeonqz.eshangul.spring.service.HangulService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = HangulAutoConfiguration.class)
class HangulAutoConfigurationTest {

    @Autowired
    private HangulService hangulService;

    @Test
    void hangulServiceShouldBeAutoConfigured() {
        assertNotNull(hangulService);
    }

    @Test
    void assembleShouldWork() {
        String result = hangulService.assemble(Arrays.asList("ㅎ", "ㅏ", "ㄴ", "ㄱ", "ㅡ", "ㄹ"));
        assertEquals("한글", result);
    }

    @Test
    void disassembleShouldWork() {
        String result = hangulService.disassemble("한글");
        assertEquals("ㅎㅏㄴㄱㅡㄹ", result);
    }

    @Test
    void josaShouldWork() {
        String result = hangulService.josa("사과", HangulJosa.JosaOption.이_가);
        assertEquals("사과가", result);
    }

    @Test
    void hasBatchimShouldWork() {
        assertTrue(hangulService.hasBatchim("값"));
        assertFalse(hangulService.hasBatchim("토"));
    }
}
