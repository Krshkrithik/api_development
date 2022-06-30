package com.example.cloudstorage.controller;

import com.example.cloudstorage.dto.Preferences;
import com.example.cloudstorage.service.AutoInvestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AutoInvestController {

    @Autowired
    AutoInvestService service;

    @PostMapping("/get-preferences")
    public ResponseEntity<?> getPrefernces(@RequestBody Preferences dto){
        service.getPreferences(dto);
        return  ResponseEntity.ok("Success");
    }

    @GetMapping("/get-all-borrowers")
    public void getAllBorrower(){

    }

    @GetMapping("/get-borrowers-by-preference")
    public void getBorrowersByPreference(){

    }
}
