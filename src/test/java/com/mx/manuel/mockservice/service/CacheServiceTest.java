package com.mx.manuel.mockservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mx.manuel.mockservice.model.MockConfig;
import com.mx.manuel.mockservice.repository.IMockConfigRepository;

@ExtendWith(MockitoExtension.class) 
class CacheServiceTest {

    @InjectMocks
    CacheService service;

    @Mock
    IMockConfigRepository repository;

    @Test
    void addCacheTest() {
        when(repository.findById(anyString())).thenReturn(Optional.of(new MockConfig("test", 10, "type", 201, "OK")));
        MockConfig resp = service.addCache("test");
        assertNotNull(resp);
        assertEquals(201, resp.statusCode());
    }

    @Test
    void addCacheErrorTest() {
        assertThrows(IllegalArgumentException.class, () -> service.addCache("test"));
    }
}
