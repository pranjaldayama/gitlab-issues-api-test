package com.abnamro.assignment.issues;

import com.abnamro.assignment.BaseTest;
import com.abnamro.assignment.client.IssueClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.abnamro.assignment.data.changeless.IssuesData.AUTHOR_NAME;
import static com.abnamro.assignment.data.changeless.IssuesData.AUTHOR_USERNAME;
import static com.abnamro.assignment.data.changeless.IssuesData.DESCRIPTION;
import static com.abnamro.assignment.data.changeless.IssuesData.EDITED_TITLE;
import static com.abnamro.assignment.data.changeless.IssuesData.IID;
import static com.abnamro.assignment.data.changeless.IssuesData.TITLE;
import static com.abnamro.assignment.data.changeless.IssuesData.TYPE;
import static com.abnamro.assignment.data.changeless.TestGroups.CRUD;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

public class IssuesCRUDTests extends BaseTest {

    private final IssueClient issueClient = new IssueClient();

    @Test
    @Tag(CRUD)
    @DisplayName("Create and fetch the issue details")
    public void shouldCreateIssue() {

        //Create issue
        var createdIssueResponse = issueClient.createIssue();
        Integer iid = createdIssueResponse.path(IID);

        // Fetch (get) the issue which is created in above step
        var expectedIssueResponse = issueClient.getIssue(iid);

        //Verify that the details of the created issue and the details of the fetched issue are same
        assertSoftly(soft -> {
            soft.assertThat(expectedIssueResponse.path(IID).toString()).isEqualTo(createdIssueResponse.path(IID).toString());
            soft.assertThat(expectedIssueResponse.path(TITLE).toString()).isEqualTo(createdIssueResponse.path(TITLE).toString());
            soft.assertThat(expectedIssueResponse.path(DESCRIPTION).toString()).isEqualTo(createdIssueResponse.path(DESCRIPTION).toString());
            soft.assertThat(expectedIssueResponse.path(TYPE).toString()).isEqualTo(createdIssueResponse.path(TYPE).toString());
            soft.assertThat(expectedIssueResponse.path(AUTHOR_NAME).toString()).isEqualTo(createdIssueResponse.path(AUTHOR_NAME).toString());
            soft.assertThat(expectedIssueResponse.path(AUTHOR_USERNAME).toString()).isEqualTo(createdIssueResponse.path(AUTHOR_USERNAME).toString());
        });
    }

    @Test
    @Tag(CRUD)
    @DisplayName("Create the issue and delete it")
    public void shouldDeleteIssue() {

        //Create issue
        var createdIssueResponse = issueClient.createIssue();
        Integer iid = createdIssueResponse.path(IID);

        //Delete the issue created in above step
        issueClient.deleteIssue(iid);

        //Verify that the issue deleted in the above step is not present
        issueClient.issueNotFound(iid);
    }

    @Test
    @Tag(CRUD)
    @DisplayName("Create the issue and edit it")
    public void shouldEditIssue() {

        //Create issue
        var createdIssueResponse = issueClient.createIssue();
        Integer iid = createdIssueResponse.path(IID);

        //Edit the issue created in above step
        var editedIssueResponse = issueClient.editIssue(iid);

        //Verify that the issue is edited
        assertSoftly(soft -> {
            soft.assertThat(editedIssueResponse.path(TITLE).toString()).isEqualTo(EDITED_TITLE);
            soft.assertThat(editedIssueResponse.path(DESCRIPTION).toString()).isEqualTo(createdIssueResponse.path(DESCRIPTION).toString());
        });
    }
}
