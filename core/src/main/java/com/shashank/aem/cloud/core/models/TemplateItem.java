package com.shashank.aem.cloud.core.models;

public class TemplateItem {

    @ValueMapValue
    private String title;

    @ValueMapValue
    private String content;

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}