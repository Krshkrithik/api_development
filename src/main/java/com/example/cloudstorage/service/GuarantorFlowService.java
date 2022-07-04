package com.example.cloudstorage.service;

import com.example.cloudstorage.Mapper.GuarantorMapper;
import com.example.cloudstorage.dto.AcceptanceStatus;
import com.example.cloudstorage.dto.EmailVerifyDto;
import com.example.cloudstorage.dto.GuarantorSignup;
import com.example.cloudstorage.exceptions.GuarantorLimitExceed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GuarantorFlowService {

    @Autowired
    GuarantorMapper mapper;

    public void getVerifyStatus(AcceptanceStatus status){
        int count = mapper.getGuarantorCount(Integer.parseInt(status.getCustomerId()));
        if ((count < 3) || (count == 0 )) {
            log.info(String.valueOf(status));
            mapper.updateVerification(Integer.parseInt(status.getCustomerId()),
                    status.getIsAcceptance().equals("1"), count++);
        }
        else {
            throw new GuarantorLimitExceed(404,"Guarantor Limit Exceed!");
        }
    }

    public void Signup(GuarantorSignup signup){
        if(signup.getConformPassword().equals(signup.getPassword())) {
            signup.setPassword(BCrypt.hashpw(signup.getPassword(),BCrypt.gensalt()));
            mapper.updateGuarantor(signup);
        }
        else{
            throw new GuarantorLimitExceed(403,"Password Mismatched!");
        }
    }

    public void verifyEmail(EmailVerifyDto dto){
        int count = mapper.countByEmail(dto.getEmail());
        if (count>0){
            throw new GuarantorLimitExceed(HttpStatus.FOUND.value(), "Email id already registered");
        }
        else{
            log.info(String.valueOf(dto));
            mapper.createGuarantor(dto.getEmail());
        }
    }
}
