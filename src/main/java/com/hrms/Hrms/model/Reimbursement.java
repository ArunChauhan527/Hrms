package com.hrms.Hrms.model;

import com.hrms.Hrms.Enum.Action;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="reimbursement_log")
@Data@AllArgsConstructor@NoArgsConstructor
public class Reimbursement {

    @Id
    @GeneratedValue
    private UUID reimbursementId;
    private String remarks;
    private Long claimedAmount;
    private Long totalAmount;
    private String billNumber;
    private String expenseName;
    @CreationTimestamp
    private Date createdDate;
    @UpdateTimestamp
    private Date updatedDate;
    private Date reimbursementDate;
    private UUID reimbursementTypeId;
    private UUID attachmentId;
    private String createdBy;
    private String updatedBy;
    @Enumerated(EnumType.STRING)
    private Action status;
    private String industry;
    @Transient
    private String file;


}
