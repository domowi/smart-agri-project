package org.nafis;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.panache.common.Sort;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.ws.rs.GET;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "nafis_example")
@Cacheable
public class NafisExample extends PanacheEntity {

    public Long rowno;
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
    public Integer max;
    public Integer min;
    public String price_date;
    public LocalDate date;

    public static NafisExample findByCommodity(String commodity){
        return find("commodity", commodity).firstResult();
    }

}