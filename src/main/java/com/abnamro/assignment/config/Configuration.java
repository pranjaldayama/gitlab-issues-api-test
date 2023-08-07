
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

    @Key("api.project.id")
    String projectId();

    @Key("api.content.type")
    String contentType();

    @Key("api.content.type.value")
    String contentTypeValue();

    @Key("api.private.token")
    String privateToken();

    @Key("api.private.token.value")
    String privateTokenValue();

    @Key("log.all")
    boolean logAll();
}
