package cn.rknight.helloworld;

import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;

/**
 * Author: shanhongqiang
 * DateTime: 2017/8/29 18:15
 * Description:please write the usage of this file.
 */
public class ExampleApplication extends Application<ExampleConfiguration> {
    @Override
    public void run(ExampleConfiguration configuration, Environment environment) throws Exception {
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment,configuration.getDataSourceFactory(),"postgresql");

    }
}
