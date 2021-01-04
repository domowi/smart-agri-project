package org.nafis;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.panache.common.Sort;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "towns")
@Cacheable
public class Towns  extends PanacheEntity {

    public Integer countyId;
    public String town;
    public Double areaSquareKilometers;
    public LocalDateTime dateCreated;
    public LocalDateTime dateModified;

}
