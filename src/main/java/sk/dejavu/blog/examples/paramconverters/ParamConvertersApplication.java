package sk.dejavu.blog.examples.paramconverters;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import sk.dejavu.blog.examples.paramconverters.provider.JacksonJsonParamConverterProvider;
import sk.dejavu.blog.examples.paramconverters.provider.ObjectMapperContextResolver;
import sk.dejavu.blog.examples.paramconverters.resource.JsonParamConverterResource;

/**
 * @author Michal Gajdos
 */
@ApplicationPath("/")
public class ParamConvertersApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<Class<?>>();

        // Providers.
        classes.add(JacksonJsonParamConverterProvider.class);
        classes.add(ObjectMapperContextResolver.class);

        // Resources.
        classes.add(JsonParamConverterResource.class);

        return classes;
    }
}
