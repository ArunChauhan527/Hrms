package com.hrms.Hrms.repository;

import com.hrms.Hrms.model.ReimbursementType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReimbursementTypeRepository extends JpaRepository<ReimbursementType, UUID> {
}
