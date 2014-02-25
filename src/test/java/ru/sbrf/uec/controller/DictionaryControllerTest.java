package ru.sbrf.uec.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

import ru.sbrf.qrcode.events.dictionary.AllFuosSelectedEvent;
import ru.sbrf.qrcode.events.dictionary.AllRegionsSelectedEvent;
import ru.sbrf.qrcode.events.dictionary.AllStatusesSelectedEvent;
import ru.sbrf.qrcode.services.DictionaryEventHandler;
import ru.sbrf.uec.controller.DictionaryController;

public class DictionaryControllerTest {

	private MockMvc mockMvc;
	private MappingJackson2HttpMessageConverter msgConverter;

	@InjectMocks
	private DictionaryController controller;

	@Mock
	private DictionaryEventHandler dictionaryEventHandler;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		this.msgConverter = new MappingJackson2HttpMessageConverter();
		this.mockMvc = standaloneSetup(controller).setMessageConverters(this.msgConverter).build();
	}

	@Test
	public void testGetAllFuo() throws Exception {
		AllFuosSelectedEvent resultEvent = new AllFuosSelectedEvent();
		when(dictionaryEventHandler.getAllFuos()).thenReturn(resultEvent);

		mockMvc.perform(get("/dictionary/fuo"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	public void testLoadFuoDictionary() throws Exception {
		// throw new RuntimeException("not yet implemented");
	}

	@Test
	public void testGetAllRegions() throws Exception {
		AllRegionsSelectedEvent resultEvent = new AllRegionsSelectedEvent();
		when(dictionaryEventHandler.getAllRegions()).thenReturn(resultEvent);

		mockMvc.perform(get("/dictionary/region"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	public void testLoadRegionDictionary() throws Exception {
		// throw new RuntimeException("not yet implemented");
	}

	@Test
	public void testGetAllStatuses() throws Exception {
		AllStatusesSelectedEvent resultEvent = new AllStatusesSelectedEvent();
		when(dictionaryEventHandler.getAllStatuses()).thenReturn(resultEvent);

		mockMvc.perform(get("/dictionary/status"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	public void testLoadStatusDictionary() throws Exception {
		// throw new RuntimeException("not yet implemented");
	}

}
