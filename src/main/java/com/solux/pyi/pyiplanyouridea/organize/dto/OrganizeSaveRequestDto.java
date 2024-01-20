package com.solux.pyi.pyiplanyouridea.organize.dto;

import com.solux.pyi.pyiplanyouridea.memos.domain.Memos;
import com.solux.pyi.pyiplanyouridea.organize.domain.Organize;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class OrganizeSaveRequestDto {
    private String organize_title;
    private Memos memoUuid;
    private String organize;
    private LocalDateTime organizeCreated;

    @Builder
    public OrganizeSaveRequestDto(Memos memoUuid, String title, String content, LocalDateTime organizeCreated){
        this.organize_title = title;
        this.organize = content;
        this.memoUuid = memoUuid;
        this.organizeCreated = organizeCreated;
    }

    public Organize toEntity(Memos memoUuid){
        return Organize.builder()
                .memoUuid(memoUuid)
                .title(organize_title)
                .content(organize)
                .organizeCreated(organizeCreated)
                .build();
    }
}
