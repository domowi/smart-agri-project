package com.smartagri.controller;

import com.smartagri.model.Prices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.smartagri.model.FuturePredictions;
import com.smartagri.repository.FuturePredictionsRepository;

import java.util.*;
import java.util.stream.Stream;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
public class FuturePredictionsController {

	private final Logger LOG = LoggerFactory.getLogger(getClass().getName());

	private final FuturePredictionsRepository futurePredictionsRepository;

	@Autowired
	public FuturePredictionsController(FuturePredictionsRepository futurePredictionsRepository) {
		this.futurePredictionsRepository = futurePredictionsRepository;
	}

	@RequestMapping(value = "/futurePredictions/{predictionId}", method = RequestMethod.GET)
	public FuturePredictions getFuturePredictions(@PathVariable Long predictionId) {
		FuturePredictions prediction = futurePredictionsRepository.findOne(predictionId);
		LOG.info("Got prediction from DB: {}.", prediction);
		return prediction;
	}

	@RequestMapping(value = "/futurePredictions/all", method = RequestMethod.GET)
	public List<FuturePredictions> getAllFuturePredictions() {
		List<FuturePredictions> futurePredictions = futurePredictionsRepository.findAll();
		LOG.info("Getting all Data: {}.", futurePredictions);
		return futurePredictions;
	}

	@RequestMapping(value = "/futurePredictions/", method = RequestMethod.GET)
	public List<FuturePredictions> getCompleteFuturePredictions() {
		List<FuturePredictions> futurePredictions = futurePredictionsRepository.findAll();
		LOG.info("Getting all FuturePredictions: {}.", futurePredictions);
		return futurePredictions;
	}

	@RequestMapping(value = "/futurePredictions/prediction", method = RequestMethod.GET)
	public List<FuturePredictions> getFuturePredictions() {
		List<FuturePredictions> futurePredictions = futurePredictionsRepository.findAll();
		LOG.info("Getting all Data: {}.", futurePredictions);
		return futurePredictions;
	}

	@RequestMapping(value = "/futurePredictions", method = RequestMethod.POST)
	public FuturePredictions createFuturePredictions(@Validated @RequestBody FuturePredictions futurePredictions) {
		LOG.info("Saving FuturePredictions: {}.", futurePredictions);
		futurePredictionsRepository.save(futurePredictions);
		LOG.info("Prediction saved: {}.", futurePredictions);
		return futurePredictions;
	}

	@RequestMapping(value = "/futurePredictions", method = RequestMethod.PUT)
	public FuturePredictions editFuturePredictions(@RequestBody FuturePredictions prediction) {
		LOG.info("Updating Prediction: {}.", prediction);
		futurePredictionsRepository.save(prediction);
		LOG.info("Prediction updated: {}.", prediction);
		return prediction;
	}

	@RequestMapping(value = "/futurePredictions/{predictionId}", method = RequestMethod.DELETE)
	public void deleteFuturePredictions(@PathVariable Long predictionId) {
		LOG.info("Deleting FuturePredictions with ID {}.", predictionId);
		futurePredictionsRepository.delete(predictionId);
		LOG.info("FuturePredictions deleted.");
	}
	
	@RequestMapping(value = "/futurepredictions/averagefuturepredictions", method = RequestMethod.GET)
	public List<Map> getAverageFuturePredictions() {
		
		List<Object[]> result = futurePredictionsRepository.getFuturePredictionAverages();
		LOG.info("Getting Average FuturePredictions Prices Data: {}.", result);

		Map<String, String> map = null;
		List<Map> futurePredictions = new ArrayList<Map>();
				
		if (result != null && !result.isEmpty()) {
			for (Object[] object : result) {
				map = new HashMap<String, String>();
				map.put("county", String.valueOf(object[0]));
				map.put("prediction", String.valueOf(object[1]));
				LOG.info("Getting Future Average Future Predictions Prices Map Collection: {}.", map);
				
				// Add to list
				futurePredictions.add(map);
			}
		}
		return futurePredictions;

	}
	
