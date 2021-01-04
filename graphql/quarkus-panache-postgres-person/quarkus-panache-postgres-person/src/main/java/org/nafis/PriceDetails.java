package org.nafis;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.panache.common.Sort;

import javax.persistence.*;

@Entity(name = "price_details")
@Cacheable
public class PriceDetails extends PanacheEntityBase {
//public class PriceDetails extends PanacheEntity {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY) //@GeneratedValue(strategy= GenerationType.AUTO)
//    @Column(name = "id",unique = true, nullable = false)
//    private Integer id;  // private Integer id;

//    @Id
//    @SequenceGenerator(name = "pricedetailSequence", sequenceName = "pricedetail_id_seq", allocationSize = 1, initialValue = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pricedetailSequence")
//    public Long id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    //public Integer price_id;
    public String nafis_price_date;
    public String county_code;
    public String county;
    public String town;
    public Integer variety;
    public String commodity;
    public String unit;
    public String kg;
    public String code;
    public String rowno;
    public Integer price;
    public Integer average;
    public Integer max;
    public Integer min;
//    public Integer average_temperature;
//    public Integer maximum_temperature;
//    public Integer minimum_temperature;
//    public Integer precipitation_mm;
//    public Integer average_humidity;
//    public Integer wind_speed;
    public Integer day_int;
    public Integer month_int;
    public Integer year_int;
    public Integer quarter_int;
    public Integer dow_int;
    public Integer week_of_month;
    public Integer week_of_year;
    public Integer is_weekday;

}
