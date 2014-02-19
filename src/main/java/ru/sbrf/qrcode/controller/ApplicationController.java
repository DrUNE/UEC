package ru.sbrf.qrcode.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ru.sbrf.qrcode.events.application.ApplicationDetails;
import ru.sbrf.qrcode.events.application.ApplicationsSelectedEvent;
import ru.sbrf.qrcode.events.application.SelectApplicationEvent;
import ru.sbrf.qrcode.json.Application;
import ru.sbrf.qrcode.json.ApplicationFilter;
import ru.sbrf.qrcode.services.ApplicationEventHandler;

/**
 * Отображение списка заявок и поиск заявок на выдачу карт
 * 
 * @author sbt-koshenkova-mv
 *
 */
@Controller
@RequestMapping("/application")
public class ApplicationController {
	
	@Autowired
	private ApplicationEventHandler applicationEventHandler;
	
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Application>> getApplicationsByFilter(@RequestBody ApplicationFilter applicationFilter){
		List<Application> apps = new ArrayList<Application>();
		
		SelectApplicationEvent selectApplicationEvent = new SelectApplicationEvent();
		selectApplicationEvent.setSin(applicationFilter.getSin());
		selectApplicationEvent.setDate(applicationFilter.getDate());
		selectApplicationEvent.setRegionId(applicationFilter.getRegionId());
		selectApplicationEvent.setUosId(applicationFilter.getUosId());
		selectApplicationEvent.setStatusId(applicationFilter.getStatusId());
		
		ApplicationsSelectedEvent selectedEvent = applicationEventHandler.getApplicationsByFilter(selectApplicationEvent); 
		for(ApplicationDetails applicationDetails : selectedEvent.getApps())
			apps.add(Application.fromApplicationDetails(applicationDetails));
		
		return new ResponseEntity<List<Application>>(apps, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{sin}", method = RequestMethod.GET)
	public ResponseEntity<Application> getApplicationById(@PathVariable String sin){
		//TODO пока хватит
		return new ResponseEntity<Application>(HttpStatus.OK);
	}

}
