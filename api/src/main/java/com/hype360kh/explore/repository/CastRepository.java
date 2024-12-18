package com.hype360kh.explore.repository;

import com.hype360kh.explore.model.entity.Cast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CastRepository extends JpaRepository<Cast, String>, JpaSpecificationExecutor<Cast> {

}