	@RequestMapping(value = "/futurepredictions/averagenairobifuturepredictions", method = RequestMethod.GET)
	public List<Map> getAverageNairobiFuturePredictions() {
		
		List<Object[]> result = futurePredictionsRepository.getNairobiFuturePredictionAverages();
		LOG.info("Getting Average Nairobi Future Predictions Prices Data: {}.", result);

		Map<String, String> map = null;
		List<Map> futurePredictions = new ArrayList<Map>();
				
		if (result != null && !result.isEmpty()) {
			for (Object[] object : result) {
				map = new HashMap<String, String>();
				map.put("county", String.valueOf(object[0]));
				map.put("prediction", String.valueOf(object[1]));
				LOG.info("Getting Future Average Nairobi Future Predictions Prices Map Collection: {}.", map);
				
				// Add to list
				futurePredictions.add(map);
			}
		}
		return futurePredictions;

	}
	
	@RequestMapping(value = "/futurepredictions/averagemombasafuturepredictions", method = RequestMethod.GET)
	public List<Map> getMombasaAverageFuturePredictions() {
		
		List<Object[]> result = futurePredictionsRepository.getMombasaFuturePredictionAverages();
		LOG.info("Getting Average Mombasa Future Predictions Prices Data: {}.", result);

		Map<String, String> map = null;
		List<Map> futurePredictions = new ArrayList<Map>();
				
		if (result != null && !result.isEmpty()) {
			for (Object[] object : result) {
				map = new HashMap<String, String>();
				map.put("county", String.valueOf(object[0]));
				map.put("prediction", String.valueOf(object[1]));
				LOG.info("Getting Future Average Mombasa Future Predictions Prices Map Collection: {}.", map);
				
				// Add to list
				futurePredictions.add(map);
			}
		}
		return futurePredictions;

	}
	
	@RequestMapping(value = "/futurepredictions/averagekisumufuturepredictions", method = RequestMethod.GET)
	public List<Map> getKisumuAverageFuturePredictions() {
		
		List<Object[]> result = futurePredictionsRepository.getKisumuAverageFuturePredictions();
		LOG.info("Getting Average Kisumu Future Predictions Prices Data: {}.", result);

		Map<String, String> map = null;
		List<Map> futurePredictions = new ArrayList<Map>();
				
		if (result != null && !result.isEmpty()) {
			for (Object[] object : result) {
				map = new HashMap<String, String>();
				map.put("county", String.valueOf(object[0]));
				map.put("prediction", String.valueOf(object[1]));
				LOG.info("Getting Future Average Kisumu Future Predictions Prices Map Collection: {}.", map);
				
				// Add to list
				futurePredictions.add(map);
			}
		}
		return futurePredictions;

	}
	
	@RequestMapping(value = "/futurepredictions/dailyfuturepredictions", method = RequestMethod.GET)
	public List<Map> getDailyFuturePredictions() {
		
		List<Object[]> result = futurePredictionsRepository.getDailyFuturePredictions();
		LOG.info("Getting Daily Future Predictions Prices Data: {}.", result);

		Map<String, String> map = null;
		List<Map> futurePredictions = new ArrayList<Map>();
				
		if (result != null && !result.isEmpty()) {
			for (Object[] object : result) {
				map = new HashMap<String, String>();
				map.put("county", String.valueOf(object[0]));
				map.put("prediction_date", String.valueOf(object[1]));
				map.put("prediction_year", String.valueOf(object[2]));
				map.put("prediction_month", String.valueOf(object[3]));
				map.put("prediction_month_int", String.valueOf(object[4]));
				map.put("prediction_price", String.valueOf(object[5]));
				LOG.info("Getting Daily Future Predictions Prices Map Collection: {}.", map);
				
				// Add to list
				futurePredictions.add(map);
			}
		}
		return futurePredictions;

	}

