package com.solux.pyi.pyiplanyouridea.memos.repository;

import com.solux.pyi.pyiplanyouridea.folders.domain.Folders;
import com.solux.pyi.pyiplanyouridea.memos.domain.Memos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

// 2

// Domain Model
// (domain services, entities, and value objects)
// > Repository Layer
// (repository interfaces and their implementations)
// - Database와 같이 데이터 저장소에 접근하는 영역이다.
// - 기존 개발자들은 Dao(Data Access Object) 영역으로 이해하면 쉽다.

// Domain에서 비지니스 처리를 담당해야 한다.

// Repository는 Dao라고 불리는 DB Layer 접근자
// JPA에서는 Repository라고 부르며 인터페이스로 생성한다.
// 단순히 인터페이스를 생성 후,
// JpaRepository<Entity 클래스, PK 타입>를 상속하면
// 기본적인 CRUD 메소드가 자동으로 생성된다.
// @Repository를 추가할 필요도 없다.
// 주의할 점은 Entity 클래스와
// 기본 Entity Repository는 함께 위치해야 하는 점이다.
// 둘은 아주 밀접한 관계이고,
// Entity 클래스는 기본 Repository 없이는
// 제대로 역할을 할 수가 없다.
// 나중에 프로젝트 규모가 커져
// 도메인별로 프로젝트를 분리해야 한다면
// 이때 Entity 클래스와 기본 Repository는
// 함께 움직여야 하므로 도메인 패키지에서 함께 관리한다.

// Posts 클래스로 Database를 접근하게 해줄 JpaRepository
public interface MemosRepository extends JpaRepository<Memos, Long> {

    // 퀵메모 리스트 전체 조회
    @Query("SELECT m FROM Memos m")
    // SpringDataJpa에서 제공하지 않는 메소드는
    // 위처럼 @Query를 사용해 쿼리로 작성해도 된다.
    // 실제로 위 코드는 SpringDataJpa에서 제공하는
    // 기본 메소드만으로 해결할 수 있다.
    // 다만 @Query가 훨씬 가독성이 좋으니 선택해서 사용하면 된다.
    List<Memos> findAllDesc();

    // folderUuid에 해당하는 퀵메모 목록 조회
    List<Memos> findMemosByFolderUuid(@Param("folderUuid") Folders folderId);
    //@Query("SELECT m FROM Memos m JOIN FETCH m.folderUuid WHERE m.folderUuid.folderUuid = :folderUuid")
    //List<Memos> findMemosByFolderUuid(@Param("folderUuid") Long folderUuid);

}