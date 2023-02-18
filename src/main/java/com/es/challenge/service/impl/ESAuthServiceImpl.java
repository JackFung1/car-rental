package com.es.challenge.service.impl;

import org.springframework.stereotype.Service;

import com.es.challenge.service.ESAuthService;


/**
 * @author fjt
 * @date 2023-02-16
 */
@Service
public class ESAuthServiceImpl implements ESAuthService {

    @Override
    public boolean isAdmin(String key) {
        return "admin".equals(key);
    }

}
