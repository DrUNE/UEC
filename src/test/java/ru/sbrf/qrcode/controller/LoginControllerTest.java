package ru.sbrf.qrcode.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;

import ru.sbrf.qrcode.json.User;

public class LoginControllerTest {

	private MockMvc mockMvc;
	private MappingJackson2HttpMessageConverter msgConverter;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.msgConverter = new MappingJackson2HttpMessageConverter();
		this.mockMvc = standaloneSetup(new LoginController()).setMessageConverters(
				this.msgConverter).build();
	}

	@Test
	public void testLogin() throws Exception {
		mockMvc.perform(
				post("/login")
					.contentType(MediaType.APPLICATION_JSON)
					.content(msgConverter.getObjectMapper().writeValueAsBytes(new User())))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().string("{\"resultCode\":0,\"resultMessage\":null}"));
	}

	@Test
	public void testLoginFail() throws Exception {
		standaloneSetup(new LoginController()).build().perform(post("/login"))
				.andExpect(status().isUnsupportedMediaType());
	}

}
