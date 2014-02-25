package ru.sbrf.qrcode.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ru.sbrf.qrcode.events.dictionary.AllFuosSelectedEvent;
import ru.sbrf.qrcode.events.dictionary.AllRegionsSelectedEvent;
import ru.sbrf.qrcode.events.dictionary.AllStatusesSelectedEvent;
import ru.sbrf.qrcode.events.dictionary.FuoDetails;
import ru.sbrf.qrcode.events.dictionary.RegionDetails;
import ru.sbrf.qrcode.events.dictionary.StatusDetails;
import ru.sbrf.qrcode.json.Fuo;
import ru.sbrf.qrcode.json.Region;
import ru.sbrf.qrcode.json.Status;
import ru.sbrf.qrcode.services.DictionaryEventHandler;

/**
 * Обрабока действий проводимых со справочниками
 * 
 * @author sbt-koshenkova-mv
 * 
 */
@Controller
@RequestMapping("/dictionary")
public class DictionaryController {

	@Autowired
	private DictionaryEventHandler dictionaryEventHandler;

	/**
	 * выборка из справочника ФУО
	 * @return
	 */
	@RequestMapping(value = "/fuo", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<List<Fuo>> getAllFuo() {
		List<Fuo> fuos = new ArrayList<Fuo>();

		AllFuosSelectedEvent selectedEvent = dictionaryEventHandler.getAllFuos();
		for (FuoDetails fuoDetails : selectedEvent.getFuos())
			fuos.add(Fuo.fromFuoDetails(fuoDetails));

		return new ResponseEntity<List<Fuo>>(fuos, HttpStatus.OK);
	}

	/**
	 * 
	 */
	@RequestMapping(value = "/fuo", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_XML_VALUE })
	public void loadFuoDictionary(Object o) {
		// TODO: определиться с форматом принимаемых данных
	}

	/**
	 * 
	 */
	@RequestMapping(value = "/region", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<List<Region>> getAllRegions() {
		List<Region> regions = new ArrayList<Region>();
		
		AllRegionsSelectedEvent selectedEvent = dictionaryEventHandler.getAllRegions();
		for (RegionDetails regionDetails : selectedEvent.getRegions())
			regions.add(Region.fromRegionDetails(regionDetails));

		return new ResponseEntity<List<Region>>(regions, HttpStatus.OK);
	}

	/**
	 * 
	 */
	@RequestMapping(value = "/region", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_XML_VALUE })
	public void loadRegionDictionary(Object o) {
		// TODO: определиться с форматом принимаемых данных
	}

	/**
	 * получение статусов заявок
	 */
	@RequestMapping(value = "/status", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<List<Status>> getAllStatuses() {
		List<Status> statuses = new ArrayList<Status>();
		
		AllStatusesSelectedEvent selectedEvent = dictionaryEventHandler.getAllStatuses();
		for(StatusDetails statusDetails:selectedEvent.getStatuses())
			statuses.add(Status.fromStatusDetails(statusDetails));
		
		return new ResponseEntity<List<Status>>(statuses, HttpStatus.OK);
	}

	/**
	 * загрузка справочника статусов
	 */
	@RequestMapping(value = "/status", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_XML_VALUE })
	public void loadStatusDictionary(Object o) {
		// TODO: определиться с форматом принимаемых данных
	}

}
