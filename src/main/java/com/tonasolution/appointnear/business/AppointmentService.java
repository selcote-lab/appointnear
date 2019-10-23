package com.tonasolution.appointnear.business;

import java.util.List;

import com.tonasolution.appointnear.models.IAppointment;

public interface AppointmentService {
	
	List<IAppointment> listAll();
	
	IAppointment getById(Long id) throws Exception;
	
	IAppointment saveOrUpdate(IAppointment appointment);
	
	void delete(Long id);
}
