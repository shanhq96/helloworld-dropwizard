package cn.rknight.helloworld.resources;

import cn.rknight.helloworld.api.Saying;
import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Author: shanhongqiang
 * DateTime: 2017/8/28 11:14
 * Description:HelloWorldResource has two annotations: @Path and @Produces. @Path("/hello-world") tells Jersey that this resource is accessible at the URI /hello-world,
 * and @Produces(MediaType.APPLICATION_JSON) lets Jersey’s content negotiation code know that this resource produces representations which are application/json.
 */
@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {

    //HelloWorldResource takes two parameters for construction:
    // the template it uses to produce the saying
    // and the defaultName used when the user declines to tell us their name.
    // An AtomicLong provides us with a cheap, thread-safe way of generating unique(ish) IDs.
    private final String template;
    private final String defaultName;
    private final AtomicLong counter;

    public HelloWorldResource(String template, String defaultName) {
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
    }

    /**
     * #sayHello(Optional<String>) is the meat of this class, and it’s a fairly simple method.
     * The @QueryParam("name") annotation tells Jersey to map the name parameter from the query string to the name parameter in the method.
     * If the client sends a request to /hello-world?name=Dougie, sayHello will be called with Optional.of("Dougie");
     * if there is no name parameter in the query string, sayHello will be called with Optional.absent().
     * (Support for Guava’s Optional is a little extra sauce that Dropwizard adds to Jersey’s existing functionality.)
     * @param name
     * @return
     */
    @GET
    @Timed
    public Saying sayHello(@QueryParam("name") Optional<String> name){
        final String value = String.format(template,name.or(defaultName));
        return new Saying(counter.incrementAndGet(),value);
    }
}
