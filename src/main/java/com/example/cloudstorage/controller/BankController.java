package com.example.cloudstorage.controller;

import com.example.cloudstorage.dto.BankInfo;
import com.example.cloudstorage.service.BankInfoImpl;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.var;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
public class BankController {

    @Autowired
    BankInfoImpl service;

//    @GetMapping("get-bank-info/{ifsc}")
//    public ResponseEntity<?> getBankInfo(@PathVariable("ifsc") String ifscCode){
//        BankInfo bankInfo= service.getBankInfo(ifscCode);
//        return ResponseEntity.status(200).body(bankInfo);
//    }

//    @GetMapping("get-bank-info/{ifsc}")
//    public void getBankInfo(@PathVariable("ifsc") String ifscCode) throws UnirestException, IOException {
//        OkHttpClient client = new OkHttpClient();
//
//        Request request = new Request.Builder()
//                .url("https://ifsc-search1.p.rapidapi.com/ifsc/string?"+ifscCode+"initiator_id=9466009091")
//                .get()
//                .addHeader("developer_key", "1fadde97933175c5841562acee0f8a73")
//                .addHeader("X-RapidAPI-Key", "3ec4b9c35fmshe798ab8bce144a8p14dbaejsna3465d08d392")
//                .addHeader("X-RapidAPI-Host", "ifsc-search1.p.rapidapi.com")
//                .build();
//
//        Response response = client.newCall(request).execute();
//
//        System.out.println(response);
//    }

    @GetMapping(value = "get-bank-info/{ifsc}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getBankInfo(@PathVariable("ifsc") String ifscCode) throws UnirestException {
        service.getBankInfo(ifscCode);
        return ResponseEntity.ok(/*service.getBankDetails(ifscCode)*/"Success");

    }
}
