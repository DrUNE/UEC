package ru.sbrf.uec.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ru.sbrf.uec.domain.RequestResult;
import ru.sbrf.uec.domain.ResultCode;
import ru.sbrf.uec.domain.User;

/**
 * @author sbt-koshenkova-mv
 * 
 * 15 февр. 2014 г. 22:38:50
 */
@Controller
public class LoginController {
	
	@RequestMapping(
            value = "/rest/login",
            method = RequestMethod.POST,
			consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody RequestResult login(@RequestBody User user) {
		RequestResult result = new RequestResult();
		result.setResultCode(ResultCode.SUCCESS);
		return result;
	}

}
