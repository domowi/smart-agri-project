package org.nafis;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "nafis_raw_1")
@Cacheable
public class NafisRaw extends PanacheEntity {

    public Long id;
    public Integer rowno;
    public String variety;
    public String commodity;
    public String unit;
    public String kg;
    public Integer code;
    public String nairobi;
    public String mombasa;
    public String kisumu;
    public String nakuru;
    public String kisii;
    public String eldoret;
    public String busia;
    public String kitale;
    public String isiolo;
    public String loitktk;
    public String malindi;
    public String kakamega;
    public String kitui;
    public String wajir;
    public String imenti;
    public String machakos;
    public String chwele;
    public String taveta;
    public String karatina;
    public String nyahururu;
    public String garissa;
    public Double average;
    public Double max;
    public Double min;
    public LocalDate price_date ;
    public LocalDate nafis_price_date;
    public LocalDateTime dateCreated;
}
