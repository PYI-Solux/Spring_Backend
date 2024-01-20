package com.solux.pyi.pyiplanyouridea.organize.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrganizeUpdateRequestDto {
    private String organize_title;
    private String organize;

    @Builder
    public OrganizeUpdateRequestDto(String title, String content){
        this.organize_title = title;
        this.organize = content;
    }
}
