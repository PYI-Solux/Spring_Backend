package com.solux.pyi.pyiplanyouridea.folders.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FoldersRepository extends JpaRepository<Folders, Long> {

    @Query("SELECT f FROM Folders f")
    List<Folders> findAllDesc();
}
