package com.solux.pyi.pyiplanyouridea.organize.domain;

import com.solux.pyi.pyiplanyouridea.memos.domain.Memos;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "organize")
public class Organize {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "organize_uuid", columnDefinition = "bigint(16)", nullable = false)
    private Long organize_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memo_uuid", columnDefinition = "bigint(16)", nullable = false)
    private Memos memoUuid;

    @Column(name = "organize_title", columnDefinition = "varchar(30)", nullable = false)
    private String organize_title;

    @Column(name = "organize_details", columnDefinition = "text", nullable = false)
    private String organize;

    @CreationTimestamp
    @Column(name = "organize_created", columnDefinition = "timestamp", nullable = false)
    private LocalDateTime organizeCreated;

    @Builder
    public Organize(Memos memoUuid, String title, String content, LocalDateTime organizeCreated){
        this.memoUuid = memoUuid;
        this.organize_title = title;
        this.organize = content;
        this.organizeCreated = organizeCreated;
    }

    public void update(String title, String content){
        this.organize_title = title;
        this.organize = content;
    }
}
