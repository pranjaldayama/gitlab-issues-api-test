package com.abnamro.assignment.data.factory;

import com.abnamro.assignment.config.Configuration;
import com.abnamro.assignment.config.ConfigurationManager;
import com.abnamro.assignment.model.Issue;
import net.datafaker.Faker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.abnamro.assignment.data.changeless.IssuesData.ISSUE;

public class IssueDataFactory {

    private static final Logger log = LogManager.getLogger(IssueDataFactory.class);
    private static final Faker faker = new Faker();

    private static final Configuration configuration = ConfigurationManager.getConfiguration();
    public static Issue createIssueRequest() {

        var issue = new Issue();
        issue.setIid(faker.number().numberBetween(1,5000));
        issue.setProjectId(configuration.projectId());
        issue.setDescription(faker.text().text(100));
        issue.setTitle(faker.text().text(5));
        issue.setType(ISSUE);
        log.info(issue);
        return issue;
    }

    public static Issue createIssueRequestWithoutTitle() {

        var issue = new Issue();
        issue.setIid(faker.number().numberBetween(1,5000));
        issue.setProjectId(configuration.projectId());
        issue.setDescription(faker.text().text(100));
        issue.setType(ISSUE);
        log.info(issue);
        return issue;
    }
}
