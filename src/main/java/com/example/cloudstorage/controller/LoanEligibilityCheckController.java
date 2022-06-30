package com.example.cloudstorage.controller;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.var;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoanEligibilityCheckController {

    @PostMapping("check-my-aadhar")
    public ResponseEntity<?> checkMyAadhar(@RequestParam("num") String number) throws UnirestException {
        var response = Unirest.post("https://aadhaar-number-verification.p.rapidapi.com/Uidverifywebsvcv1/Uidverify").header("content-type", "application/x-www-form-urlencoded").header("X-RapidAPI-Key", "3ec4b9c35fmshe798ab8bce144a8p14dbaejsna3465d08d392").header("X-RapidAPI-Host", "aadhaar-number-verification.p.rapidapi.com").body("captchaValue=TK6HXq&captchaTxnId=58p5MxkQrNFp&method=uidvalidate&clientid=111&txn_id=4545533&consent=Y&uidnumber=" + number).asJson().getBody().getObject();
        System.out.println(response);
        return ResponseEntity.ok(response);
    }

    @GetMapping("get-captcha")
    public ResponseEntity<?> getCaptcha() throws UnirestException {
        var response = Unirest.post("https://aadhaar-number-verification.p.rapidapi.com/Uidverifywebsvcv1/Getcaptcha").header("content-type", "application/x-www-form-urlencoded").header("X-RapidAPI-Key", "3ec4b9c35fmshe798ab8bce144a8p14dbaejsna3465d08d392").header("X-RapidAPI-Host", "aadhaar-number-verification.p.rapidapi.com").body("clientid=111&txn_id=985656&method=getcaptcha").asJson().getBody().getObject();

        return ResponseEntity.ok(response);
    }
}
