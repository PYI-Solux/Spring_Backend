package com.solux.pyi.pyiplanyouridea.keywords.service;

import com.solux.pyi.pyiplanyouridea.keywords.domain.Keywords;
import com.solux.pyi.pyiplanyouridea.keywords.repository.KeywordsRepository;
import com.solux.pyi.pyiplanyouridea.keywords.dto.KeywordsResponseDto;
import com.solux.pyi.pyiplanyouridea.keywords.dto.KeywordsSaveRequestDto;
import com.solux.pyi.pyiplanyouridea.keywords.dto.KeywordsUpdateRequestDto;
import com.solux.pyi.pyiplanyouridea.memos.domain.Memos;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class KeywordsService {
    private final KeywordsRepository keywordsRepository;

    @Transactional
    public Long save(Memos memoUuid, KeywordsSaveRequestDto requestDto){
        return keywordsRepository.save(requestDto.toEntity(memoUuid)).getKeywordUuid();
    }

    @Transactional
    public Long update(Long id, KeywordsUpdateRequestDto requestDto){
        Keywords keywords = keywordsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        keywords.update(requestDto.getKeywordDetails());
        return id;
    }

    public KeywordsResponseDto findById(Long id){
        Keywords entity = keywordsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        return new KeywordsResponseDto(entity);
    }

    @Transactional
    public void delete(Long id){
        Keywords keywords = keywordsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        keywordsRepository.delete(keywords);
    }
}
