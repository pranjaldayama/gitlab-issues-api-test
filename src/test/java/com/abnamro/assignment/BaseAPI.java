package com.abnamro.assignment;

import com.abnamro.assignment.config.Configuration;
import com.abnamro.assignment.config.ConfigurationManager;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public abstract class BaseAPI {
    protected static Configuration configuration;

    @BeforeAll
    public static void beforeAllTests() {
        configuration = ConfigurationManager.getConfiguration();

        baseURI = configuration.baseURI();
        RestAssured.useRelaxedHTTPSValidation();

        determineLog();
    }

    /*
     * When log.all is true in the api.properties file all the request and response information will be logged
     * otherwise it will log only when the test fails
     */
    private static void determineLog() {
        if (configuration.logAll()) {
            RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        } else {
            RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        }
    }
}
