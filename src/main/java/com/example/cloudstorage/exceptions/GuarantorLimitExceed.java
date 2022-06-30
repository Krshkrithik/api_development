package com.example.cloudstorage.exceptions;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuarantorLimitExceed extends RuntimeException{
    private int status;
    private String message;
}
