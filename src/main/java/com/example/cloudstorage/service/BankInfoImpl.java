package com.example.cloudstorage.service;

import com.example.cloudstorage.dto.BankInfo;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.var;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BankInfoImpl {

    private RestTemplate restTemplate;

    public String getBankInfo(String ifsc) throws UnirestException {
        var body = Unirest.get("https://ifsc.firstatom.org/key/YLGF406033jAX8a4d45kVA3kV/ifsc/" + ifsc)
                .asJson().getBody().getObject();
        System.out.println(body);
        return "good";
    }

    public BankInfo getBankDetails(String ifsc) throws UnirestException {
        try {
            var body = Unirest.get("https://ifsc.razorpay.com/" + ifsc)
                    .asJson().getBody().getObject();

            BankInfo info = new BankInfo();
            info.setBank(String.valueOf(body.get("BANK")));
            info.setState(String.valueOf(body.get("STATE")));
            info.setAddress(String.valueOf(body.get("ADDRESS")));
            info.setBankCode(String.valueOf(body.get("BANKCODE")));
            info.setCenter(String.valueOf(body.get("CENTRE")));
            info.setCity(String.valueOf(body.get("CITY")));
            info.setDistrict(String.valueOf(body.get("DISTRICT")));
            info.setMICR(String.valueOf(body.get("MICR")));
            info.setIFSC(String.valueOf(body.get("IFSC")));
            info.setBranch(String.valueOf(body.get("BRANCH")));
            info.setContact(String.valueOf(body.get("CONTACT")));
            return info;
        }
        catch (Exception e){

        }
        return null;
    }

    public void getBankInfoTwo(String ifsc) throws UnirestException {
        var body = Unirest.get("https://bank-apis.justinclicks.com/API/V1/IFSC" + ifsc)
                .asJson().getBody().getObject();
        System.out.println(body);
    }

}
