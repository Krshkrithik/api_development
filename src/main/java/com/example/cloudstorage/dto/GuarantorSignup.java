package com.example.cloudstorage.dto;

import lombok.Data;

@Data
public class GuarantorSignup {
    private String name;
    private String mobileNumber;
    private String emailId;
    private String gender;
    private String password;
    private String conformPassword;
}
