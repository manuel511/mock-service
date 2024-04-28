package com.mx.manuel.mockservice.service;

import org.springframework.http.ResponseEntity;

public interface IMockService {

    ResponseEntity<?> findRequest(String id);
}