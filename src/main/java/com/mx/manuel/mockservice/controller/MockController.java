package com.mx.manuel.mockservice.controller;

import org.springframework.web.bind.annotation.RestController;

import com.mx.manuel.mockservice.service.IMockService;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class MockController {

    @Autowired
    private IMockService service;

    @RequestMapping("/**")
    public ResponseEntity<?> mock(HttpServletRequest request) {
        return service.findRequest(request.getMethod() + request.getRequestURI());
    }

    @GetMapping("/api/v1/mock/cache/clear")
    @CacheEvict(value = "mock", allEntries = true)
    public String clearCache(HttpServletRequest request) {
        return "Cache reset";
    }

}