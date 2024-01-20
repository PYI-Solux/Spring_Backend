package com.solux.pyi.pyiplanyouridea.folders.service;

import com.solux.pyi.pyiplanyouridea.folders.domain.Folders;
import com.solux.pyi.pyiplanyouridea.folders.domain.FoldersRepository;
import com.solux.pyi.pyiplanyouridea.folders.dto.FoldersListResponseDto;
import com.solux.pyi.pyiplanyouridea.folders.dto.FoldersResponseDto;
import com.solux.pyi.pyiplanyouridea.folders.dto.FoldersSaveRequestDto;
import com.solux.pyi.pyiplanyouridea.folders.dto.FoldersUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FoldersService {
    private final FoldersRepository foldersRepository;

    // 폴더 하나 생성
    @Transactional
    public Long save(FoldersSaveRequestDto requestDto){
        return foldersRepository.save(requestDto.toEntity()).getFolderUuid();
    }

    // 폴더 하나 이름 수정
    @Transactional
    public Long update(Long id, FoldersUpdateRequestDto requestDto){
        Folders folders = foldersRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        folders.update(requestDto.getFolder_name());
        return id;
    }

    public FoldersResponseDto findById(Long id){
        Folders entity = foldersRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        return new FoldersResponseDto(entity);
    }

    @Transactional
    public void delete(Long id){
        Folders folders = foldersRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        foldersRepository.delete(folders);
    }

    // 폴더 리스트 조회
    @Transactional(readOnly = true)
    public List<FoldersListResponseDto> findAllDesc(){
        return foldersRepository.findAllDesc().stream()
                .map(folders -> new FoldersListResponseDto(folders))
                .collect(Collectors.toList());
    }
}
