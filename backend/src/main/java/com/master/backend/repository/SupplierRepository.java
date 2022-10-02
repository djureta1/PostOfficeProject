package com.master.backend.repository;

import com.master.backend.model.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<SupplierEntity,Integer> {
}
