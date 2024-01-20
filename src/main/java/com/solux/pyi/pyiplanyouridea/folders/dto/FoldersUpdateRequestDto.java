package com.solux.pyi.pyiplanyouridea.folders.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FoldersUpdateRequestDto {
    private String folder_name;

    @Builder
    public FoldersUpdateRequestDto(String folder_name){
        this.folder_name = folder_name;
    }

}
