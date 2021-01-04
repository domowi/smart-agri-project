package org.nafis;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.panache.common.Sort;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "inflation")
@Cacheable
public class Inflation extends PanacheEntity {

    public Long id;
    public Integer year;
    public String month;
    public Double annual_average_inflation;
    public Double twelve_month_inflation;
    public LocalDateTime date_created;
    public LocalDateTime date_modified;

}
