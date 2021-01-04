package org.graphql;

import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Query;
import org.nafis.Inflation;
import org.nafis.NafisRaw;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@GraphQLApi 
public class NafisRawResource {

    @Inject
    NafisRawService nafisRawService;

    @Query("NafisRaw")
    @Description("Get Nafis Raw Prices for different towns in Kenya")
    public List<NafisRaw> getNafisRaw(){
        return nafisRawService.getNafisRaw();
    }

    @Query("SukumaPrices")
    @Description("Get Sukuma Prices from Nafis Raw Prices for different towns in Kenya")
    public List<NafisRaw> getSukumaPrices(String commodity){
        return nafisRawService.getSukumaPrices(commodity);
    }

}
