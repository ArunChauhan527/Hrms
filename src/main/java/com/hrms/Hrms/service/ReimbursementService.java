package com.hrms.Hrms.service;

import com.hrms.Hrms.model.Reimbursement;

import java.util.List;

public interface ReimbursementService {

    Reimbursement save(Reimbursement reimbursement);

    List<Reimbursement> findByCreatedBy(String createdBy);
}
