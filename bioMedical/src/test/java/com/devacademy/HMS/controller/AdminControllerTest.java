package com.devacademy.HMS.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.web.context.WebApplicationContext;

import com.devacademy.HMS.Controller.AdminController;
import com.devacademy.HMS.model.Admin;
import com.devacademy.HMS.service.AdminServiceImplementation;
import com.devacademy.HMS.service.AppointmentServiceImplementation;
import com.devacademy.HMS.service.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(AdminController.class)
@WebAppConfiguration
public class AdminControllerTest {
	
	private MockMvc mockMvc;
	
	@Autowired
	private AdminServiceImplementation adminService;
	
	@Autowired
	private WebApplicationContext webApplication;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplication).build();
	}
	
	@Test
	public void testShowFormForAdd() throws Exception {
		
		Admin admin = new Admin()
				.id(1)
                .firstname("Alicia")
                .lastname("Parker")
                .email("test@gmail.com")
                .department("Neurology")
                .gender("Female")
                .build();
 
        when(adminService.findByEmail("test@gmail.com")).thenReturn(admin);
 
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/add-doctor"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("/admin/addDoctor"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/admin/doctor"))
                .andExpect(MockMvcResultMatchers.model().attribute("doctor", hasProperty("id", is(1))))
                .andExpect(MockMvcResultMatchers.model().attribute("doctor", hasProperty("firstname", is("Alicia"))))
                .andExpect(MockMvcResultMatchers.model().attribute("doctor", hasProperty("lastname", is("Parker"))))
                .andExpect(MockMvcResultMatchers.model().attribute("doctor", hasProperty("email", is("test@gmail.com"))))
                .andExpect(MockMvcResultMatchers.model().attribute("doctor", hasProperty("department", is("Neurology"))))
                .andExpect(MockMvcResultMatchers.model().attribute("doctor", hasProperty("gender", is("Female"))));
                
 
       verify(adminService, times(1)).findByEmail("test@gmail.com");
       verifyNoMoreInteractions(adminService);
    }
}

