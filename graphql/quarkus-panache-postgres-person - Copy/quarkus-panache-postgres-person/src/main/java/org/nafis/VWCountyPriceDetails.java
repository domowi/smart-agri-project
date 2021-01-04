package org.nafis;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Cacheable;
import javax.persistence.Entity;

@Entity(name = "county_price_details")
@Cacheable
public class VWCountyPriceDetails extends PanacheEntity {

    public Long id;
    public String nafis_price_date;
    public String county_code;
    public String county;
    public String town;
    public String variety;
    public String commodity;
    public String unit;
    public String kg;
    public Integer code;
    public Integer rowno;
    public Float price;
    public Float average;
    public Integer max;
    public Integer min;
    public Integer average_temperature;
    public Integer maximum_temperature;
    public Integer minimum_temperature;
    public Integer precipitation_mm;
    public Integer average_humidity;
    public Integer wind_speed;
    public Integer day_int;
    public Integer month_int;
    public Integer year_int;
    public Integer quarter_int;
    public Integer dow_int;
    public Integer week_of_month;
    public Integer week_of_year;
    public Boolean is_weekday;
}
