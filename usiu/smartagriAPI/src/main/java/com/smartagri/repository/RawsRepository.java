package com.smartagri.repository;

import com.smartagri.model.Raws;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;


/**
 * Spring Data JPA repository for the Raws entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RawsRepository extends JpaRepository<Raws, Long> {

	
	String monthlyAvgRawNafisPrices = "SELECT avg(FLOOR(nafis_prices / 10) * 10), MONTH(STR_TO_DATE(nafis_date,'%d/%m/%Y')) "
			+ "as month_int FROM Raws GROUP BY MONTH(STR_TO_DATE(nafis_date,'%d/%m/%Y'))";
	//@Query(value = monthlyAvgRawNafisPrices, nativeQuery = true)
	@Query(value = monthlyAvgRawNafisPrices)
	//List<Raws> findMonthlyAvgRawNafisPrices();
	List<Object[]> findMonthlyAvgRawNafisPrices();
	
	//public List<Raws> findAll(new Sort(Sort.Direction.ASC, "nafis_date"));
	
	String rawNafisPrices = "SELECT nafis_date, county, nafis_prices FROM Raws WHERE STR_TO_DATE(nafis_date,'%d/%m/%Y') BETWEEN :startDate AND :endDate";
	@Query(value = rawNafisPrices, nativeQuery = true)
	List<Object[]> fetchRawNafisPrices(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
}
