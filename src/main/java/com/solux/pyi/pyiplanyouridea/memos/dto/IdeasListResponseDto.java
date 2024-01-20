package com.solux.pyi.pyiplanyouridea.memos.dto;

import com.solux.pyi.pyiplanyouridea.keywords.domain.Keywords;
import com.solux.pyi.pyiplanyouridea.memos.domain.Memos;
import com.solux.pyi.pyiplanyouridea.organize.domain.Organize;
import com.solux.pyi.pyiplanyouridea.todos.domain.Todos;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

// 아이디어 구체화 및 실현 조회
@Getter
// - 클래스 내 모든 필드의 Getter 메소드를 자동생성
public class IdeasListResponseDto {

    private Long memoUuid;
    //private List<Keywords> keywords;
    private List<String> keywordDetails;
    private List<LocalDateTime> keywordCreated;
    private List<LocalDateTime> todoDate;
    private List<String> todoDetails;
    private List<String> organizeTitle;
    private List<String> organizeDetails;
    private List<LocalDateTime> organizeCreated;
    private String reviewTitle;
    private String reviewDetails;
    private LocalDateTime reviewCreated;
    private BigDecimal starDetails;

    public IdeasListResponseDto(Memos entity) {
        this.memoUuid = entity.getMemoUuid();
        this.keywordDetails = entity.getKeywords().stream()
                .map(Keywords::getKeywordDetails)
                .collect(Collectors.toList());
        this.keywordCreated = entity.getKeywords().stream()
                .map(Keywords::getKeywordCreated)
                .collect(Collectors.toList());
        this.todoDate = entity.getTodos().stream()
                .map(Todos::getTodoDate)
                .collect(Collectors.toList());
        this.todoDetails = entity.getTodos().stream()
                .map(Todos::getTodoDetails)
                .collect(Collectors.toList());
        this.organizeTitle = entity.getOrganize().stream()
                .map(Organize::getOrganizeTitle)
                .collect(Collectors.toList());
        this.organizeDetails = entity.getOrganize().stream()
                .map(Organize::getOrganizeDetails)
                .collect(Collectors.toList());
        this.organizeCreated = entity.getOrganize().stream()
                .map(Organize::getOrganizeCreated)
                .collect(Collectors.toList());
        this.reviewTitle = entity.getReview().getReviewTitle();
        this.reviewDetails = entity.getReview().getReviewDetails();
        this.reviewCreated = entity.getReview().getReviewCreated();
        this.starDetails = entity.getStar().getStarDetails();
    }
}