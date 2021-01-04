package org.graphql;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import org.nafis.Inflation;
import org.nafis.VWCountyPriceDetails;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class CountyPriceDetailsService {

    public List<VWCountyPriceDetails> getCountyPriceDetails() {
        int pageSize = 25;
        PanacheQuery<VWCountyPriceDetails> countyPriceDetails = VWCountyPriceDetails.findAll(Sort.by("commodity"));
        return countyPriceDetails.page(Page.ofSize(pageSize)).list();
    }

    /*public List<Hero> getHeroesByFilm(Film film) {
        return heroes.stream()
                .filter(hero -> hero.getEpisodeIds().contains(film.getEpisodeID()))
                .collect(Collectors.toList());
    }

    public List<Hero> getHeroesBySurname(String surname) {
        return heroes.stream()
                .filter(hero -> hero.getSurname().equals(surname))
                .collect(Collectors.toList());
    }*/

    public List<Inflation> getInflationRates() {
        PanacheQuery<Inflation> inflation = Inflation.findAll();
        return inflation.list();
    }

}
