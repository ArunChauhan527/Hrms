package com.hrms.Hrms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name="reimbursement_type")
@Data@NoArgsConstructor@AllArgsConstructor
public class ReimbursementType {

    @Id
    private UUID id;
    private String type;

}
