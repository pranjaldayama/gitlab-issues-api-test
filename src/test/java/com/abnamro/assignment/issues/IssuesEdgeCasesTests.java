package com.abnamro.assignment.issues;

import com.abnamro.assignment.BaseTest;
import com.abnamro.assignment.client.IssueClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.abnamro.assignment.data.changeless.IssuesData.INVALID_ISSUE_ID;
import static com.abnamro.assignment.data.changeless.TestGroups.EDGE;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

public class IssuesEdgeCasesTests extends BaseTest {

    private final IssueClient issueClient = new IssueClient();
    @Test
    @Tag(EDGE)
    @DisplayName("Should not be able to update issue with invalid issue ID")
    void verifyErrorOnEditingInvalidIssue() {

        //Verify editing the invalid issue returns 404 NOT FOUND error
        issueClient.editInvalidIssue(INVALID_ISSUE_ID);
    }

    @Test
    @Tag(EDGE)
    @DisplayName("Should not be able to create issue without Authorization Token")
    void verifyErrorOnCreatingIssueWithoutToken() {

        // Verify unauthorized error when user tries to create issue without Authorization token in the header
        issueClient.createIssueWithoutAuthorizationToken();
    }

    @Test
    @Tag(EDGE)
    @DisplayName("Should not be able to create issue without mandatory field - title")
    void verifyErrorOnCreationIssueWithoutTitle() {

        // Verify bad request error when creating the issue without title
        var response = issueClient.createIssueWithoutMandatoryFields();

        assertSoftly(soft -> {
            soft.assertThat(response.path("error").toString()).isEqualTo("title is missing");
        });
    }
}
