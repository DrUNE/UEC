package ru.sbrf.qrcode;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.Mockito.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by sbt-litvinov-ay on 14.02.14.
 */
public class FeedbackCommandsControllerTest {
    MockMvc mockMvc;

    @InjectMocks
    FeedbackCommandsController controller;

    UUID key = UUID.fromString("f3512d26-72f6-4290-9265-63ad69eccc13");

    MappingJackson2HttpMessageConverter jsonMessageConverter = new MappingJackson2HttpMessageConverter();
    Jaxb2RootElementHttpMessageConverter xmlMessageConverter = new Jaxb2RootElementHttpMessageConverter();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        this.mockMvc = standaloneSetup(controller)
            .setMessageConverters(jsonMessageConverter, xmlMessageConverter).build();

    }

    @Test
    public void thatCreateFeedbackRendersJsonCorrectly() throws Exception {
        this.mockMvc.perform(
                post("/rest/feedbacks")
                    .content(jsonOf(standardFeedback()))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$.fullName").value(standardFeedback().getFullName()))
                .andExpect(jsonPath("$.message").value(standardFeedback().getMessage()));
    }

    private Feedback standardFeedback() {
        Feedback newFeedback = new Feedback();
        newFeedback.setFullName("Charlie Sheen");
        newFeedback.setMessage("Хорошая идея для улучшения сайта.");
        newFeedback.setEmail("charlie121@gmail.com");
        return newFeedback;
    }

    private String jsonOf(Object o){
        try {
            return jsonMessageConverter.getObjectMapper().writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
