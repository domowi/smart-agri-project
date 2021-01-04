package org.nafis;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/v1/nafis")
public class NafisExampleResource {

    @GET
    @Path("{commodity}")
    @Produces(MediaType.APPLICATION_JSON)
    public NafisExample getSingle(@PathParam("commodity") String commodity) {
        NafisExample entity = NafisExample.findByCommodity(commodity);
        if (entity == null) {
            throw new WebApplicationException("Commodity " + commodity + " does not exist.", 404);
        }
        return entity;
    }

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<NafisExample> get() {
        return NafisExample.listAll(Sort.by("commodity"));
    }

    @GET
    @Path("/commodity/{commodity}/page/{pageSize}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<NafisExample> getListByPage(@PathParam("commodity") String commodity, @PathParam("pageSize") Integer pageSize) {
        // create a query for 5 commodities
        PanacheQuery<NafisExample> pagedCommodity = NafisExample.find("commodity", commodity);
        return pagedCommodity.page(Page.ofSize(pageSize)).list();
    }

    @GET
    @Path("/prices/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<PriceDetails> getPrices() {
    //public List<PriceDetails> getPrices(@PathParam("commodity") String commodity, @PathParam("pageSize") Integer pageSize) {
        int pageSize = 25;
        PanacheQuery<PriceDetails> priceDetails = PriceDetails.findAll(Sort.by("commodity"));
        return priceDetails.page(Page.ofSize(pageSize)).list();
    }

    @GET
    @Path("/pricedetails/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<NafisCountyPriceDetails> getNafisCountyPrices() {
        //public List<NafisCountyPriceDetails> getPrices(@PathParam("commodity") String commodity, @PathParam("pageSize") Integer pageSize) {
        int pageSize = 25;
        PanacheQuery<NafisCountyPriceDetails> nafisCountyPriceDetails = NafisCountyPriceDetails.findAll(Sort.by("commodity"));
        return nafisCountyPriceDetails.page(Page.ofSize(pageSize)).list();
    }

    @GET
    @Path("/countypricedetails/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<VWCountyPriceDetails> getCountyPriceDetails() {
        int pageSize = 25;
        PanacheQuery<VWCountyPriceDetails> countyPriceDetails = VWCountyPriceDetails.findAll(Sort.by("commodity"));
        return countyPriceDetails.page(Page.ofSize(pageSize)).list();
    }

}
