package com.tonasolution.appointnear.controllers;

import static org.mockito.Mockito.when;
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
import com.tonasolution.appointnear.business.AppointmentService;
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
	
	@Autowired
	AppointmentService appointmentService;
	
	@Test
	public void testGetAll() throws Exception {
		
		mockMvc.perform(
					MockMvcRequestBuilders.get(API_URL + "all")
					.accept(MediaType.APPLICATION_JSON)
		).andExpect(status().isAccepted());
		
	}
	
	@Test
	public void testCreate() throws Exception {
		
		String objToJson = this.mapper.writeValueAsString(getAppointment());
		
		mockMvc.perform(
					MockMvcRequestBuilders.post(API_URL + "new")
					.accept(MediaType.APPLICATION_JSON)
					.characterEncoding("utf-8")
					.content(objToJson)
					.contentType(MediaType.APPLICATION_JSON)
				)
				.andExpect(status().isCreated());
	}
	
	@Test
	public void testDelete() throws Exception {
		
		mockMvc.perform(
				MockMvcRequestBuilders.delete(API_URL + "{id}/delete", "1")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
			)
			.andExpect(status().isOk());
	}
	
	@Test
	public void testGetById() throws Exception {
		
		IAppointment appointment = this.appointmentService.saveOrUpdate(getAppointment());
		mockMvc.perform(
					MockMvcRequestBuilders.get(API_URL + "get/by/{id}", String.valueOf(appointment.get_id()))
					.accept(MediaType.APPLICATION_JSON)
					.contentType(MediaType.APPLICATION_JSON)
				)
				.andExpect(status().isAccepted());
	}
	
	private IAppointment getAppointment() {
		
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
		
		return appointment;
	}
}
