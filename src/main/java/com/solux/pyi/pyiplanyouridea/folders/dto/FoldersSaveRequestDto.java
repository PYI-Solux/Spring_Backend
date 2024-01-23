package com.solux.pyi.pyiplanyouridea.folders.dto;

import com.solux.pyi.pyiplanyouridea.folders.domain.Folders;
import com.solux.pyi.pyiplanyouridea.users.domain.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class FoldersSaveRequestDto {
    private String folderName;
    private LocalDateTime folderCreated;
    private Users userUuid;

    @Builder
    public FoldersSaveRequestDto(Users userUuid, String folderName, LocalDateTime folderCreated){
        this.userUuid = userUuid;
        this.folderName = folderName;
        this.folderCreated = folderCreated;
    }

    public Folders toEntity(Users userUuid){
        return Folders.builder()
                .userUuid(userUuid)
                .folderName(folderName)
                .folderCreated(folderCreated)
                .build();
    }
}
