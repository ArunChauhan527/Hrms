package com.hrms.Hrms.repository;

import com.hrms.Hrms.model.Reimbursement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReimbursementRepository extends JpaRepository<Reimbursement, UUID> {

    List<Reimbursement> findByCreatedByOrderByUpdatedByDesc(String createdBy);

}
