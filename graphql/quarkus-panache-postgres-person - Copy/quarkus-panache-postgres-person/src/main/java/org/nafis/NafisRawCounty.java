package org.nafis;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "nafis_raw_county")
@Cacheable
public class NafisRawCounty  extends PanacheEntity {

    public Integer countyId;
    public LocalDate nafisPriceDate;
    public String county;
    public String town;
    public String variety;
    public String commodity;
    public String unit;
    public String kg;
    public Integer code;
    public Integer rowno;
    public Double price;
    public Double average;
    public Integer max;
    public Integer min;
    public String priceDate;
    public Integer varietyId;
    public Integer commodityId;
    public Integer dayInt;
    public Integer monthInt;
    public Integer yearInt;
    public Integer quarterInt;
    public Integer dowInt;
    public Integer weekOfMonth;
    public Integer weekOfYear;
    public String isWeekday;
    public LocalDateTime dateCreated;

}
