package com.mx.manuel.mockservice.controller;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.mx.manuel.mockservice.service.IMockService;

@WebMvcTest(MockController.class)
public class MockControllerTest {

    @Autowired
	MockMvc mockMvc;

    @MockBean
	IMockService service;

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
	void mockTest() throws Exception {
		when(service.findRequest(anyString())).thenReturn(new ResponseEntity("TEST OK", HttpStatusCode.valueOf(201)));
		this.mockMvc.perform(post("/api/v1/mock/test")).andDo(print()).andExpect(status().isCreated())
				.andExpect(content().string(containsString("TEST OK")));
	}

    @Test
	void clearCacheTest() throws Exception {
		this.mockMvc.perform(get("/api/v1/mock/cache/clear")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Cache reset")));
	}
}
