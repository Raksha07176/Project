package com.devacademy.HMS.service;

import java.util.List;

import com.devacademy.HMS.model.Admin;
import com.devacademy.HMS.model.User;

public interface AdminService {

	public List<Admin> findByRole(String user);

	public Admin findByEmail(String user);

	public List<Admin> findAll();

	public void save(Admin admin);

}
