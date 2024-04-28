package com.mx.manuel.mockservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mx.manuel.mockservice.model.MockConfig;

public interface IMockConfigRepository extends MongoRepository<MockConfig, String> {
}