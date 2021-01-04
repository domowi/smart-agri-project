package com.smartagri.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.google.common.collect.Lists;
import com.smartagri.model.Predictions;
import com.smartagri.repository.PredictionsRepository;
import utils.Utils;

import java.util.*;

@RestController
public class PredictionsController {

	private final Logger LOG = LoggerFactory.getLogger(getClass().getName());

	private final PredictionsRepository predictionsRepository;

	@Autowired
	public PredictionsController(PredictionsRepository predictionsRepository) {
		this.predictionsRepository = predictionsRepository;
	}

	@RequestMapping(value = "/predictions/{predictionId}", method = RequestMethod.GET)
	public Predictions getNote(@PathVariable Long predictionId) {
		Predictions prediction = predictionsRepository.findOne(predictionId);
		LOG.info("Got prediction from DB: {}.", prediction);
		return prediction;
	}

	@RequestMapping(value = "/predictions/all", method = RequestMethod.GET)
	public List<Predictions> getAllPredictions() {
		List<Predictions> predictions = predictionsRepository.findAll();
		LOG.info("Getting all Data: {}.", predictions);
		return predictions;
	}

	@RequestMapping(value = "/predictions/", method = RequestMethod.GET)
	public List<Predictions> getCompletePredictions() {
		List<Predictions> predictions = predictionsRepository.findAll();
		LOG.info("Getting all Predictions: {}.", predictions);
		return predictions;
	}

	@RequestMapping(value = "/predictions/prediction", method = RequestMethod.GET)
	public List<Predictions> getPredictions() {
		List<Predictions> predictions = predictionsRepository.findAll();
		LOG.info("Getting all Data: {}.", predictions);
		return predictions;
	}

	@RequestMapping(value = "/predictions", method = RequestMethod.POST)
	public Predictions createPredictions(@Validated @RequestBody Predictions predictions) {
		LOG.info("Saving Predictions: {}.", predictions);
		predictionsRepository.save(predictions);
		LOG.info("Prediction saved: {}.", predictions);
		return predictions;
	}

	@RequestMapping(value = "/predictions", method = RequestMethod.PUT)
	public Predictions editPredictions(@RequestBody Predictions prediction) {
		LOG.info("Updating Prediction: {}.", prediction);
		predictionsRepository.save(prediction);
		LOG.info("Prediction updated: {}.", prediction);
		return prediction;
	}

	@RequestMapping(value = "/predictions/{predictionId}", method = RequestMethod.DELETE)
	public void deletePredictions(@PathVariable Long predictionId) {
		LOG.info("Deleting Predictions with ID {}.", predictionId);
		predictionsRepository.delete(predictionId);
		LOG.info("Predictions deleted.");
	}

	@RequestMapping(value = "/predictions/monthlyavgpricespredicts", method = RequestMethod.GET)
	public Map<String, Object> getMonthlyAvgRawNafisPredictedPrices() {

		List<Object[]> result = predictionsRepository.findMonthlyAvgPredictedNafisPrices();
		LOG.info("Getting Monthly Average Predictions Nafis Prices Data: {}.", result);

		Map<String, String> map = null;
		Map<String, String> finalResult = null;
		HashMap<String, Object> finalMap = new HashMap<>();
		List<Map> raws = new ArrayList<Map>();
		if (result != null && !result.isEmpty()) {
			for (Object[] object : result) {
				// LOG.info("Predictions Nafis Prices Data - Month: {}", object[0]);
				map = new HashMap<String, String>();

				// map.put("month", String.valueOf(object[0].toString()));
				map.put("year", object[0].toString()); // This handles int's
				map.put("month", String.valueOf(object[1]));
				map.put("nafis_price", String.valueOf(object[2]));
				map.put("predicted_price", String.valueOf(object[3]));
				LOG.info("Getting Monthly Average Predictions Nafis Prices Map Collection: {}.", map);

				// Add to list
				raws.add(map);
			}
		}
		finalMap.put("predictions", raws);

		return finalMap;

	}

	@RequestMapping(value = "/predictions/dailynafispredictions", method = RequestMethod.GET)
	public List<Map> getSortedDailyNafisPredictions() {

		List<Object[]> result = predictionsRepository.getSortedDailyNafisPredictions();
		LOG.info("Getting Daily Nafis Predictions Prices Data: {}.", result);

		Map<String, String> map = null;
		List<Map> predictions = new ArrayList<Map>();

		if (result != null && !result.isEmpty()) {
			for (Object[] object : result) {
				map = new HashMap<String, String>();
				map.put("nafis_date", object[0].toString());
				map.put("nafis_price", String.valueOf(object[1]));
				map.put("predicted_price", String.valueOf(object[2]));
				map.put("avg_temp", String.valueOf(object[3]));
				map.put("precipitation", String.valueOf(object[4]));
				map.put("inflation", String.valueOf(object[5]));
				LOG.info("Getting Daily Nafis Predictions Prices Map Collection: {}.", map);

				// Add to list
				predictions.add(map);
			}
		}
		return predictions;

	}

