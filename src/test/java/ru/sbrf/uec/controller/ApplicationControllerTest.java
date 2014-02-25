package ru.sbrf.uec.controller;

import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
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

import ru.sbrf.qrcode.events.application.ApplicationDetails;
import ru.sbrf.qrcode.events.application.ApplicationSelectedEvent;
import ru.sbrf.qrcode.events.application.ApplicationsSelectedEvent;
import ru.sbrf.qrcode.events.application.SelectApplicationEvent;
import ru.sbrf.qrcode.events.application.SelectApplicationsEvent;
import ru.sbrf.qrcode.services.ApplicationEventHandler;
import ru.sbrf.uec.controller.ApplicationController;
import ru.sbrf.uec.domain.ApplicationFilter;

/**
 * @author sbt-koshenkova-mv
 *
 */
public class ApplicationControllerTest {
	
	private MockMvc mockMvc;
	private MappingJackson2HttpMessageConverter msgConverter;

	@InjectMocks
	private ApplicationController controller;

	@Mock
	private ApplicationEventHandler eventHandler;
	
	private String sin;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		this.msgConverter = new MappingJackson2HttpMessageConverter();
		this.mockMvc = standaloneSetup(controller).setMessageConverters(this.msgConverter).build();
		sin = "12345123451234512345";
	}

	/**
	 * Test method for {@link ru.sbrf.uec.controller.ApplicationController#getApplicationsByFilter(ru.sbrf.uec.domain.ApplicationFilter)}.
	 */
	@Test
	public void testGetApplicationsByFilter() throws Exception {
		ApplicationsSelectedEvent resultEvent = new ApplicationsSelectedEvent();
		List<ApplicationDetails> apps = new ArrayList<ApplicationDetails>();
		resultEvent.setApps(apps);
		
		when(eventHandler.getApplicationsByFilter(any(SelectApplicationsEvent.class))).thenReturn(resultEvent);

		ApplicationFilter applicationFilter = new ApplicationFilter();
		applicationFilter.setSin(sin);
		mockMvc.perform(get("/application")
					.contentType(MediaType.APPLICATION_JSON)
					.content(msgConverter.getObjectMapper().writeValueAsBytes(applicationFilter)))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	/**
	 * Test method for {@link ru.sbrf.uec.controller.ApplicationController#getApplicationById(java.lang.String)}.
	 */
	@Test
	public void testGetApplicationById() throws Exception {
		ApplicationSelectedEvent resultEvent = new ApplicationSelectedEvent();
		ApplicationDetails applicationDetails = new ApplicationDetails();
		applicationDetails.setSin(sin);
		resultEvent.setApplicationDetails(applicationDetails);
		
		when(eventHandler.getApplicationBySin(any(SelectApplicationEvent.class))).thenReturn(resultEvent);

		mockMvc.perform(get("/application/{sin}", sin))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.sin").value(sin));
	}

}
