package sk.dejavu.blog.examples.paramconverters.resource;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import sk.dejavu.blog.examples.paramconverters.model.Entity;

/**
 * @author Michal Gajdos
 */
@Path("json-param-converter")
@Produces("application/json")
public class JsonParamConverterResource {

    @GET
    @Path("query")
    public Entity getViaQueryParam(
            @QueryParam("entity")
            @DefaultValue("{\"Entity\":{\"foo\":\"bar\",\"bar\":\"foo\"}}")
            final Entity entity) {
        return entity;
    }

    @GET
    @Path("header")
    public Entity getViaHeaderParam(@HeaderParam("Entity") final Entity entity) {
        return entity;
    }
}
