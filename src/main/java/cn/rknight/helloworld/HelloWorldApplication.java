package cn.rknight.helloworld;

import cn.rknight.helloworld.health.TemplateHealthCheck;
import cn.rknight.helloworld.resources.HelloWorldResource;
import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;

/**
 * Author: shanhongqiang
 * DateTime: 2017/8/28 11:03
 * Description:Combined with your project’s Configuration subclass,
 * its Application subclass forms the core of your Dropwizard application.
 * The Application class pulls together the various bundles and commands which provide basic functionality.
 * (More on that later.) For now, though, our HelloWorldApplication looks like this:
 */
public class HelloWorldApplication extends Application<HelloWorldConfiguration>{

    public static void main(String[] args) throws Exception{
        new HelloWorldApplication().run(args);
    }

    @Override
    public String getName(){
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap){
        // noting to do yet
    }

    /**
     * Before that will actually work, though, we need to go back to HelloWorldApplication and add this new resource class.
     * In its run method we can read the template and default name from the HelloWorldConfiguration instance,
     * create a new HelloWorldResource instance, and then add it to the application’s Jersey environment:
     * @param configuration
     * @param environment
     * @throws Exception
     */
    @Override
    public void run(HelloWorldConfiguration configuration, Environment environment) throws Exception {
        final HelloWorldResource resource = new HelloWorldResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );
        final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template",healthCheck);
        environment.jersey().register(resource);
    }
}
