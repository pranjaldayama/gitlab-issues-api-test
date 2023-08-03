package com.abnamro.assignment.specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;

public class IssueSpecs {

    private IssueSpecs() {}

    public static RequestSpecification listIssuesCreated() {

        return new RequestSpecBuilder().
                addRequestSpecification(InitialStateSpecs.set()).build();
    }

    public static ResponseSpecification listIssuesResponse() {
        return new ResponseSpecBuilder().expectStatusCode(HttpStatus.SC_OK).build();
    }
}
