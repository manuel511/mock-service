package com.mx.manuel.mockservice.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

class HandleErrorTest {

    HandleError controller = new HandleError();

    @Test
    void handleUnhandledErrorTest() {
        ResponseEntity<?> response = controller.handleUnhandledError(new Exception("Test"));
        assertNotNull(response);
        assertTrue(response.getStatusCode().is5xxServerError());
    }

    @Test
    void handleEntityNotFoundTest() {
        ResponseEntity<?> response = controller.handleEntityNotFound(new IllegalArgumentException("Test"));
        assertNotNull(response);
        assertEquals(404, response.getStatusCode().value());
    }
}
