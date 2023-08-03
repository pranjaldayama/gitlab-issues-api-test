
package com.abnamro.assignment.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;

@LoadPolicy(LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:api.properties"})
public interface Configuration extends Config {

    @Key("api.base.uri")
    String baseURI();

    @Key("api.health.context")
    String health();

    @Key("log.all")
    boolean logAll();

    @Key("projectId")
    String projectId();

    @Key("contentType")
    String contentType();

    @Key("contentTypeValue")
    String contentTypeValue();

    @Key("privateToken")
    String privateToken();

    @Key("privateTokenValue")
    String privateTokenValue();
}
