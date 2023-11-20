package com.personaproject.ecommercewebapp.common;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class ResponseServices {

    public HashMap<String, Object> apiResponse(HttpStatus httpStatus, Object message) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("HttpStatus", httpStatus);
        hashMap.put("Message", message);
        return hashMap;
    }
}
