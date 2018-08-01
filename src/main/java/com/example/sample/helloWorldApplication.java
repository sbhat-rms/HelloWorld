package com.example.sample;

import com.example.sample.health.TemplateHealthCheck;
import com.example.sample.resources.HelloWorldResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

public class helloWorldApplication extends Application<helloWorldConfiguration> {

    public static void main(final String[] args) throws Exception {
        new helloWorldApplication().run(args);
    }

    @Override
    public String getName() {
        return "helloWorld";
    }

    @Override
    public void initialize(final Bootstrap<helloWorldConfiguration> bootstrap) {
        bootstrap.addBundle(new SwaggerBundle<helloWorldConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration (helloWorldConfiguration configuration){
                return configuration.swaggerBundleConfiguration;
            }
        });
    }

    @Override
    public void run(final helloWorldConfiguration configuration,
                    final Environment environment) {
        final HelloWorldResource resource = new HelloWorldResource(configuration.getTemplate(),configuration.getDefaultName());
        final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template",healthCheck);
        environment.jersey().register(resource);

    }

}
