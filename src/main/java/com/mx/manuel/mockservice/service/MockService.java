package com.mx.manuel.mockservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.mx.manuel.mockservice.model.MockConfig;

@Service
public class MockService implements IMockService {

    @Autowired
    private ICacheService cache;

    @Override
    public ResponseEntity<?> findRequest(String id) {
        final MockConfig config = cache.addCache(id);
        if (config.responseTime() != null)
            sleep(config.responseTime());
        final MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        if (config.contentType() != null)
            headers.add("Content-Type", config.contentType());
        return new ResponseEntity<>(config.responseBody(), headers, config.statusCode());
    }

    private static void sleep(int time) {
        try {
            Thread.sleep(time * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
