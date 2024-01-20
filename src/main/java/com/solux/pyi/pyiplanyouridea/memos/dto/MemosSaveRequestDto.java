package com.solux.pyi.pyiplanyouridea.memos.dto;

import com.solux.pyi.pyiplanyouridea.folders.domain.Folders;
import com.solux.pyi.pyiplanyouridea.memos.domain.Memos;
import com.solux.pyi.pyiplanyouridea.users.domain.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// Request 요청을 받을 Dto

// Dtos
// - Dto(Data Transger Object)는 계층 간에 데이터 교환을 위한
// 객체를 이야기하며 Dtos는 이들의 영역을 얘기한다.
// - 예를 들어 뷰 템플릿 엔진에서 사용될 객체나 Repository Layer에서
// 결과로 넘겨준 객체 등이 이들을 이야기한다.

// Controller와 Service에 사용할 Dto 클래스
// Entity 클래스와 거의 유사한 형태임에도 Dto 클래스를 추가로 생성하였다.
// 하지만, 절대로 Entity 클래스를
// Request/Response 클래스로 사용해서는 안된다.
// Entity 클래스는 데이터베이스와 맞닿은 핵심 클래스이다.
// Entity 클래스를 기준으로 테이블이 생성되고, 스키마가 변경된다.
// 화면 변경은 아주 사소한 기능 변경인데,
// 이를 위해 테이블과 연결된 Entity 클래스를 변경하는 것은
// 너무 큰 변경이다.
// 수많은 서비스 클래스나 비즈니스 로직들이
// Entity 클래스를 기준으로 동작한다.
// Entity 클래스가 변경되면 여러 클래스에 영향을 끼치지만,
// Request나 Response용 Dto는 View를 위한 클래스라
// 정말 자주 변경이 필요하다.
// View Layer(Dto)와 DB Layer(Entity)의
// 역할 분리를 철저하게 하는 것이 좋다.
// 실제로 Controller에서 결과값으로
// 여러 테이블에서 조인해서 줘야 할 경우가 빈번하므로
// Entity 클래스만으로 표현하기가 어려운 경우가 많다.
// 꼭 Entity 클래스와 Controller에서 쓸 Dto는 분리해서 사용해야 한다.

// 퀵메모 저장
@Getter
// - 클래스 내 모든 필드의 Getter 메소드를 자동생성
@NoArgsConstructor
// - 기본 생성자 자동 추가
// - public Memos() {} 와 같은 효과
public class MemosSaveRequestDto {
    //private Users userUuid;
    private Folders folderUuid;
    private String memoTitle;
    private String memoDetails;
    private LocalDateTime memoCreated;

    @Builder
    // - 해당 클래스의 빌더 패턴 클래스를 생성
    // - 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    // 생성자나 빌더나 생성 시점에 값을 채워주는 역할은 똑같다.
    // 다만, 생성자의 경우 지금 채워야 할 필드가 무엇인지 명확히 지정할 수가 없다.
    // 생성자에서는 매개변수의 위치를 변경해도 코드를 실행하기 전까지는 문제를 찾을 수 없다.
    // 하지만 빌더를 사용하게 되면 어느 필드에 어떤 값을 채워야 할지 명확하게 인지할 수 있다.
    //public MemosSaveRequestDto(Users userUuid, Folders folderUuid, String memoTitle, String memoDetails, LocalDateTime memoCreated) {
    public MemosSaveRequestDto(Folders folderUuid, String memoTitle, String memoDetails, LocalDateTime memoCreated) {
        //this.userUuid = userUuid;
        this.folderUuid = folderUuid;
        this.memoTitle = memoTitle;
        this.memoDetails = memoDetails;
        this.memoCreated = memoCreated;
    }

    public Memos toEntity(Folders folderUuid) {
        return Memos.builder()
                //.userUuid(userUuid)
                .folderUuid(folderUuid)
                .memoTitle(memoTitle)
                .memoDetails(memoDetails)
                .memoCreated(memoCreated)
                .build();
    }
}