package sk.dejavu.blog.examples.paramconverters;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.glassfish.jersey.uri.UriComponent;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import sk.dejavu.blog.examples.paramconverters.model.Entity;

/**
 * @author Michal Gajdos
 */
public class JsonParamConverterResourceTest extends JerseyTest {

    private final static ObjectMapper mapper = new ObjectMapper()
            .configure(SerializationFeature.WRAP_ROOT_VALUE, true);

    @Override
    protected Application configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);

        return new ParamConvertersApplication();
    }

    @Test
    public void testJsonInQueryParam() throws Exception {
        final Entity entity = new Entity("foo", "bar");
        final String json = mapper.writer().writeValueAsString(entity);

        final Response response = target("json-param-converter")
                .path("query")
                .queryParam("entity", UriComponent.encode(json, UriComponent.Type.QUERY_PARAM_SPACE_ENCODED))
                .request()
                .get();

        // Buffer entity.
        response.bufferEntity();

        // Check POJO.
        assertThat(response.readEntity(Entity.class), is(entity));

        // Check JSON.
        final String jsonResponse = response.readEntity(String.class).replaceAll("[\n\t ]+", "");
        assertTrue(json.contains(jsonResponse));
    }

    @Test
    public void testDefaultJsonInQueryParam() throws Exception {
        final Response response = target("json-param-converter")
                .path("query")
                .request()
                .get();

        // Check POJO.
        assertThat(response.readEntity(Entity.class), is(new Entity("bar", "foo")));
    }

    @Test
    public void testJsonInHeaderParam() throws Exception {
        final Entity entity = new Entity("foo", "bar");
        final String json = mapper.writer().writeValueAsString(entity);

final Response response = target("json-param-converter")
        .path("header")
        .request()
        .header("Entity", json)
        .get();

        // Buffer entity.
        response.bufferEntity();

        // Check POJO.
        assertThat(response.readEntity(Entity.class), is(entity));

        // Check JSON.
        final String jsonResponse = response.readEntity(String.class).replaceAll("[\n\t ]+", "");
        assertTrue(json.contains(jsonResponse));
    }
}
