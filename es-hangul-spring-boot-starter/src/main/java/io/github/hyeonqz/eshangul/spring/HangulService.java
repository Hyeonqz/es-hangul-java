package io.github.hyeonqz.eshangul.spring;

import io.github.hyeonqz.eshangul.core.*;
import io.github.hyeonqz.eshangul.validator.HangulValidator;

import java.util.List;

/**
 * 한글 처리를 위한 통합 서비스
 */
public class HangulService {

    // 조립
    public String assemble(List<String> fragments) {
        return HangulAssembler.assemble(fragments);
    }

    // 분해
    public String disassemble(String str) {
        return HangulDisassembler.disassemble(str);
    }

    public List<List<String>> disassembleToGroups(String str) {
        return HangulDisassembler.disassembleToGroups(str);
    }

    // 조사
    public String josa(String word, HangulJosa.JosaOption josaOption) {
        return HangulJosa.josa(word, josaOption);
    }

    // 받침
    public boolean hasBatchim(String str) {
        return HangulBatchim.hasBatchim(str);
    }

    public boolean hasBatchim(String str, HangulBatchim.BatchimType type) {
        return HangulBatchim.hasBatchim(str, type);
    }

    // 검증
    public boolean canBeChoseong(String character) {
        return HangulValidator.canBeChoseong(character);
    }

    public boolean canBeJungseong(String character) {
        return HangulValidator.canBeJungseong(character);
    }

    public boolean canBeJongseong(String character) {
        return HangulValidator.canBeJongseong(character);
    }
}
