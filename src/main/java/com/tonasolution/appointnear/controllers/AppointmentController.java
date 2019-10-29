package com.tonasolution.appointnear.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tonasolution.appointnear.business.AppointmentService;
import com.tonasolution.appointnear.models.Adress;
import com.tonasolution.appointnear.models.Advertiser;
import com.tonasolution.appointnear.models.Appointment;
import com.tonasolution.appointnear.models.IAppointment;

@RestController
@RequestMapping("/api/v1/")
public class AppointmentController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentController.class);
	private AppointmentService appointmentService;

	@Autowired
	public AppointmentController(AppointmentService appointmentService) {
		this.appointmentService = appointmentService;
	}
	
	@GetMapping("/appointments/all")
	public List<IAppointment> getAll(){
		return this.appointmentService.listAll();
	}
	
	@PostMapping("/appointments/new")
	public ResponseEntity<Void> create(@RequestBody  @Valid Appointment appointment){
		
		try {

			if(appointment == null) {
				return ResponseEntity.badRequest().build();
			}
			else {
				IAppointment appSaved = this.appointmentService.saveOrUpdate(appointment);
//				@Todo: change buildAndExpand value
				URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
						"/{id}").buildAndExpand("111").toUri();
				
				return ResponseEntity.created(location).build();
			}
		}
		catch(Exception e) {
			LOGGER.debug(
					e.getMessage() 
					+ " \n stack trace by " 
					+ e.getStackTrace()
					+ "\n caused by " + e.getCause()
					);
			return ResponseEntity.badRequest().build();
		}
	}
}
