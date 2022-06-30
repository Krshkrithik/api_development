package com.example.cloudstorage.service;

import com.example.cloudstorage.Mapper.GuarantorMapper;
import com.example.cloudstorage.dto.AcceptanceStatus;
import com.example.cloudstorage.dto.GuarantorSignup;
import com.example.cloudstorage.exceptions.GuarantorLimitExceed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
            mapper.createGuarantor(Integer.parseInt(status.getCustomerId()));
        }
        else {
            throw new GuarantorLimitExceed(404,"Guarantor Limit Exceed!");
        }
    }

    public void Signup(GuarantorSignup signup){
        mapper.updateGuarantor(signup);
    }
}