	//////////////////////////////////// NAIROBI ////////////////////////////////////////////////

	@RequestMapping(value = "/predictions/avgnairobipredictionspast", method = RequestMethod.GET)
	public List<Map> getAverageNairobiPredictionsPast() {

		List<Object[]> result = predictionsRepository.getAvgNairobiNafisPredictionsPast();
		LOG.info("Getting Average Nairobi Predictions Prices Past Data: {}.", result);

		Map<String, String> map = null;
		List<Map> predictions = new ArrayList<Map>();
		List<Map> predictions_reversed = new ArrayList<Map>();

		if (result != null && !result.isEmpty()) {
			for (Object[] object : result) {
				map = new HashMap<String, String>();
				map.put("year", String.valueOf(object[0]));
				map.put("month", String.valueOf(object[1]));
				map.put("nafisprices", String.valueOf(object[2]));
				map.put("predictions", String.valueOf(object[3]));

				LOG.info("Getting Average Nairobi Predictions Prices Past Map Collection: {}.", map);

				// Add to list
				predictions.add(map);
				predictions_reversed = Lists.reverse(predictions);
				//Collections.reverse(List<?> list);
				//predictions_reversed = Collections.reverse(predictions);
			}
		}
		return predictions_reversed;
	}

	@RequestMapping(value = "/predictions/dailynairobinafisvspredictions", method = RequestMethod.GET)
	public List<Map> getDailyNairobiNafisVSPredictions() {

		List<Object[]> result = predictionsRepository.getDailyNairobiNafisPredictionsComp();
		LOG.info("Getting Daily Nairobi Nafis Vs Predictions Prices Data: {}.", result);

		Map<String, String> map = null;
		List<Map> predictions = new ArrayList<Map>();

		if (result != null && !result.isEmpty()) {
			for (Object[] object : result) {
				map = new HashMap<String, String>();
				map.put("nafis_date", String.valueOf(object[0]));
				map.put("nafisprices", String.valueOf(object[1]));
				map.put("predictions", String.valueOf(object[2]));
				map.put("avg_temp", String.valueOf(object[3]));
				map.put("precipitation_mm", String.valueOf(object[4]));
				map.put("annual_avg_inflation", String.valueOf(object[5]));

				LOG.info("Getting Daily Nairobi Nafis Vs Predictions Prices Map Collection: {}.", map);

				// Add to list
				predictions.add(map);
			}
		}
		return predictions;

	}

	//////////////////////////////////// NAIROBI - END ////////////////////////////////////

	//////////////////////////////////// MOMBASA //////////////////////////////////////////

	@RequestMapping(value = "/predictions/avgmombasapredictionspast", method = RequestMethod.GET)
	public List<Map> getAverageMombasaPredictionsPast() {

		List<Object[]> result = predictionsRepository.getAvgMombasaNafisPredictionsPast();
		LOG.info("Getting Average Mombasa Predictions Prices Past Data: {}.", result);

		Map<String, String> map = null;
		List<Map> predictions = new ArrayList<Map>();
		List<Map> predictions_reversed = new ArrayList<Map>();

		if (result != null && !result.isEmpty()) {
			for (Object[] object : result) {
				map = new HashMap<String, String>();
				map.put("year", String.valueOf(object[0]));
				map.put("month", String.valueOf(object[1]));
				map.put("nafisprices", String.valueOf(object[2]));
				map.put("predictions", String.valueOf(object[3]));

				LOG.info("Getting Average Mombasa Predictions Prices Past Map Collection: {}.", map);

				// Add to list
				predictions.add(map);
				predictions_reversed = Lists.reverse(predictions);
			}
		}
		return predictions_reversed;
		//return predictions;

	}

	@RequestMapping(value = "/predictions/dailymombasanafisvspredictions", method = RequestMethod.GET)
	public List<Map> getDailyMombasaNafisVSPredictions() {

		List<Object[]> result = predictionsRepository.getDailyMombasaNafisPredictionsComp();
		LOG.info("Getting Daily Mombasa Nafis Vs Predictions Prices Data: {}.", result);

		Map<String, String> map = null;
		List<Map> predictions = new ArrayList<Map>();

		if (result != null && !result.isEmpty()) {
			for (Object[] object : result) {
				map = new HashMap<String, String>();
				map.put("nafis_date", String.valueOf(object[0]));
				map.put("nafisprices", String.valueOf(object[1]));
				map.put("predictions", String.valueOf(object[2]));
				map.put("avg_temp", String.valueOf(object[3]));
				map.put("precipitation_mm", String.valueOf(object[4]));
				map.put("annual_avg_inflation", String.valueOf(object[5]));

				LOG.info("Getting Daily Mombasa Nafis Vs Predictions Prices Map Collection: {}.", map);

				// Add to list
				predictions.add(map);
			}
		}
		return predictions;

	}