	@RequestMapping(value = "/futurepredictions/cumulativefuturepredictions", method = RequestMethod.GET)
	public List<Map> getCumulativeFuturePredictions() {
	// public Set getCumulativeFuturePredictions() {
		
		List<Object[]> result = futurePredictionsRepository.getCumulativeFuturePredictions();
		LOG.info("Getting Cumulative Future Predictions Prices Data: {}.", result);

		Map<String, String> map = null;
		List<Map> futurePredictions = new ArrayList<Map>();

		Set<Prices> setOfNafisDates = new HashSet();
		List<Prices> listOfDates = new ArrayList<>();

		Comparator<Prices> pricesComparator = null;


		/**
		 - Loop through result set and get the date
		 - Insert the date in an ArrayList
		 - Using date as the key, loop through again and
		 */

		if (result != null && !result.isEmpty()) {
			for (Object[] object : result) {
				//setOfNafisDates.add(String.valueOf(object[0]));
				//LOG.info("Getting Daily Future Predictions Prices Map Collection: {}.", setOfNafisDates);

				setOfNafisDates.add(new Prices(String.valueOf(object[0])));

				// https://www.callicoder.com/java-comparable-comparator/
				pricesComparator = new Comparator<Prices>() {

					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					Date date1, date2;

					@Override
					public int compare(Prices pred1, Prices pred2) {

						try {
							date1 = sdf.parse(pred1.getNafisDate());
							date2 = sdf.parse(pred2.getNafisDate());
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return date1.compareTo(date2);

					}

				};




			}
		}
		LOG.info("Convert Set to List values .....");
		listOfDates = new ArrayList<Prices>(setOfNafisDates);

		Collections.sort(listOfDates, pricesComparator);
		LOG.info("\nPrices (Sorted by Date) : " + listOfDates.toString());

		LinkedHashSet<Prices> hashSet = new LinkedHashSet<>(listOfDates);

		ArrayList<Prices> sanitizedListOfDates = new ArrayList<>(hashSet);

		// loop through the set and populate a map of values
		// assign
		// sort the dates from older to current

		String nrb = null, msa = null, ksm = null;
		String rsDate = null;

		try {

		Iterator iterator = sanitizedListOfDates.iterator();
		while(iterator.hasNext()){		

			String nafisDate = iterator.next().toString();
			LOG.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>: {}.", nafisDate);

			map = new HashMap<String, String>();

			for (Object[] object : result) {

				rsDate = String.valueOf(object[0]);

				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date dateInSet, dateInResultSet;

				dateInSet = sdf.parse(nafisDate);
			    dateInResultSet = sdf.parse(rsDate);

				if(dateInSet.compareTo(dateInResultSet) == 0){

					nrb = String.valueOf(object[2]);
					if(!isNullOrEmpty(nrb)){
						map.put("nairobi", nrb);
					}					
					
					msa = String.valueOf(object[3]);
					if(!isNullOrEmpty(msa)){
						map.put("mombasa", msa);
					}
					
					ksm = String.valueOf(object[4]);
					if(!isNullOrEmpty(ksm)){
						map.put("kisumu", ksm);
					} else {

					}

				}

				// convert nafis date to String and add to map
				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String formattedNafisDate = formatter.format(dateInSet);

				map.put("nafis_date", formattedNafisDate);
				//LOG.info("Getting Cumulative Future Predictions Prices Map Collection: {}.", map);

			}

			if(futurePredictions.contains(map)) {
				continue;
			}else {
				// Add to list
				futurePredictions.add(map);
			}

		}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		//LOG.info("Getting Cumulative Future Predictions Prices Collection: {} >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>", futurePredictions);
		return futurePredictions;

		//LOG.info("Getting Cumulative Future Predictions Prices Collection: {} >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>", setOfNafisDates);
		//return setOfNafisDates;

	}

	/**
	 * Function to check if String value is null or empty
	 * @param str
	 * @return
	 */
	public static boolean isNullOrEmpty(String str) {

		// https://www.programiz.com/java-programming/examples/string-empty-null

		/*
		if(str != null && !str.isEmpty())
			return false;
		return true;
		*/
		if(str != "null")
			return false;
		return true;
	}

}
