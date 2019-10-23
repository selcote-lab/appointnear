package com.tonasolution.appointnear.dao;

import org.springframework.data.repository.CrudRepository;

import com.tonasolution.appointnear.models.Appointment;

public interface AppointmentDao extends CrudRepository<Appointment, Long> {

}
