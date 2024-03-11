package com.hrms.Hrms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "attachment_log")
@Data@NoArgsConstructor@AllArgsConstructor
public class Attachment {

    @Id
    private UUID id;
    private String fileName;
    private String fileData;
    @CreationTimestamp
    private Date createdDate;
    private String createdBy;

}
