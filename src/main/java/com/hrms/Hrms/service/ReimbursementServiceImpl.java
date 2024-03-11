package com.hrms.Hrms.service;

import com.hrms.Hrms.Enum.Action;
import com.hrms.Hrms.model.Attachment;
import com.hrms.Hrms.model.Reimbursement;
import com.hrms.Hrms.repository.ReimbursementRepository;
import com.hrms.Hrms.repository.ReimbursementTypeRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service@AllArgsConstructor@NoArgsConstructor@Transactional
public class ReimbursementServiceImpl implements ReimbursementService{


    private ReimbursementRepository reimbursementRepository;

    private ReimbursementTypeRepository reimbursementTypeRepository;

    private AttachmentService attachmentService;

    @Override
    public Reimbursement save(Reimbursement reimbursement) {
        reimbursement.setStatus(Action.Pending);
        if(reimbursement.getFile().isEmpty())
        {
            Attachment attachment = new Attachment();
            attachment.setFileData(reimbursement.getFile());
            reimbursement.setAttachmentId(attachmentService.saveAttachment(attachment).getId());
        }
        return reimbursementRepository.saveAndFlush(reimbursement);
    }

    @Override
    public List<Reimbursement> findByCreatedBy(String createdBy) {
        return reimbursementRepository.findByCreatedByOrderByUpdatedByDesc(createdBy);
    }


}
