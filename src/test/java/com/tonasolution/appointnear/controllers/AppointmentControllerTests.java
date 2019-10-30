package com.tonasolution.appointnear.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tonasolution.appointnear.business.AppointmentService;
import com.tonasolution.appointnear.models.Adress;
import com.tonasolution.appointnear.models.Advertiser;
import com.tonasolution.appointnear.models.Appointment;
import com.tonasolution.appointnear.models.IAppointment;

@RunWith(SpringRunner.class)
@WebMvcTest
public class AppointmentControllerTests {
	public static final String API_URL = "/api/v1/appointments/";
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired 
	private ObjectMapper mapper;
	
	@MockBean
	private AppointmentService appointmentService;
	
	@Test
	public void testGetAll() throws Exception {
		
		Mockito.when(this.appointmentService.listAll())
				.thenReturn(Collections.EMPTY_LIST);
		
		MvcResult mvcResult = mockMvc.perform(
					MockMvcRequestBuilders.get(API_URL + "all")
					.accept(MediaType.APPLICATION_JSON)
				)
				.andReturn();
		
		System.out.println(mvcResult.getResponse());
		
	}
	
	@Test
	public void testCreate() throws Exception {
		
		Adress adress = new Adress();
		adress.setCity("Paris");
		adress.setCountry("France");
		adress.setRegion("region");
		adress.setZipCode("62000");
		
		Advertiser advertiser = new Advertiser();
		advertiser.setEmail("test@gmail.com");
		advertiser.setFirstName("Firstname");
		advertiser.setLastName("last name");
		advertiser.setPhoneNumber("+212 616714599");
		
		IAppointment appointment = new Appointment();
		appointment.setAdress(adress);
		appointment.setAdvertiser(advertiser);
		appointment.setAt("12/12/2010");
		appointment.setDescription("description ....");
		appointment.setPrice(12.12);
		appointment.setType("residence");
		appointment.set_id(12L);
		
		
		when(this.appointmentService.saveOrUpdate(appointment)).thenReturn(appointment);
		
		String appToJson = this.mapper.writeValueAsString(appointment);
		
		System.out.println(appToJson);
		
		MvcResult result = mockMvc.perform(
					MockMvcRequestBuilders.post(API_URL + "new")
					.content(appToJson)
					.contentType(MediaType.APPLICATION_JSON)
					.characterEncoding("utf-8")
					.accept(MediaType.APPLICATION_JSON)
				)
			   .andExpect(status().isCreated())
			   .andReturn(); 
		System.out.println(result.toString());
		
	}
}

