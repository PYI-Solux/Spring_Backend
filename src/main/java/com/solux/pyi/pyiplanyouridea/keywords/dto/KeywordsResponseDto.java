package com.solux.pyi.pyiplanyouridea.keywords.dto;

import com.solux.pyi.pyiplanyouridea.keywords.domain.Keywords;
import com.solux.pyi.pyiplanyouridea.memos.domain.Memos;

import java.time.LocalDateTime;

public class KeywordsResponseDto {

    private Memos memoUuid;
    private Long keywords_id;
    private String keywords;
    private LocalDateTime keywordCreated;

    public KeywordsResponseDto(Keywords entity){
        this.keywords_id = entity.getKeyword_id();
        this.memoUuid = entity.getMemoUuid();
        this.keywords = entity.getKeyword();
        this.keywordCreated = entity.getKeywordCreated();
    }
}
