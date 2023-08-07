package com.abnamro.assignment.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class Issue {

    @SerializedName("project_id")
    public String projectId;
    public String title;
    public Object description;
    public Integer id;
    public Integer iid;
    public String state;
    @SerializedName("created_at")
    public String createdAt;
    public List<String> labels;
    public List<String> assignees;
    public String type;
    public String severity;
    public Boolean confidential;
    @SerializedName("issue_type")
    public String issueType;
    @SerializedName("web_url")
    public String webUrl;
    private Author author;
}
