package com.solux.pyi.pyiplanyouridea.keywords.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KeywordsUpdateRequestDto {
    private String keywords;

    @Builder
    public KeywordsUpdateRequestDto(String keywords){
        this.keywords = keywords;
    }
}
