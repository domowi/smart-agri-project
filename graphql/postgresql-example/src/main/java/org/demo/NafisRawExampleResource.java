package org.demo;

import org.demo.dto.NafisRawExampleDto;
import org.demo.mapper.NafisRawExampleMapper;
import org.demo.model.NafisRawExample;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("/nafis")
@Produces("application/json")
@Consumes("application/json")
public class NafisRawExampleResource {

    @Inject
    NafisRawExampleMapper nafisRawExampleMapper;

    @GET
    @Path("/{commodity}")
    public Response find(@PathParam("commodity") String commodity) {
        return Optional.ofNullable(NafisRawExample.find("commodity", commodity))
                .map(c -> Response.ok(nafisRawExampleMapper.toResource(c)))
                //.map(c -> Response.ok(nafisRawExampleMapper.toResource((NafisRawExample) c)))
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND))
                .build();
    }

    @Transactional
    @POST
    public Response create(NafisRawExampleDto nafisRawExampleDto){
        NafisRawExample nafisRawExample = nafisRawExampleMapper.fromResource(nafisRawExampleDto);
        nafisRawExample.persistAndFlush();
        return Response.ok(nafisRawExampleMapper.toResource(nafisRawExample)).build();
    }

}
