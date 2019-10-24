package com.tonasolution.appointnear.controllers;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.tonasolution.appointnear.business.AppointmentService;

@RunWith(SpringRunner.class)
@WebMvcTest
public class AppointmentControllerTests {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	AppointmentService appointmentService;
	
	@Test
	public void testGetAll() throws Exception {
		
		Mockito.when(this.appointmentService.listAll())
				.thenReturn(Collections.EMPTY_LIST);
		
		MvcResult mvcResult = mockMvc.perform(
					MockMvcRequestBuilders.get("/api/v1/all")
					.accept(MediaType.APPLICATION_JSON)
				).andReturn();
		
		System.out.println(mvcResult.getResponse());
		
	}
}

