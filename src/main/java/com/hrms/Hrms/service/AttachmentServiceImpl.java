package com.hrms.Hrms.service;

import com.hrms.Hrms.model.Attachment;
import com.hrms.Hrms.repository.AttachmentRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service@Transactional@AllArgsConstructor@NoArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {


    private AttachmentRepository attachmentRepository;

    @Override
    public Attachment saveAttachment(Attachment attachment) {
        return attachmentRepository.saveAndFlush(attachment);
    }
}
