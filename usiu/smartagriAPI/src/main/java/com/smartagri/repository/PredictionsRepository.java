package com.smartagri.repository;

import com.smartagri.model.Predictions;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Predictions entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PredictionsRepository extends JpaRepository<Predictions, Long> {
	
	// Get the monthly average predictions vs nafis prices
	String monthlyAvgPredictionsNafisPrices = "SELECT YEAR(STR_TO_DATE(nafis_date,'%d/%m/%Y')) as year, "
			+ " MONTHNAME(STR_TO_DATE(nafis_date,'%d/%m/%Y')) as mon, "
			+ " FLOOR(avg(nafis_prices / 10) * 10) as nafis_price, "
			+ " FLOOR(avg(predictions / 10) * 10) as prediction FROM Predictions "
			+ "GROUP BY YEAR(STR_TO_DATE(nafis_date,'%d/%m/%Y')), MONTH(STR_TO_DATE(nafis_date,'%d/%m/%Y'))";
//	String monthlyAvgPredictionsNafisPrices = "SELECT MONTHNAME(STR_TO_DATE(nafis_date,'%d/%m/%Y')) as month, "
//			+ "avg(FLOOR(nafis_prices / 10) * 10) as nafis_price, avg(FLOOR(predictions / 10) * 10) as prediction "
//			+ "FROM Predictions WHERE YEAR(STR_TO_DATE(nafis_date,'%d/%m/%Y')) > YEAR(DATE_SUB(CURDATE(), INTERVAL 1 YEAR)) "
//			+ "GROUP BY MONTH(STR_TO_DATE(nafis_date,'%d/%m/%Y'));";
	@Query(value = monthlyAvgPredictionsNafisPrices)
	List<Object[]> findMonthlyAvgPredictedNafisPrices();
	
	
	// Get the past daily predicted values vs the nafis prices
	String dailyPredictions = "SELECT nafis_date, nafis_prices, (FLOOR(predictions / 10) * 10) as prediction, "
			+ "avg_temp, precipitation_mm, annual_avg_inflation FROM Predictions "
			+ "ORDER BY STR_TO_DATE(nafis_date,'%d/%m/%Y') DESC LIMIT 10;\n";
	@Query(value = dailyPredictions, nativeQuery=true)
	List<Object[]> getSortedDailyNafisPredictions();
	
	
	//////////////////////////// NAIROBI ////////////////////////////
	// Get last 1 year nafis prices vs predictions
	String avgNairobiNafisPredictionsPast = "SELECT YEAR(STR_TO_DATE(nafis_date,'%d/%m/%Y')) AS year, "
			+ "MONTHNAME(STR_TO_DATE(nafis_date,'%d/%m/%Y')) as month, (FLOOR(AVG(nafis_prices))) as nafis_price, "
			+ "(FLOOR(AVG(predictions))) as prediction FROM Predictions WHERE county = 'Nairobi' "
			+ " GROUP BY YEAR(STR_TO_DATE(nafis_date,'%d/%m/%Y')), MONTH(STR_TO_DATE(nafis_date,'%d/%m/%Y')) "
			+ " ORDER BY year DESC, MONTH(STR_TO_DATE(nafis_date,'%d/%m/%Y')) DESC LIMIT 12";
	@Query(value = avgNairobiNafisPredictionsPast, nativeQuery=true)
	List<Object[]> getAvgNairobiNafisPredictionsPast();
	
	
	// Get last 1 month daily nafis prices vs predictions
	String dailyNairobiNafisPredictionComparisons = "SELECT nafis_date, nafis_prices, (FLOOR(predictions / 10) * 10) as prediction, "
			+ "avg_temp, precipitation_mm, annual_avg_inflation FROM Predictions WHERE county = 'Nairobi' "
			+ "ORDER BY STR_TO_DATE(nafis_date,'%d/%m/%Y') DESC LIMIT 10";
	@Query(value = dailyNairobiNafisPredictionComparisons, nativeQuery=true)
	List<Object[]> getDailyNairobiNafisPredictionsComp();
	
	
	//////////////////////////// MOMBASA ////////////////////////////
	// Get last 1 year nafis prices vs predictions
	String avgMombasaNafisPredictionsPast = "SELECT YEAR(STR_TO_DATE(nafis_date,'%d/%m/%Y')) AS year, "
			+ "MONTHNAME(STR_TO_DATE(nafis_date,'%d/%m/%Y')) as month, (FLOOR(AVG(nafis_prices))) as nafis_price, "
			+ "(FLOOR(AVG(predictions))) as prediction FROM Predictions WHERE county = 'Mombasa' "
			+ " GROUP BY YEAR(STR_TO_DATE(nafis_date,'%d/%m/%Y')), MONTH(STR_TO_DATE(nafis_date,'%d/%m/%Y')) "
			+ " ORDER BY year DESC, MONTH(STR_TO_DATE(nafis_date,'%d/%m/%Y')) DESC LIMIT 12";
	@Query(value = avgMombasaNafisPredictionsPast, nativeQuery=true)
	List<Object[]> getAvgMombasaNafisPredictionsPast();
	
	
	// Get last 1 month daily nafis prices vs predictions
	String dailyMombasaNafisPredictionComparisons = "SELECT nafis_date, nafis_prices, (FLOOR(predictions / 10) * 10) as prediction, "
			+ "avg_temp, precipitation_mm, annual_avg_inflation FROM Predictions WHERE county = 'Mombasa' "
			+ "ORDER BY STR_TO_DATE(nafis_date,'%d/%m/%Y') DESC LIMIT 10";
	@Query(value = dailyMombasaNafisPredictionComparisons, nativeQuery=true)
	List<Object[]> getDailyMombasaNafisPredictionsComp();
	
	
	//////////////////////////// KISUMU ////////////////////////////
	// Get last 1 year nafis prices vs predictions
	String avgKisumuNafisPredictionsPast = "SELECT YEAR(STR_TO_DATE(nafis_date,'%d/%m/%Y')) AS year, "
			+ "MONTHNAME(STR_TO_DATE(nafis_date,'%d/%m/%Y')) as month, (FLOOR(AVG(nafis_prices))) as nafis_price, "
			+ "(FLOOR(AVG(predictions))) as prediction FROM Predictions WHERE county = 'Kisumu' "
			+ " GROUP BY YEAR(STR_TO_DATE(nafis_date,'%d/%m/%Y')), MONTH(STR_TO_DATE(nafis_date,'%d/%m/%Y')) "
			+ " ORDER BY year DESC, MONTH(STR_TO_DATE(nafis_date,'%d/%m/%Y')) DESC LIMIT 12";
	@Query(value = avgKisumuNafisPredictionsPast, nativeQuery=true)
	List<Object[]> getAvgKisumuNafisPredictionsPast();
	
	
	// Get last 1 month daily nafis prices vs predictions
	String dailyKisumuNafisPredictionComparisons = "SELECT nafis_date, nafis_prices, (FLOOR(predictions / 10) * 10) as prediction, "
			+ "avg_temp, precipitation_mm, annual_avg_inflation FROM Predictions WHERE county = 'Kisumu' "
			+ "ORDER BY STR_TO_DATE(nafis_date,'%d/%m/%Y') DESC LIMIT 10";
	@Query(value = dailyKisumuNafisPredictionComparisons, nativeQuery=true)
	List<Object[]> getDailyKisumuNafisPredictionsComp();


	//////////////////////////// SEARCH /////////////////////////////

	// Get Prediction Prices
	// SELECT * FROM predictions WHERE county = "Nairobi" AND STR_TO_DATE(nafis_date,'%d/%m/%Y') BETWEEN '2018-01-01' AND '2018-07-01';
	String searchPredictionPricesList2 = "SELECT nafis_date, nafis_prices, (FLOOR(predictions / 10) * 10) as prediction, county, avg_temp, "
			+ " precipitation_mm, annual_avg_inflation"
			+ " FROM predictions p "
			+ " WHERE p.county = :county ";
			//+ " AND STR_TO_DATE(nafis_date,'%d/%m/%Y') BETWEEN "
			//+ " :startDate AND :endDate";
			// + " ORDER BY ?#{#pageable}";
	@Query(value = searchPredictionPricesList2, nativeQuery=true)
	List<Object[]> getSearchPredictionPricesList2(
			@Param("county") String county
	);
	/*List<Object[]> getSearchPredictionPricesList(
			@Param("county") String county, @Param("startDate") String startDate, @Param("endDate") String endDate
	);*/
	/*List<Object[]> getSearchPredictionPricesList(
			@Param("county") String county, @Param("startDate") String startDate, @Param("endDate") String endDate,
			Pageable pageable
	);*/

	String searchPredictionPricesList = "SELECT nafis_date, nafis_prices, (FLOOR(predictions / 10) * 10) as prediction, county, avg_temp, "
			+ " precipitation_mm, annual_avg_inflation "
			+ " FROM Predictions "
			+ " WHERE id = :county";
	// + " WHERE LOWER(p.county) LIKE LOWER(CONCAT('%',:county,'%'))";
	//+ " AND STR_TO_DATE(nafis_date,'%d/%m/%Y') BETWEEN "
	//+ " :startDate AND :endDate";
	// + " ORDER BY ?#{#pageable}";
	@Query(value = searchPredictionPricesList, nativeQuery=true)
	List<Object[]> getSearchPredictionPricesList(
			@Param("county") Integer county
	);
	
	//////////////////////////// FEATURES ///////////////////////////
	// Get weather data last 1 year - Average Temparature - Nairobi, Mombasa, Kisumu
	
	
	// Get inflation data last 1 year - Annual / Monthly Inflation - Nairobi, Mombasa, Kisumu
	
	
}
