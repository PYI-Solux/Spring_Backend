package com.solux.pyi.pyiplanyouridea.folders.dto;

import com.solux.pyi.pyiplanyouridea.folders.domain.Folders;

import java.time.LocalDateTime;

public class FoldersResponseDto {
    private Long folderUuid;
    private String folderName;
    private LocalDateTime folderCreated;

    public FoldersResponseDto(Folders entity){
        this.folderUuid = entity.getFolderUuid();
        this.folderName = entity.getFolderName();
        this.folderCreated = entity.getFolderCreated();
    }
}
