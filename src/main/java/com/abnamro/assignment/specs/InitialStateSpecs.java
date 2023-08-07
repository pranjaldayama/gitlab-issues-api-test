package com.abnamro.assignment.specs;

import com.abnamro.assignment.config.ConfigurationManager;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class InitialStateSpecs {

    private InitialStateSpecs() {
    }
    public static RequestSpecification set() {
        var configuration = ConfigurationManager.getConfiguration();

        return new RequestSpecBuilder()
                .setBaseUri(configuration.baseURI())
                .build()
                .header(configuration.contentType(),configuration.contentTypeValue())
                .header(configuration.privateToken(),configuration.privateTokenValue());
    }

    public static RequestSpecification setNotAuthorized() {
        var configuration = ConfigurationManager.getConfiguration();

        return new RequestSpecBuilder()
                .setBaseUri(configuration.baseURI())
                .build()
                .header(configuration.contentType(),configuration.contentTypeValue());
    }
}
