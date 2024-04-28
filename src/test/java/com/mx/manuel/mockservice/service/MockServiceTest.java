package com.mx.manuel.mockservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.mx.manuel.mockservice.model.MockConfig;

@ExtendWith(MockitoExtension.class) 
class MockServiceTest {

    @InjectMocks
    MockService service;

    @Mock
    ICacheService cache;

    @Test
    void findRequestTest() {
        when(cache.addCache(anyString())).thenReturn(new MockConfig("test", 1, "text/plain", 201, "OK"));
        ResponseEntity<?> resp = service.findRequest("Test");
        assertNotNull(resp);
        assertTrue(resp.getStatusCode().is2xxSuccessful());
        assertEquals("OK", resp.getBody());
        assertEquals(MediaType.TEXT_PLAIN, resp.getHeaders().getContentType());
    }
}
