package com.solux.pyi.pyiplanyouridea.folders.controller;

import com.solux.pyi.pyiplanyouridea.folders.dto.FoldersListResponseDto;
import com.solux.pyi.pyiplanyouridea.folders.dto.FoldersResponseDto;
import com.solux.pyi.pyiplanyouridea.folders.dto.FoldersSaveRequestDto;
import com.solux.pyi.pyiplanyouridea.folders.dto.FoldersUpdateRequestDto;
import com.solux.pyi.pyiplanyouridea.folders.service.FoldersService;
import com.solux.pyi.pyiplanyouridea.users.domain.Users;
import com.solux.pyi.pyiplanyouridea.users.repository.UsersRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class FoldersController {
    private final FoldersService foldersService;
    private final UsersRepository usersRepository;

    // 폴더 하나 생성
    @PostMapping("https://planyouridea/category/createfolder")
    public Long save(@PathVariable Long userUuid, @RequestBody FoldersSaveRequestDto requestDto) {
        Users users = usersRepository.getReferenceById(userUuid);
        return foldersService.save(users, requestDto); }

    // 카테고리에서 폴더 리스트 조회
    @GetMapping("https://planyouridea/category")
    public List<FoldersListResponseDto> getCategoryFoldersList(){
        return foldersService.findAllDesc();
    }

    // 메인 페이지에서 전체 폴더 리스트 조회
    @GetMapping("https://planyouridea/main")
    public List<FoldersListResponseDto> getMainpageFoldersList(){
        return foldersService.findAllDesc();
    }

    // 폴더 하나 이름 수정
    @PutMapping("https://planyouridea/category/editfoldername/{folderId}")
    public Long update(@PathVariable Long folder_id, @RequestBody FoldersUpdateRequestDto requestDto){
        return foldersService.update(folder_id, requestDto);
    }

    // 폴더 하나 삭제
    @DeleteMapping("https://planyouridea/deletefolder/{folderId}")
    public Long delete(@PathVariable Long folder_id){
        foldersService.delete(folder_id);
        return folder_id;
    }
}
