package com.tonasolution.appointnear.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tonasolution.appointnear.dao.AppointmentDao;
import com.tonasolution.appointnear.models.Appointment;
import com.tonasolution.appointnear.models.IAppointment;

@Service
public class AppointmentServiceImp implements AppointmentService {
	
	private AppointmentDao appointmentDao;
	
	@Autowired
	public AppointmentServiceImp(AppointmentDao appointmentDao) {
		this.appointmentDao = appointmentDao;
	}

	@Override
	public List<IAppointment> listAll() {
		
		List<IAppointment> appointments = new ArrayList<IAppointment>();
		this.appointmentDao.findAll().forEach(appointments::add);
		
		return appointments;
	}

	@Override
	public IAppointment getById(Long id) throws Exception {
		
		if(id == null) throw new IllegalArgumentException("Input not valid");
		
		Optional<Appointment> appointmentOpt = this.appointmentDao.findById(id);
		
		return appointmentOpt.get();
	}

	@Override
	public IAppointment saveOrUpdate(IAppointment appointment) {
		return this.appointmentDao.save((Appointment) appointment);
	}

	@Override
	public void delete(Long id) {
		if (id == null) throw new IllegalArgumentException("Bad attribute");
		this.appointmentDao.deleteById(id);
		
	}
}
