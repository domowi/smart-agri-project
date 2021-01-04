package org.nafis;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "weather")
@Cacheable
public class Weather extends PanacheEntity {

    public Integer townId;
    public LocalDateTime date_of_weather_forecast;
    public Double averageTemperature;
    public Double maximumTemperature;
    public Double minimumTemperature;
    public Double precipitationMm;
    public Double averageHumidity;
    public Double maximumHumidity;
    public Double minimumHumidity;
    public Double windSpeed;
    public LocalDateTime dateCreated;
    public LocalDateTime dateModified;

}
