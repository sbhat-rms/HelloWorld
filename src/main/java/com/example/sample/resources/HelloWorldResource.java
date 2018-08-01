package com.example.sample.resources;

import com.codahale.metrics.annotation.Timed;
import com.example.sample.api.Saying;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
@Api(value="/hello-world", description ="Operation to say hello")
public class HelloWorldResource {

    private final String template;
    private final String defaultName;
    private final AtomicLong counter;

    public HelloWorldResource(String template, String defaultName){
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
    }

    @GET
    @Timed
    @ApiOperation(value= "Says Hello to person based on the name given",
            notes="when a name is provided along with the request the operation returns a result along with the name provided else returns a default name ")
    public Saying sayHello(@QueryParam("name") Optional<String> name){
        final String value = String.format(template, name.orElse(defaultName));
        return new Saying(counter.incrementAndGet(),value);

    }
}
