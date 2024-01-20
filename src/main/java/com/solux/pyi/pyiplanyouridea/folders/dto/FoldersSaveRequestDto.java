package com.solux.pyi.pyiplanyouridea.folders.dto;

import com.solux.pyi.pyiplanyouridea.folders.domain.Folders;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class FoldersSaveRequestDto {
    private String folderName;
    private LocalDateTime folderCreated;

    @Builder
    public FoldersSaveRequestDto(String folderName, LocalDateTime folderCreated){

        this.folderName = folderName;
        this.folderCreated = folderCreated;
    }

    public Folders toEntity(){
        return Folders.builder()
                //.userUuid(userUuid)
                .folderName(folderName)
                .folderCreated(folderCreated)
                .build();
    }
}
