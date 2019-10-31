package com.tonasolution.appointnear.business;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
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
public class AppointmentServiceImpTests {
	
	@Autowired
	AppointmentService appointmentService;
	
	@MockBean
	private AppointmentDao appointmentDao;
	
	@Test
	public void testListAll() {
		
		Mockito.when(this.appointmentDao.findAll())
		.thenReturn(Collections.EMPTY_LIST);
		
		List<IAppointment> appointments = this.appointmentService.listAll();
		
		assertEquals(0, appointments.size());
	}
	
	@Test
	public void testSaveOrUpdate() { 
		
		IAppointment appointment = getAppointment().get();
		Mockito.when(this.appointmentDao.save((Appointment)appointment))
		.thenReturn((Appointment)appointment);
		
		IAppointment appointmentsaved = this.appointmentService.saveOrUpdate(appointment);
		
		assertEquals(appointment.get_id(), appointmentsaved.get_id());
		
	}
	
	@Test
	public void delete() {
		
		doNothing().when(this.appointmentDao).deleteById(1L);
		
		this.appointmentService.delete(1L);
		
		verify(this.appointmentDao, times(1)).deleteById(1L);
	}
	
	@Test
	public void getById() throws Exception {
		when(this.appointmentDao.findById(1L)).thenReturn(getAppointment());
		
		this.appointmentService.getById(1L);
		
		verify(this.appointmentDao, times(1)).findById(1L);
	}
	
	private Optional<Appointment> getAppointment() {
		Adress adress = new Adress();
		adress.setCity("Paris");
		adress.setCountry("France");
		adress.setRegion("region");
		adress.setZipCode("62000");
		
		Advertiser advertiser = new Advertiser();
		advertiser.setEmail("test@gmail.com");
		advertiser.setFirstName("Firstname");
		advertiser.setLastName("last name");
		advertiser.setPhoneNumber("+212 616799599");
		
		Appointment appointment = new Appointment();
		appointment.setAdress(adress);
		appointment.setAdvertiser(advertiser);
		appointment.setAt("12/12/2010");
		appointment.setDescription("description ....");
		appointment.setPrice(12.12);
		appointment.setType("residence");
		appointment.set_id(12L);
		
		return Optional.of(appointment);
		
	}
	
}
