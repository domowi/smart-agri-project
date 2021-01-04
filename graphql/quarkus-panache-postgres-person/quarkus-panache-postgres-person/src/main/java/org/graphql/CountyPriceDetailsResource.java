package org.graphql;

import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Query;
import org.nafis.Inflation;
import org.nafis.VWCountyPriceDetails;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@GraphQLApi
public class CountyPriceDetailsResource {

    @Inject
    CountyPriceDetailsService countyPriceDetailsService;

    @Query("CountyPriceDetails")
    @Description("Get county prices details for all commodities")
    public List<VWCountyPriceDetails> getCountyPriceDetails(){
        return countyPriceDetailsService.getCountyPriceDetails();
    }

    @Query("InflationRates")
    @Description("Get inflation rates for the period for Kenya")
    public List<Inflation> getInflationRates(){
        return countyPriceDetailsService.getInflationRates();
    }

}
