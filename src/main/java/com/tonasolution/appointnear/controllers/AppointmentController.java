package com.tonasolution.appointnear.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tonasolution.appointnear.business.AppointmentService;
import com.tonasolution.appointnear.models.IAppointment;

@RestController
@RequestMapping("/api/v1/")
public class AppointmentController {
	
	private AppointmentService appointmentService;

	@Autowired
	public AppointmentController(AppointmentService appointmentService) {
		this.appointmentService = appointmentService;
	}
	
	@GetMapping("/all")
	public List<IAppointment> getAll(){
		return this.appointmentService.listAll();
	}
}
