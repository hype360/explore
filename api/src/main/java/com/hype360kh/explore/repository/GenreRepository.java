package com.hype360kh.explore.repository;

import com.hype360kh.explore.model.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, String>, JpaSpecificationExecutor<Genre> {
}
