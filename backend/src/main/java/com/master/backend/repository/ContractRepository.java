package com.master.backend.repository;

import com.master.backend.model.ContractEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContractRepository extends JpaRepository<ContractEntity,Integer> {
    List<ContractEntity> findByUserId(Integer id);
    List<ContractEntity> findBySupplierId(Integer id);
}
