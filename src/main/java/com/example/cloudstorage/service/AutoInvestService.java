package com.example.cloudstorage.service;

import com.example.cloudstorage.dto.Preferences;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AutoInvestService {

    public void getPreferences(Preferences dto){
        log.info(String.valueOf(dto));
    }

}
