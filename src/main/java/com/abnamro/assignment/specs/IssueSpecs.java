package com.abnamro.assignment.specs;

import com.abnamro.assignment.data.factory.IssueDataFactory;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;

public class IssueSpecs {

    private IssueSpecs() {}
    public static RequestSpecification createIssue() {

        var issue = new IssueDataFactory().createIssueRequest();
        return new RequestSpecBuilder().
                addRequestSpecification(InitialStateSpecs.set()).
                setBody(issue).
                build();
    }

    public static ResponseSpecification verifyCreatedIssue() {
        return new ResponseSpecBuilder().
                expectStatusCode(HttpStatus.SC_CREATED).
                build();
    }

    public static RequestSpecification createIssueWithoutAuthorizationToken() {

        var issue = new IssueDataFactory().createIssueRequest();
        return new RequestSpecBuilder().
                addRequestSpecification(InitialStateSpecs.setNotAuthorized()).
                setBody(issue).
                build();
    }

    public static RequestSpecification createIssueWithoutTitle() {

        var issue = new IssueDataFactory().createIssueRequestWithoutTitle();
        return new RequestSpecBuilder().
                addRequestSpecification(InitialStateSpecs.set()).
                setBody(issue).
                build();
    }

    public static ResponseSpecification verifyUnAuthorizationError() {
        return new ResponseSpecBuilder().
                expectStatusCode(HttpStatus.SC_UNAUTHORIZED).
                build();
    }

    public static ResponseSpecification verifyErrorOnCreateIssueMissingMandatoryFields() {
        return new ResponseSpecBuilder().
                expectStatusCode(HttpStatus.SC_BAD_REQUEST).
                build();
    }

    public static RequestSpecification initialRequest() {

        return new RequestSpecBuilder().
                addRequestSpecification(InitialStateSpecs.set()).
                build();
    }

    public static ResponseSpecification verifySuccessResponse() {
        return new ResponseSpecBuilder().
                expectStatusCode(HttpStatus.SC_OK).
                build();
    }

    public static ResponseSpecification verifyNotFoundError() {
        return new ResponseSpecBuilder().
                expectStatusCode(HttpStatus.SC_NOT_FOUND).
                build();
    }

    public static ResponseSpecification verifyDeleteIssue() {
        return new ResponseSpecBuilder().
                expectStatusCode(HttpStatus.SC_NO_CONTENT).
                build();
    }
}
