package com.hrms.Hrms.controller;

import com.hrms.Hrms.Dto.JwtDetials;
import com.hrms.Hrms.config.JwtDetailsService;
import com.hrms.Hrms.model.Reimbursement;
import com.hrms.Hrms.model.ReimbursementType;
import com.hrms.Hrms.service.ReimbursementService;
import com.hrms.Hrms.service.ReimbursementTypeService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@NoArgsConstructor@AllArgsConstructor
public class ReimbursementController {

    private ReimbursementTypeService reimbursementTypeService;

    private ReimbursementService reimbursementService;

    private JwtDetailsService jwt;


    // This api is used for fetching all Reimbursement Type
    @GetMapping("getAllReimbursement")
    public ResponseEntity<List<ReimbursementType>> getAllReimbursement(){
       return ResponseEntity.status(HttpStatus.OK).body(reimbursementTypeService.getAll());
    }


     // This api is used for fetching all Reimbursement applied by the user
    @GetMapping("getReimbursementByCreated")
    public ResponseEntity<List<Reimbursement>> getReimbursement(){
        JwtDetials simpleInd =  jwt.getJwtDetails((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        String empCode = simpleInd.getEmpCode();
        return ResponseEntity.status(HttpStatus.OK).body(reimbursementService.findByCreatedBy(empCode));
    }

    // This api is used for saving or update Reimbursement.
    @PostMapping("saveReimbursement")
    public ResponseEntity<Reimbursement> saveReimbursement(@RequestBody Reimbursement request){
        JwtDetials simpleInd =  jwt.getJwtDetails((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        String empCode = simpleInd.getEmpCode();
        String industry =  simpleInd.getIndustry();
        request.setCreatedBy(empCode);
        request.setIndustry(industry);
        return ResponseEntity.status(HttpStatus.CREATED).body(reimbursementService.save(request));
    }

}
