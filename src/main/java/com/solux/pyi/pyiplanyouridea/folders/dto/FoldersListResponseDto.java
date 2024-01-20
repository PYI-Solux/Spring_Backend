package com.solux.pyi.pyiplanyouridea.folders.dto;

import com.solux.pyi.pyiplanyouridea.folders.domain.Folders;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Getter
public class FoldersListResponseDto {
    private Long folderUuid;
    private String folderName;
    private LocalDateTime folderCreated;

    public FoldersListResponseDto(Folders entity){
        this.folderUuid = entity.getFolderUuid();
        this.folderName = entity.getFolderName();
        this.folderCreated = entity.getFolderCreated();
    }

}
