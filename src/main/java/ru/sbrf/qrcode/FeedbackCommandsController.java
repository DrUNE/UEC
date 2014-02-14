package ru.sbrf.qrcode;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

/**
 * Created by sbt-litvinov-ay on 13.02.14.
 */
@Controller
@RequestMapping("/rest/feedbacks")
public class FeedbackCommandsController {

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Feedback> createFeedback(@RequestBody Feedback feedback, UriComponentsBuilder builder) {

        feedback.setId(UUID.randomUUID());
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                builder.path("/rest/feedbacks/{id}")
                        .buildAndExpand(feedback.getId().toString()).toUri());

        return new ResponseEntity<Feedback>(feedback, headers, HttpStatus.CREATED);
    }
}
