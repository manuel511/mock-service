package com.mx.manuel.mockservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.mx.manuel.mockservice.model.MockConfig;
import com.mx.manuel.mockservice.repository.IMockConfigRepository;

@Service
public class CacheService implements ICacheService {

    private static final Logger LOG = LoggerFactory.getLogger(CacheService.class);

    @Autowired
    private IMockConfigRepository repository;

    @Cacheable("mock")
    @Override
    public MockConfig addCache(String id) {
        LOG.info("Adding to Cache: {}", id);
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException(id.concat(" not found")));
    }
}