	//////////////////////////////////// MOMBASA - END //////////////////////////////////////////////

	//////////////////////////////////// KISUMU /////////////////////////////////////////////////////

	@RequestMapping(value = "/predictions/avgkisumupredictionspast", method = RequestMethod.GET)
	public List<Map> getAverageKisumuPredictionsPast() {

		List<Object[]> result = predictionsRepository.getAvgKisumuNafisPredictionsPast();
		LOG.info("Getting Average Kisumu Predictions Prices Past Data: {}.", result);

		Map<String, String> map = null;
		List<Map> predictions = new ArrayList<Map>();
		List<Map> predictions_reversed = new ArrayList<Map>();

		if (result != null && !result.isEmpty()) {
			for (Object[] object : result) {
				map = new HashMap<String, String>();
				map.put("year", String.valueOf(object[0]));
				map.put("month", String.valueOf(object[1]));
				map.put("nafisprices", String.valueOf(object[2]));
				map.put("predictions", String.valueOf(object[3]));

				LOG.info("Getting Average Kisumu Predictions Prices Past Map Collection: {}.", map);

				// Add to list
				predictions.add(map);
				predictions_reversed = Lists.reverse(predictions);
				//predictions_reversed = Collections.reverse(predictions);
				//Utils.reverseList(predictions);

			}
		}
		return predictions_reversed;
	}


	@RequestMapping(value = "/predictions/dailykisumunafisvspredictions", method = RequestMethod.GET)
	public List<Map> getDailyKisumuNafisVSPredictions() {

		List<Object[]> result = predictionsRepository.getDailyKisumuNafisPredictionsComp();
		LOG.info("Getting Daily Kisumu Nafis Vs Predictions Prices Data: {}.", result);

		Map<String, String> map = null;
		List<Map> predictions = new ArrayList<Map>();

		if (result != null && !result.isEmpty()) {
			for (Object[] object : result) {
				map = new HashMap<String, String>();
				map.put("nafis_date", String.valueOf(object[0]));
				map.put("nafisprices", String.valueOf(object[1]));
				map.put("predictions", String.valueOf(object[2]));
				map.put("avg_temp", String.valueOf(object[3]));
				map.put("precipitation_mm", String.valueOf(object[4]));
				map.put("annual_avg_inflation", String.valueOf(object[5]));

				LOG.info("Getting Daily Kisumu Nafis Vs Predictions Prices Map Collection: {}.", map);

				// Add to list
				predictions.add(map);
			}
		}
		return predictions;

	}

	//////////////////////////////////// KISUMU - END /////////////////////////////////////////////////////

	//////////////////////////////////// START - SEARCH /////////////////////////////////////////////////////

	@RequestMapping(value = "/predictions/getsearchpredictionpriceslist", method = RequestMethod.GET)
	public List<Map> getSearchPredictionPricesList(String county, String startDate, String endDate) {

		LOG.info("getSearchPredictionPricesList :: County: " + county + " - Start Date: " + startDate + " - End Date: " + endDate);
		List<Object[]> result = predictionsRepository.getSearchPredictionPricesList(189);
		//List<Object[]> result = predictionsRepository.getSearchPredictionPricesList(county, startDate, endDate);
		LOG.info("Getting Search Predictions Prices Data: {}.", result);

		Map<String, String> map = null;
		List<Map> predictions = new ArrayList<Map>();

		if (result != null && !result.isEmpty()) {
			for (Object[] object : result) {
				map = new HashMap<String, String>();
				map.put("nafis_date", String.valueOf(object[0]));
				map.put("nafis_prices", String.valueOf(object[1]));
				map.put("predictions", String.valueOf(object[2]));
				map.put("county", String.valueOf(object[3]));
				map.put("avg_temp", String.valueOf(object[4]));
				map.put("precipitation_mm", String.valueOf(object[5]));
				map.put("annual_avg_inflation", String.valueOf(object[6]));

				LOG.info("Getting Search Predictions Prices Map Collection: {}.", map);

				// Add to list
				predictions.add(map);
			}
		}
		return predictions;

	}

		//////////////////////////////////// SEARCH - END /////////////////////////////////////////////////////

}
