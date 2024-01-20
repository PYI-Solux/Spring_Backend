package com.solux.pyi.pyiplanyouridea.keywords.dto;

import com.solux.pyi.pyiplanyouridea.keywords.domain.Keywords;
import com.solux.pyi.pyiplanyouridea.memos.domain.Memos;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class KeywordsSaveRequestDto {
    private Memos memoUuid;
    private String keywords;
    private LocalDateTime keywordCreated;

    @Builder
    public KeywordsSaveRequestDto(Memos memoUuid, String keywords, LocalDateTime keywordCreated){

        this.memoUuid = memoUuid;
        this.keywords = keywords;
        this.keywordCreated = keywordCreated;
    }

    public Keywords toEntity(Memos memoUuid){
        return Keywords.builder()
                .memoUuid(memoUuid)
                .keyword(keywords)
                .keywordCreated(keywordCreated)
                .build();
    }
}
