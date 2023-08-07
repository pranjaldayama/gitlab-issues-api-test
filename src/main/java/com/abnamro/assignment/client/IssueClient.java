package com.abnamro.assignment.client;

import com.abnamro.assignment.config.Configuration;
import com.abnamro.assignment.config.ConfigurationManager;
import com.abnamro.assignment.data.changeless.IssuesData;
import com.abnamro.assignment.specs.IssueSpecs;
import io.restassured.response.Response;

import static com.abnamro.assignment.data.changeless.IssuesData.EDIT_ISSUE;
import static io.restassured.RestAssured.given;

public class IssueClient {

    Configuration configuration = ConfigurationManager.getConfiguration();

    public Response createIssue() {

        return
                given().
                        spec(IssueSpecs.createIssue()).
                        when().
                        post(IssuesData.PROJECTS_PATH_URI + configuration.projectId() + IssuesData.ISSUES_PATH_URI).
                        then().
                        spec(IssueSpecs.verifyCreatedIssue()).
                        extract().
                        response();
    }

    public void createIssueWithoutAuthorizationToken() {

        given().
                spec(IssueSpecs.createIssueWithoutAuthorizationToken()).
                when().
                post(IssuesData.PROJECTS_PATH_URI + configuration.projectId() + IssuesData.ISSUES_PATH_URI).
                then().
                spec(IssueSpecs.verifyUnAuthorizationError());
    }

    public Response createIssueWithoutMandatoryFields() {

        return
                given().
                        spec(IssueSpecs.createIssueWithoutTitle()).
                        when().
                        post(IssuesData.PROJECTS_PATH_URI + configuration.projectId() + IssuesData.ISSUES_PATH_URI).
                        then().
                        spec(IssueSpecs.verifyErrorOnCreateIssueMissingMandatoryFields()).extract().response();
    }

    public Response getIssue(Integer issueId) {

        return
                given().
                        spec(IssueSpecs.initialRequest()).
                        when().
                        get(IssuesData.PROJECTS_PATH_URI + configuration.projectId() + IssuesData.ISSUES_PATH_URI + "/" + issueId).
                        then().
                        spec(IssueSpecs.verifySuccessResponse()).
                        extract().
                        response();
    }

    public Response getIssues() {

        return
                given().
                        spec(IssueSpecs.initialRequest()).
                        when().
                        get(IssuesData.PROJECTS_PATH_URI + configuration.projectId() + IssuesData.ISSUES_PATH_URI).
                        then().
                        spec(IssueSpecs.verifySuccessResponse()).
                        extract().
                        response();
    }


    public void deleteIssue(Integer issueId) {

        given().
                spec(IssueSpecs.initialRequest()).
                when().
                delete(IssuesData.PROJECTS_PATH_URI + configuration.projectId() + IssuesData.ISSUES_PATH_URI + "/" + issueId).
                then().
                spec(IssueSpecs.verifyDeleteIssue());
    }

    public void issueNotFound(Integer issueId) {

        given().
                spec(IssueSpecs.initialRequest()).
                when().
                get(IssuesData.PROJECTS_PATH_URI + configuration.projectId() + IssuesData.ISSUES_PATH_URI + "/" + issueId).
                then().
                spec(IssueSpecs.verifyNotFoundError());
    }

    public Response editIssue(Integer issueId) {

        return
                given().
                        spec(IssueSpecs.initialRequest()).
                        when().
                        put(IssuesData.PROJECTS_PATH_URI + configuration.projectId() + IssuesData.ISSUES_PATH_URI + "/" + issueId + EDIT_ISSUE).
                        then().
                        spec(IssueSpecs.verifySuccessResponse()).
                        extract().
                    response();
    }

    public void editInvalidIssue(Integer issueId) {

        given().
                spec(IssueSpecs.initialRequest()).
                when().
                put(IssuesData.PROJECTS_PATH_URI + configuration.projectId() + IssuesData.ISSUES_PATH_URI + "/" + issueId + EDIT_ISSUE).
                then().
                spec(IssueSpecs.verifyNotFoundError());
    }
}
