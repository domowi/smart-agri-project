package com.smartagri.model;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Future Predictions.
 */
@Entity
@Table(name = "future_predictions")
public class FuturePredictions implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "county")
    private String county;

    @Column(name = "commodity")
    private String commodity;

    @Column(name = "month")
    private String month;
    
    @Column(name = "year")
    private String year;

    @Column(name = "season")
    private String season;

    @Column(name = "quantities")
    private String quantities;

    @Column(name = "selling_price")
    private String selling_price;

    @Column(name = "household_sizes")
    private String household_sizes;

    @Column(name = "total_demand_in_kes")
    private String total_demand_in_kes;

    @Column(name = "total_household_demand_kg")
    private String total_household_demand_kg;

    @Column(name = "annual_avg_inflation")
    private String annual_avg_inflation;

    @Column(name = "twelve_month_inflation")
    private String twelve_month_inflation;

    @Column(name = "nafis_date")
    private String nafis_date;

    @Column(name = "nafis_units_fifty_kg")
    private String nafis_units_fifty_kg;

    @Column(name = "nafis_prices")
    private String nafis_prices;
    
    @Column(name = "predictions")
    private String predictions;
    
    @Column(name = "prediction_date")
    private String prediction_date;

    @Column(name = "avg_temp")
    private String avg_temp;

    @Column(name = "max_temp")
    private String max_temp;

    @Column(name = "min_temp")
    private String min_temp;

    @Column(name = "precipitation_mm")
    private String precipitation_mm;

    @Column(name = "avg_humidity")
    private String avg_humidity;

    @Column(name = "max_humidity")
    private String max_humidity;

    @Column(name = "min_humidity")
    private String min_humidity;

    @Column(name = "month_val")
    private String month_val;

    @Column(name = "season_val")
    private String season_val;

    @Column(name = "active")
    private Integer active;

    @Column(name = "insertedby")
    private Integer insertedby;

    @Column(name = "datecreated")
    private Instant datecreated;

    @Column(name = "updatedby")
    private Integer updatedby;

    @Column(name = "datemodified")
    private LocalDate datemodified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCounty() {
        return county;
    }

    public FuturePredictions county(String county) {
        this.county = county;
        return this;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCommodity() {
        return commodity;
    }

    public FuturePredictions commodity(String commodity) {
        this.commodity = commodity;
        return this;
    }

    public void setCommodity(String commodity) {
        this.commodity = commodity;
    }

    public String getYear() {
        return year;
    }

    public FuturePredictions year(String year) {
        this.year = year;
        return this;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public FuturePredictions month(String month) {
        this.month = month;
        return this;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getSeason() {
        return season;
    }

    public FuturePredictions season(String season) {
        this.season = season;
        return this;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getQuantities() {
        return quantities;
    }

    public FuturePredictions quantities(String quantities) {
        this.quantities = quantities;
        return this;
    }

    public void setQuantities(String quantities) {
        this.quantities = quantities;
    }

    public String getSellingprice() {
        return selling_price;
    }

    public FuturePredictions sellingprice(String selling_price) {
        this.selling_price = selling_price;
        return this;
    }

    public void setSellingprice(String selling_price) {
        this.selling_price = selling_price;
    }

    public String getHouseholdsizes() {
        return household_sizes;
    }

    public FuturePredictions householdsizes(String household_sizes) {
        this.household_sizes = household_sizes;
        return this;
    }

    public void setHouseholdsizes(String household_sizes) {
        this.household_sizes = household_sizes;
    }

    public String getTotaldemandinkes() {
        return total_demand_in_kes;
    }

    public FuturePredictions totaldemandinkes(String total_demand_in_kes) {
        this.total_demand_in_kes = total_demand_in_kes;
        return this;
    }

    public void setTotaldemandinkes(String total_demand_in_kes) {
        this.total_demand_in_kes = total_demand_in_kes;
    }

    public String getTotalhouseholddemandkg() {
        return total_household_demand_kg;
    }

    public FuturePredictions totalhouseholddemandkg(String total_household_demand_kg) {
        this.total_household_demand_kg = total_household_demand_kg;
        return this;
    }

    public void setTotalhouseholddemandkg(String total_household_demand_kg) {
        this.total_household_demand_kg = total_household_demand_kg;
    }

    public String getAnnualavginflation() {
        return annual_avg_inflation;
    }

    public FuturePredictions annualavginflation(String annual_avg_inflation) {
        this.annual_avg_inflation = annual_avg_inflation;
        return this;
    }

    public void setAnnualavginflation(String annual_avg_inflation) {
        this.annual_avg_inflation = annual_avg_inflation;
    }

    public String getTwelvemonthinflation() {
        return twelve_month_inflation;
    }

    public FuturePredictions twelvemonthinflation(String twelve_month_inflation) {
        this.twelve_month_inflation = twelve_month_inflation;
        return this;
    }

    public void setTwelvemonthinflation(String twelve_month_inflation) {
        this.twelve_month_inflation = twelve_month_inflation;
    }

    public String getNafisdate() {
        return nafis_date;
    }

    public FuturePredictions nafisdate(String nafis_date) {
        this.nafis_date = nafis_date;
        return this;
    }

    public void setNafisdate(String nafis_date) {
        this.nafis_date = nafis_date;
    }

    public String getNafisunitsfiftykg() {
        return nafis_units_fifty_kg;
    }

    public FuturePredictions nafisunitsfiftykg(String nafis_units_fifty_kg) {
        this.nafis_units_fifty_kg = nafis_units_fifty_kg;
        return this;
    }

    public void setNafisunitsfiftykg(String nafis_units_fifty_kg) {
        this.nafis_units_fifty_kg = nafis_units_fifty_kg;
    }

    public String getNafisprices() {
        return nafis_prices;
    }

    public FuturePredictions nafisprices(String nafis_prices) {
        this.nafis_prices = nafis_prices;
        return this;
    }

    public void setNafisprices(String nafis_prices) {
        this.nafis_prices = nafis_prices;
    }
    
    public String getPredictions() {
        return predictions;
    }

    public FuturePredictions predictions(String predictions) {
        this.predictions = predictions;
        return this;
    }

    public void setPredictions(String predictions) {
        this.predictions = predictions;
    }
    
    public String getPredictionDate() {
        return prediction_date;
    }

    public FuturePredictions predictiondate(String prediction_date) {
        this.prediction_date = prediction_date;
        return this;
    }

    public void setPredictionDate(String prediction_date) {
        this.prediction_date = prediction_date;
    }
    
    public String getAvgtemp() {
        return avg_temp;
    }

    public FuturePredictions avgtemp(String avg_temp) {
        this.avg_temp = avg_temp;
        return this;
    }

    public void setAvgtemp(String avgtemp) {
        this.avg_temp = avg_temp;
    }

    public String getMaxtemp() {
        return max_temp;
    }

    public FuturePredictions maxtemp(String max_temp) {
        this.max_temp = max_temp;
        return this;
    }

    public void setMaxtemp(String max_temp) {
        this.max_temp = max_temp;
    }

    public String getMintemp() {
        return min_temp;
    }

    public FuturePredictions mintemp(String min_temp) {
        this.min_temp = min_temp;
        return this;
    }

    public void setMintemp(String min_temp) {
        this.min_temp = min_temp;
    }

    public String getPrecipitationmm() {
        return precipitation_mm;
    }

    public FuturePredictions precipitationmm(String precipitation_mm) {
        this.precipitation_mm = precipitation_mm;
        return this;
    }

    public void setPrecipitationmm(String precipitation_mm) {
        this.precipitation_mm = precipitation_mm;
    }

    public String getAvghumidity() {
        return avg_humidity;
    }

    public FuturePredictions avghumidity(String avg_humidity) {
        this.avg_humidity = avg_humidity;
        return this;
    }

    public void setAvghumidity(String avg_humidity) {
        this.avg_humidity = avg_humidity;
    }

    public String getMaxhumidity() {
        return max_humidity;
    }

    public FuturePredictions maxhumidity(String max_humidity) {
        this.max_humidity = max_humidity;
        return this;
    }

    public void setMaxhumidity(String max_humidity) {
        this.max_humidity = max_humidity;
    }

    public String getMinhumidity() {
        return min_humidity;
    }

    public FuturePredictions minhumidity(String min_humidity) {
        this.min_humidity = min_humidity;
        return this;
    }

    public void setMinhumidity(String min_humidity) {
        this.min_humidity = min_humidity;
    }

    public String getMonthval() {
        return month_val;
    }

    public FuturePredictions monthval(String month_val) {
        this.month_val = month_val;
        return this;
    }

    public void setMonthval(String month_val) {
        this.month_val = month_val;
    }

    public String getSeasonval() {
        return season_val;
    }

    public FuturePredictions seasonval(String season_val) {
        this.season_val = season_val;
        return this;
    }

    public void setSeasonval(String season_val) {
        this.season_val = season_val;
    }

    public Integer getActive() {
        return active;
    }

    public FuturePredictions active(Integer active) {
        this.active = active;
        return this;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public Integer getInsertedby() {
        return insertedby;
    }

    public FuturePredictions insertedby(Integer insertedby) {
        this.insertedby = insertedby;
        return this;
    }

    public void setInsertedby(Integer insertedby) {
        this.insertedby = insertedby;
    }

    public Instant getDatecreated() {
        return datecreated;
    }

    public FuturePredictions datecreated(Instant datecreated) {
        this.datecreated = datecreated;
        return this;
    }

    public void setDatecreated(Instant datecreated) {
        this.datecreated = datecreated;
    }

    public Integer getUpdatedby() {
        return updatedby;
    }

    public FuturePredictions updatedby(Integer updatedby) {
        this.updatedby = updatedby;
        return this;
    }

    public void setUpdatedby(Integer updatedby) {
        this.updatedby = updatedby;
    }

    public LocalDate getDatemodified() {
        return datemodified;
    }

    public FuturePredictions datemodified(LocalDate datemodified) {
        this.datemodified = datemodified;
        return this;
    }

    public void setDatemodified(LocalDate datemodified) {
        this.datemodified = datemodified;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FuturePredictions prediction = (FuturePredictions) o;
        if (prediction.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), prediction.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FuturePredictions{" +
            "id=" + getId() +
            ", county='" + getCounty() + "'" +
            ", commodity='" + getCommodity() + "'" +
            ", year='" + getYear() + "'" +
            ", month='" + getMonth() + "'" +
            ", season='" + getSeason() + "'" +
            ", quantities='" + getQuantities() + "'" +
            ", selling_price='" + getSellingprice() + "'" +
            ", household_sizes='" + getHouseholdsizes() + "'" +
            ", total_demand_in_kes='" + getTotaldemandinkes() + "'" +
            ", total_household_demand_kg='" + getTotalhouseholddemandkg() + "'" +
            ", annual_avg_inflation='" + getAnnualavginflation() + "'" +
            ", twelve_month_inflation='" + getTwelvemonthinflation() + "'" +
            ", nafis_date='" + getNafisdate() + "'" +
            ", nafis_units_fifty_kg='" + getNafisunitsfiftykg() + "'" +
            ", nafis_prices='" + getNafisprices() + "'" +
            ", avg_temp='" + getAvgtemp() + "'" +
            ", max_temp='" + getMaxtemp() + "'" +
            ", min_temp='" + getMintemp() + "'" +
            ", precipitation_mm='" + getPrecipitationmm() + "'" +
            ", avg_humidity='" + getAvghumidity() + "'" +
            ", max_humidity='" + getMaxhumidity() + "'" +
            ", min_humidity='" + getMinhumidity() + "'" +
            ", month_val='" + getMonthval() + "'" +
            ", season_val='" + getSeasonval() + "'" +
            ", active=" + getActive() +
            ", insertedby=" + getInsertedby() +
            ", datecreated='" + getDatecreated() + "'" +
            ", updatedby=" + getUpdatedby() +
            ", datemodified='" + getDatemodified() + "'" +
            "}";
    }
}
