package com.mx.manuel.mockservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("MOCK_CONFIG")
public record MockConfig(@Id String id, Integer responseTime, String contentType, int statusCode, Object responseBody) {
}