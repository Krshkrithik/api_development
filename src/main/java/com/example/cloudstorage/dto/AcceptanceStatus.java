package com.example.cloudstorage.dto;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class AcceptanceStatus {

    @Pattern(regexp = "^[0-1]{1}$",message = "Please enter valid constraints")
    private String isAcceptance;

    private String customerId;
}
