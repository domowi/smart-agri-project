package com.smartagri.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.smartagri.model.Raws;
import com.smartagri.repository.RawsRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RawsController {

	private final Logger LOG = LoggerFactory.getLogger(getClass().getName());

	private final RawsRepository rawsRepository;

	@Autowired
	public RawsController(RawsRepository rawsRepository) {
		this.rawsRepository = rawsRepository;
	}

	@RequestMapping(value = "/raws/{rawsId}", method = RequestMethod.GET)
	public Raws getRaws(@PathVariable Long rawsId) {
		Raws raws = rawsRepository.findOne(rawsId);
		LOG.info("Got note from DB: {}.", raws);
		return raws;
	}

	@RequestMapping(value = "/raws/all", method = RequestMethod.GET)
	public List<Raws> getAllRaws() {
		List<Raws> raws = rawsRepository.findAll();
		LOG.info("Getting all Data: {}.", raws);
		return raws;
	}

	// @CrossOrigin(origins = "http://localhost:4205")
	@RequestMapping(value = "/raws/", method = RequestMethod.GET)
	public List<Raws> getCompleteRaws() {
		List<Raws> raws = rawsRepository.findAll();
		LOG.info("Getting all Data: {}.", raws);
		return raws;
	}

	/*
	 * @RequestMapping(value = "/raws/monthlyavgprices", method = RequestMethod.GET)
	 * public List<Raws> getMonthlyAvgRawNafisPrices() { List<Raws> raws =
	 * rawsRepository.findMonthlyAvgRawNafisPrices();
	 * LOG.info("Getting Monthly Average Raw Nafis Prices Data: {}.", raws); return
	 * raws; }
	 */

	@RequestMapping(value = "/raws/monthlyavgprices", method = RequestMethod.GET)
	public Map<String, Object> getMonthlyAvgRawNafisPrices() {
		List<Object[]> result = rawsRepository.findMonthlyAvgRawNafisPrices();
		//LOG.info("Getting Monthly Average Raw Nafis Prices Data: {}.", result);
		//List<Raws> result = rawsRepository.findMonthlyAvgRawNafisPrices();
		LOG.info("Getting Monthly Average Raw Nafis Prices Data: {}.", result);

		Map<String, String> map = null; 
		Map<String, String> finalResult = null; 
		//LinkedHashMap<String, Object> finalMap = new LinkedHashMap<>();
		HashMap<String, Object> finalMap = new HashMap<>();
		List<Map> raws = new ArrayList<Map>();
		if (result != null && !result.isEmpty()) {
			for (Object[] object : result) {
				LOG.info("Raw Nafis Prices Data - Month: {}", object[1].toString());
				LOG.info("Raw Nafis Prices Data - Price: {}", object[0]);
				map = new HashMap<String, String>();
				
				map.put("month", object[1].toString());
				map.put("price", String.valueOf(object[0]));
				raws.add(map);
			}
		}
		finalMap.put("Raws", raws);
		
//		Map<String, String> map
//		if (result != null && !result.isEmpty()) {
//			map = new HashMap<String, String>();
//			for (Object[] object : result) {
//				//map.put((String) object[0], (String) object[1]);
//				map.put("month", String.valueOf(((Integer) object[1]).intValue()));
//				map.put("price", (String)object[0]);
//			}
//		}

		// String Raws = "";
		//
		// Map<String, String> map = null;
		//
		// List<Raws> result = rawsRepository.findMonthlyAvgRawNafisPrices();
		// if (!result.isEmpty()) {
		//
		// Gson gsonBuilder = new GsonBuilder().create();
		// String statusResultsArray = gsonBuilder.toJson(result);
		// JSONArray formattedJsonResult = (JSONArray)
		// JSONValue.parse(statusResultsArray);
		//
		// //Accounts = ((JSONObject)
		// formattedJsonResult.get(0)).get("ACCT_SEE").toString();
		// Raws = formattedJsonResult.toString();
		//
		// map = new HashMap<String, String>();
		// map.put("Raws", Raws);
		//
		// }
		return finalMap;

	}

	@RequestMapping(value = "/raws/raws", method = RequestMethod.GET)
	public List<Raws> getRaws() {
		List<Raws> raws = rawsRepository.findAll();
		LOG.info("Getting all Data: {}.", raws);
		return raws;
	}

	@RequestMapping(value = "/raws", method = RequestMethod.POST)
	public Raws createRaws(@Validated @RequestBody Raws raws) {
		LOG.info("Saving Raws: {}.", raws);
		rawsRepository.save(raws);
		LOG.info("Raws saved: {}.", raws);
		return raws;
	}

	@RequestMapping(value = "/raws", method = RequestMethod.PUT)
	public Raws editRaws(@RequestBody Raws raws) {
		LOG.info("Updating Raws: {}.", raws);
		rawsRepository.save(raws);
		LOG.info("Raws updated: {}.", raws);
		return raws;
	}

	@RequestMapping(value = "/raws/{rawsId}", method = RequestMethod.DELETE)
	public void deleteRaws(@PathVariable Long rawsId) {
		LOG.info("Deleting Raws with ID {}.", rawsId);
		rawsRepository.delete(rawsId);
		LOG.info("Raws deleted.");
	}
	
	@RequestMapping(value = "/raws/fetch", method = RequestMethod.GET)
	public List<Map> searchRaws(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam("startDate") Date startDate,
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam("endDate") Date endDate) {
		
		List<Object[]> result = rawsRepository.fetchRawNafisPrices(startDate, endDate);
		LOG.info("Getting Daily Raw Nafis Prices Data: {}.", result);

		Map<String, String> map = null; 
		List<Map> raws = new ArrayList<Map>();
		if (result != null && !result.isEmpty()) {
			for (Object[] object : result) {
				map = new HashMap<String, String>();
				
				map.put("date", String.valueOf(object[0]));
				map.put("county", String.valueOf(object[1]));
				map.put("price", String.valueOf(object[2]));
				
				raws.add(map);
			}
		}
		return raws;
	}
	
	// /employees/234/paystubs?startDate=2000-10-31&enddate=2000-10-31
	// @CrossOrigin(origins = "http://localhost:4205")
	//@RequestMapping(value = "/raws/{countyId}/", method = RequestMethod.GET)
//	@RequestMapping(value = "/raws/{countyId}/", method = RequestMethod.GET)
//	public List<Map> searchCompleteRaws(@PathVariable("countyId") String countyId, 
//			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam("startDate") LocalDate startDate,
//			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam("endDate") LocalDate endDate) {
//		List<Object[]> result = rawsRepository.fetchRawNafisPrices(startDate, endDate);
//		LOG.info("Getting Monthly Average Raw Nafis Prices Data: {}.", result);
//
//		Map<String, String> map = null; 
//		List<Map> raws = new ArrayList<Map>();
//		if (result != null && !result.isEmpty()) {
//			for (Object[] object : result) {
//				LOG.info("Raw Nafis Prices Data - County: {}", object[0].toString());
//				LOG.info("Raw Nafis Prices Data - Date: {}", object[1].toString());
//				LOG.info("Raw Nafis Prices Data - Price: {}", object[2].toString());
//				map = new HashMap<String, String>();
//				
//				map.put("county", object[0].toString());
//				map.put("date", String.valueOf(object[1]));
//				map.put("price", String.valueOf(object[2]));
//				raws.add(map);
//			}
//		}
//		return raws;
//	}	
	
}
