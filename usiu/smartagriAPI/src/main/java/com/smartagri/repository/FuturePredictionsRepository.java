

package com.smartagri.repository;

import com.smartagri.model.FuturePredictions;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.*;

/**
 * Spring Data JPA repository for the Future Predictions entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FuturePredictionsRepository extends JpaRepository<FuturePredictions, Long> {
		
	//Get the average amount of future prediction
	String futurePredictionAverages = "SELECT county, (FLOOR(AVG(predictions) / 10) * 10) AS "
			+ "avg_future_prediction FROM FuturePredictions";
	@Query(value = futurePredictionAverages)
	List<Object[]> getFuturePredictionAverages();
	
	//Get the average amount of future prediction for Nairobi
	String futureNairobiPredictionAverages = "SELECT county, (FLOOR(AVG(predictions) / 10) * 10) AS "
			+ "avg_future_prediction FROM FuturePredictions WHERE county = 'Nairobi'";
	@Query(value = futureNairobiPredictionAverages)
	List<Object[]> getNairobiFuturePredictionAverages();
	
	//Get the average amount of future prediction for Mombasa
	String futureMombasaPredictionAverages = "SELECT county, (FLOOR(AVG(predictions) / 10) * 10) AS "
			+ "avg_future_prediction FROM FuturePredictions WHERE county = 'Mombasa'";
	@Query(value = futureMombasaPredictionAverages)
	List<Object[]> getMombasaFuturePredictionAverages();
	
	//Get the average amount of future prediction for Kisumu
	String futureKisumuPredictionAverages = "SELECT county, (FLOOR(AVG(predictions) / 10) * 10) AS "
			+ "avg_future_prediction FROM FuturePredictions WHERE county = 'Kisumu'";
	@Query(value = futureKisumuPredictionAverages)
	List<Object[]> getKisumuAverageFuturePredictions();
	
	//Get the daily future price prediction by counties
	String dailyFuturePredictions = "SELECT county, DATE(prediction_date) AS prediction_date, YEAR(prediction_date) AS prediction_year, "
			+ "MONTHNAME(prediction_date) AS prediction_month, MONTH(prediction_date) AS prediction_month_int, "
			+ "(FLOOR(predictions)) AS prediction_price FROM FuturePredictions ORDER BY DATE(prediction_date) ASC";
	@Query(value = dailyFuturePredictions)
	List<Object[]> getDailyFuturePredictions();

	//Get the 3-month future price prediction by counties
	String cumulativeFuturePredictions = "SELECT nafis_date, county, ROUND((CASE WHEN county = 'Nairobi' THEN predictions END), 0) AS Nairobi, "
										+ "ROUND((CASE WHEN county = 'Mombasa' THEN predictions END)) AS Mombasa, "
										+ "ROUND((CASE WHEN county = 'Kisumu' THEN predictions END)) AS Kisumu  FROM FuturePredictions"
										+ " GROUP BY WEEK(STR_TO_DATE(nafis_date,'%d/%m/%Y')), county ORDER BY STR_TO_DATE(nafis_date,'%d/%m/%Y') ASC";
	@Query(value = cumulativeFuturePredictions)
	List<Object[]> getCumulativeFuturePredictions();

}
