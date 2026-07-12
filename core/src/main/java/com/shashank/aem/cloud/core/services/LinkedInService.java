package com.shashank.aem.cloud.core.services;

public interface LinkedInService {
    String getAccessToken();
    String getApiEndpoint();
    String getPersonUrn();

    boolean publishPost(String postContent);
}