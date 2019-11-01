package com.tonasolution.appointnear.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.tonasolution.appointnear.AppointnearApplication;
import com.tonasolution.appointnear.dao.AppointmentDao;
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
public class AppointmentServiceImpITests {
	
	@Autowired
	AppointmentService appointmentService;
	
//	@Test
//	public void testListAll() {
//		
//		List<IAppointment> appointments = this.appointmentService.listAll();
//		assertEquals(0, appointments.size());
//		
//	}
//	
//	@Test
//	public void testSaveOrUpdate() {
//		Adress adress = new Adress();
//		adress.setCity("Paris");
//		adress.setCountry("France");
//		adress.setRegion("region");
//		adress.setZipCode("62000");
//		
//		Advertiser advertiser = new Advertiser();
//		advertiser.setEmail("test@gmail.com");
//		advertiser.setFirstName("Firstname");
//		advertiser.setLastName("last name");
//		advertiser.setPhoneNumber("+212 99090009999");
//		
//		IAppointment appointment = new Appointment();
//		appointment.setAdress(adress);
//		appointment.setAdvertiser(advertiser);
//		appointment.setAt("12/12/2010");
//		appointment.setDescription("description ....");
//		appointment.setPrice(12.12);
//		appointment.setType("residence");
//		
//		IAppointment appointmentsaved = this.appointmentService.saveOrUpdate(appointment);
//		
//		assertEquals(12.12, appointmentsaved.getPrice());
//	}
//	
//	@Test(expected = IllegalArgumentException.class)
//	public void delete() {
//		this.appointmentService.delete(null);
//	}
//	
//	@Test(expected = IllegalArgumentException.class)
//	public void getById() throws Exception {
//		this.appointmentService.getById(null);
//	}
	
}
