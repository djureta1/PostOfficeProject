package com.master.backend.repository;

import com.master.backend.model.GradeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GradeRepository extends JpaRepository<GradeEntity,Integer> {
    public List<GradeEntity> findBySupplierId(Integer id);
    public List<GradeEntity> findByCriteriaId(Integer id);
}
