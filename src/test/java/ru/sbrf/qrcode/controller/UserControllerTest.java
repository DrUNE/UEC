package ru.sbrf.qrcode.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;

import ru.sbrf.qrcode.events.user.ChangeUserPasswordEvent;
import ru.sbrf.qrcode.events.user.ChangeUserStatusEvent;
import ru.sbrf.qrcode.events.user.DeleteUserEvent;
import ru.sbrf.qrcode.events.user.DeletedUserEvent;
import ru.sbrf.qrcode.events.user.SelectedAllUsersEvent;
import ru.sbrf.qrcode.events.user.UpdatedUserEvent;
import ru.sbrf.qrcode.events.user.UserDetails;
import ru.sbrf.qrcode.json.UserData;
import ru.sbrf.qrcode.services.UserEventHandler;

public class UserControllerTest {

	private MockMvc mockMvc;
	private MappingJackson2HttpMessageConverter msgConverter;
	
	private long id;
	
	@InjectMocks
	private UserController userController;
	
	@Mock
	private UserEventHandler userEventHandler;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		this.msgConverter = new MappingJackson2HttpMessageConverter();
		this.mockMvc = standaloneSetup(userController).setMessageConverters(
				this.msgConverter).build();
		this.id = 1L;
	}
	
	@Test
	public void testGetAllUsers() throws Exception {
		when(userEventHandler.getAllUsers()).thenReturn(new SelectedAllUsersEvent());
		
		mockMvc.perform(get("/users"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	public void testDeleteUser() throws Exception {
		DeletedUserEvent deletedUserEvent = new DeletedUserEvent();
		deletedUserEvent.setDeleteSuccess(Boolean.TRUE);
		UserDetails userDetails = new UserDetails();
		userDetails.setUserId(id);
		deletedUserEvent.setUserDetails(userDetails);
		
		when(userEventHandler.deleteUser(any(DeleteUserEvent.class))).thenReturn(deletedUserEvent);
		
		mockMvc.perform(delete("/users/{id}",id))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	public void testChangeActiveStatus() throws Exception {
		UpdatedUserEvent updatedUserEvent = new UpdatedUserEvent();
		updatedUserEvent.setUpdateSuccess(true);
		UserDetails userDetails = new UserDetails();
		userDetails.setUserId(id);
		userDetails.setActive(Boolean.FALSE);
		updatedUserEvent.setUserDetails(userDetails);
		UserData userData = UserData.fromUserDetails(userDetails);
		
		when(userEventHandler.changeUserActiveStatus(any(ChangeUserStatusEvent.class)))
			.thenReturn(updatedUserEvent);
		
		mockMvc.perform(post("/users/change_status")
				.contentType(MediaType.APPLICATION_JSON)
				.content(msgConverter.getObjectMapper().writeValueAsBytes(userData)))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	public void testChangePassword() throws Exception {
		UpdatedUserEvent updatedUserEvent = new UpdatedUserEvent();
		updatedUserEvent.setUpdateSuccess(true);
		UserDetails userDetails = new UserDetails();
		userDetails.setUserId(id);
		updatedUserEvent.setUserDetails(userDetails);
		UserData userData = UserData.fromUserDetails(userDetails);
		
		when(userEventHandler.changeUserPassword(any(ChangeUserPasswordEvent.class)))
			.thenReturn(updatedUserEvent);
		
		mockMvc.perform(post("/users/change_password")
				.contentType(MediaType.APPLICATION_JSON)
				.content(msgConverter.getObjectMapper().writeValueAsBytes(userData)))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

}
