package com.example.cloudstorage.dto;

import lombok.Data;

import java.util.List;

@Data
public class Preferences {
    private String money;
    private String riskLevel;
    private List<String> loanTypes;
}
