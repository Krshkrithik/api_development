package com.example.cloudstorage.controller;

import com.example.cloudstorage.dto.AcceptanceStatus;
import com.example.cloudstorage.dto.EmailVerifyDto;
import com.example.cloudstorage.dto.GuarantorSignup;
import com.example.cloudstorage.service.GuarantorFlowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class GuarantorFlow {

    @Autowired
    GuarantorFlowService service;

    @PostMapping("/get-verify-status")
    public ResponseEntity<?> getVerifiedStatus(@Valid @RequestBody AcceptanceStatus status){
        service.getVerifyStatus(status);
        Map<String,String> response  =  new HashMap<>();
        response.put("status","Guarantor has been added");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody GuarantorSignup signup){
        service.Signup(signup);
        return ResponseEntity.ok(signup);
    }

    @PostMapping("/verify-email")
    public ResponseEntity<?> verifyEmail(@RequestBody EmailVerifyDto dto){
        log.info(String.valueOf(dto));
        service.verifyEmail(dto);
        return ResponseEntity.ok("success");
    }
}
