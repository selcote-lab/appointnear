package com.tonasolution.appointnear.business;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

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

}
