package com.abnamro.assignment.issues;

import com.abnamro.assignment.config.ConfigurationManager;
import com.abnamro.assignment.data.changeless.IssuesData;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class IssuesTests extends IssuesBase {

    @Test
    @DisplayName("Should lists the issues created in a project")
    void getIssue() {
        var configuration = ConfigurationManager.getConfiguration();

        given().
                when().
                get(IssuesData.PROJECTS_PATH_URI + configuration.projectId() + IssuesData.ISSUES_PATH_URI).
                then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    @DisplayName("Should create the issue in a project")
    void createIssue() {
        var configuration = ConfigurationManager.getConfiguration();

        given().
                when().
                post(IssuesData.PROJECTS_PATH_URI + configuration.projectId() + IssuesData.ISSUES_PATH_URI).
                then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    @DisplayName("Should edit the issue in a project")
    void editIssue() {
        var configuration = ConfigurationManager.getConfiguration();

        given().
                when().
                put(IssuesData.PROJECTS_PATH_URI + configuration.projectId() + IssuesData.ISSUES_PATH_URI).
                then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    @DisplayName("Should delete the issue in a project")
    void deleteIssue() {
        var configuration = ConfigurationManager.getConfiguration();

        given().
                when().
                delete(IssuesData.PROJECTS_PATH_URI + configuration.projectId() + IssuesData.ISSUES_PATH_URI).
                then()
                .statusCode(HttpStatus.SC_OK);
    }
}
