package com.tonasolution.appointnear.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tonasolution.appointnear.AppointnearApplication;
import com.tonasolution.appointnear.models.Adress;
import com.tonasolution.appointnear.models.Advertiser;
import com.tonasolution.appointnear.models.Appointment;
import com.tonasolution.appointnear.models.IAppointment;

@RunWith(SpringRunner.class)
@SpringBootTest( 
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
		classes = AppointnearApplication.class
)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
public class AppointmentControllerITests {
	public static final String API_URL = "/api/v1/appointments/";
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired 
	private ObjectMapper mapper;
	
	@Test
	public void testGetAll() throws Exception {
		
		MvcResult mvcResult = mockMvc.perform(
					MockMvcRequestBuilders.get(API_URL + "all")
					.accept(MediaType.APPLICATION_JSON)
				).andReturn();
		
		System.out.println(mvcResult.getResponse());
		
	}
	
	@Test
	public void create() throws Exception {
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
		
		
		String objToJson = this.mapper.writeValueAsString(appointment);
		
		mockMvc.perform(
					MockMvcRequestBuilders.post(API_URL + "new")
					.accept(MediaType.APPLICATION_JSON)
					.characterEncoding("utf-8")
					.content(objToJson)
					.contentType(MediaType.APPLICATION_JSON)
				)
				.andExpect(status().isCreated());
	}
}
