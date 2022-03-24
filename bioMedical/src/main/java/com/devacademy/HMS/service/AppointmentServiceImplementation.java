package com.devacademy.HMS.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devacademy.HMS.model.Appointment;
import com.devacademy.HMS.repository.AppointmentRepository;
import com.devacademy.HMS.model.Admin;

@Service
public class AppointmentServiceImplementation {

	private AppointmentRepository appointmentRepository;

	@Autowired // Adding bean id @Qualifier
	public AppointmentServiceImplementation(AppointmentRepository obj) {
		appointmentRepository = obj;
	}

	public void save(Appointment app) {

		appointmentRepository.save(app);
	}

	public List<Appointment> findAll() {
		return appointmentRepository.findAll();
	}

	public List<Appointment> findByDepartment(String department) {
		return appointmentRepository.findByDepartment(department);
	}

}
