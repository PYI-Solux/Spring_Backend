package com.solux.pyi.pyiplanyouridea.organize.dto;

import com.solux.pyi.pyiplanyouridea.memos.domain.Memos;
import com.solux.pyi.pyiplanyouridea.organize.domain.Organize;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class OrganizeResponseDto {

    private Long organize_id;
    private Memos memoUuid;
    private String organize_title;
    private String organize;
    private LocalDateTime organizeCreated;

    public OrganizeResponseDto(Organize entity){
        this.organize_id = entity.getOrganize_id();
        this.memoUuid = entity.getMemoUuid();
        this.organize_title = entity.getOrganize_title();
        this.organize = entity.getOrganize();
        this.organizeCreated = entity.getOrganizeCreated();
    }
}
