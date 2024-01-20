package com.solux.pyi.pyiplanyouridea.organize.service;

import com.solux.pyi.pyiplanyouridea.memos.domain.Memos;
import com.solux.pyi.pyiplanyouridea.organize.domain.Organize;
import com.solux.pyi.pyiplanyouridea.organize.domain.OrganizeRepository;
import com.solux.pyi.pyiplanyouridea.organize.dto.OrganizeResponseDto;
import com.solux.pyi.pyiplanyouridea.organize.dto.OrganizeSaveRequestDto;
import com.solux.pyi.pyiplanyouridea.organize.dto.OrganizeUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class OrganizeService {
    private final OrganizeRepository organizeRepository;

    @Transactional
    public Long save(Memos memoUuid, OrganizeSaveRequestDto requestDto){
        return organizeRepository.save(requestDto.toEntity(memoUuid)).getOrganize_id();
    }

    @Transactional
    public Long update(Long id, OrganizeUpdateRequestDto requestDto){
        Organize organize = organizeRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        organize.update(requestDto.getOrganize_title(), requestDto.getOrganize());
        return id;
    }

    public OrganizeResponseDto findById(Long id){
        Organize entity = organizeRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        return new OrganizeResponseDto(entity);
    }

    @Transactional
    public void delete(Long id){
        Organize organize = organizeRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        organizeRepository.delete(organize);
    }
}
