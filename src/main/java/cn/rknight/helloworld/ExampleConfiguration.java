package cn.rknight.helloworld;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Author: shanhongqiang
 * DateTime: 2017/8/29 15:44
 * Description:please write the usage of this file.
 */
public class ExampleConfiguration  extends Configuration {
    @Valid
    @NotNull
    private DataSourceFactory database = new DataSourceFactory();

    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        return database;
    }

    @JsonProperty("database")
    public void setDataSouceFactory(DataSourceFactory factory) {
        this.database = factory;
    }
}
