package ru.sbrf.uec.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.ArrayList;
import java.util.List;

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
import ru.sbrf.qrcode.events.user.CreateUserEvent;
import ru.sbrf.qrcode.events.user.DeleteUserEvent;
import ru.sbrf.qrcode.events.user.UserDeletedEvent;
import ru.sbrf.qrcode.events.user.AllUsersSelectedEvent;
import ru.sbrf.qrcode.events.user.UserUpdatedEvent;
import ru.sbrf.qrcode.events.user.UserDetails;
import ru.sbrf.qrcode.services.UserEventHandler;
import ru.sbrf.uec.controller.UserController;
import ru.sbrf.uec.domain.UserData;

public class UserControllerTest {

	private MockMvc mockMvc;
	private MappingJackson2HttpMessageConverter msgConverter;
	
	private int id;
	
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
		this.id = 1;
	}
	
	@Test
	public void testGetAllUsers() throws Exception {
		AllUsersSelectedEvent selectedAllUsersEvent = new AllUsersSelectedEvent();
		when(userEventHandler.getAllUsers()).thenReturn(selectedAllUsersEvent);
		
		mockMvc.perform(get("/users"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON));
		
		List<UserDetails> users = new ArrayList<UserDetails>();
		users.add(new UserDetails(id));
		selectedAllUsersEvent.setUsers(users);
		mockMvc.perform(get("/users"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$").isArray())
			.andExpect(jsonPath("$[0].id").value(id));
	}

	@Test
	public void testDeleteUser() throws Exception {
		UserDeletedEvent deletedUserEvent = new UserDeletedEvent();
		deletedUserEvent.setDeleteSuccess(Boolean.TRUE);
		UserDetails userDetails = new UserDetails();
		userDetails.setUserId(id);
		deletedUserEvent.setUserDetails(userDetails);
		
		when(userEventHandler.deleteUser(any(DeleteUserEvent.class))).thenReturn(deletedUserEvent);
		
		mockMvc.perform(delete("/users/{id}",id))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON));
		
		deletedUserEvent.setDeleteSuccess(Boolean.FALSE);
		when(userEventHandler.deleteUser(any(DeleteUserEvent.class))).thenReturn(deletedUserEvent);
		
		mockMvc.perform(delete("/users/{id}",id))
			.andExpect(status().isForbidden())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	public void testChangeActiveStatus() throws Exception {
		UserUpdatedEvent updatedUserEvent = new UserUpdatedEvent();
		updatedUserEvent.setUpdateSuccess(true);
		UserDetails userDetails = new UserDetails();
		userDetails.setUserId(id);
		userDetails.setActive(Boolean.TRUE);
		updatedUserEvent.setUserDetails(userDetails);
		UserData userData = UserData.fromUserDetails(userDetails);
		
		when(userEventHandler.changeUserActiveStatus(any(ChangeUserStatusEvent.class)))
			.thenReturn(updatedUserEvent);
		
		mockMvc.perform(post("/users/change_status")
				.contentType(MediaType.APPLICATION_JSON)
				.content(msgConverter.getObjectMapper().writeValueAsBytes(userData)))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.id").value(id))
			.andExpect(jsonPath("$.active").value(true));
	}

	@Test
	public void testChangePassword() throws Exception {
		UserUpdatedEvent updatedUserEvent = new UserUpdatedEvent();
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
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.id").value(id));
	}

	@Test
	public void testCreateUser() throws Exception {
		UserDetails userDetails = new UserDetails();
		userDetails.setUserId(id);
		AllUsersSelectedEvent selectedAllUsersEvent = new AllUsersSelectedEvent();
		
		when(userEventHandler.createUser(any(CreateUserEvent.class))).thenReturn(selectedAllUsersEvent);
		
		UserData userData = UserData.fromUserDetails(userDetails);
		
		mockMvc.perform(post("/users")
				.contentType(MediaType.APPLICATION_JSON)
				.content(msgConverter.getObjectMapper().writeValueAsBytes(userData)))
			.andExpect(status().isOk());
	}

}
