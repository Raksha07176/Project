package com.devacademy.HMS.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.devacademy.HMS.model.Admin;
import com.devacademy.HMS.model.Appointment;
import com.devacademy.HMS.service.AdminServiceImplementation;
import com.devacademy.HMS.service.AppointmentServiceImplementation;
import com.devacademy.HMS.service.UserService;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

	private UserService userService;

	private AdminServiceImplementation adminServiceImplementation;

	private AppointmentServiceImplementation appointmentServiceImplementation;

	@Autowired
	public DoctorController(UserService userService, AdminServiceImplementation obj,
			AppointmentServiceImplementation app) {
		this.userService = userService;
		adminServiceImplementation = obj;
		appointmentServiceImplementation = app;
	}

	@RequestMapping("/index")
	public String index(Model model) {

		// get last seen
		String username = "";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
			String Pass = ((UserDetails) principal).getPassword();
			System.out.println("One + " + username + "   " + Pass);
			// System.out.println("department" + ((UserDetails)principal)());

		} else {
			username = principal.toString();
			System.out.println("Two + " + username);
		}

		Admin admin = adminServiceImplementation.findByEmail(username);

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date now = new Date();

		String log = now.toString();

		admin.setLastseen(log);

		adminServiceImplementation.save(admin);

		List<Appointment> list = appointmentServiceImplementation.findByDepartment(admin.getDepartment());

		model.addAttribute("name", admin.getFirstName());

		model.addAttribute("email", admin.getEmail());

		model.addAttribute("department", admin.getDepartment());

		model.addAttribute("user", admin.getFirstName() + " " + admin.getLastName());

		// add to the spring model
		model.addAttribute("app", list);

		return "doctor/index";
	}

}
