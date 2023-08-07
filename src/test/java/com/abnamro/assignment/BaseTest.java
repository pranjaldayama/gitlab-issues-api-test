package com.abnamro.assignment;

import com.abnamro.assignment.client.IssueClient;
import com.abnamro.assignment.config.Configuration;
import com.abnamro.assignment.config.ConfigurationManager;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.util.List;

import static io.restassured.RestAssured.baseURI;


/* This class is used to set the initial requests (baseURI) using RestAssured and to log all the request and response details*/

public abstract class BaseTest {
    protected static Configuration configuration;
    private static final IssueClient issueClient = new IssueClient();

    @BeforeAll
    public static void beforeAllTests() {

        configuration = ConfigurationManager.getConfiguration();
        baseURI = configuration.baseURI();
        determineLog();
    }

    @AfterAll
    public static void cleanUpIssues() {

        Response response = issueClient.getIssues();

        // Extract the list of issue ID's from the response
        List<Integer> issueIds = response.jsonPath().getList("iid", Integer.class);

        //Delete each issue
        for (int issueId : issueIds) {
            issueClient.deleteIssue(issueId);
        }
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
