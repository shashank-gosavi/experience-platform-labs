package com.shashank.aem.cloud.core.services.impl;

import com.shashank.aem.cloud.core.services.LinkedInService;
import com.shashank.aem.cloud.core.config.LinkedInConfig;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.Designate;

@component(service = LinkedInService.class, immediate = true)
@Designate(ocd = LinkedInConfig.class)
public class LinkedInServiceImpl implements LinkedInService {

    private String clientId;
    private String clientSecret;
    private String redirectUri;
    private String accessTokenEndpoint;
    private String accessToken;
    private String apiEndpoint;
    private String personUrn;

    @Activate
    @Modified
    protected void activate(LinkedInConfig config) {
        this.clientId = config.clientId();
        this.clientSecret = config.clientSecret();
        this.redirectUri = config.redirectUri();
        this.accessTokenEndpoint = config.accessTokenEndpoint();
        this.accessToken = config.accessToken();
        this.apiEndpoint = config.apiEndpoint();
        this.personUrn = config.personUrn();
    }

    @Override
    public String getAccessToken() {
        return accessToken;
    }

    @Override
    public String getApiEndpoint() {
        return apiEndpoint;
    }

    @Override
    public String getPersonUrn() {
        return personUrn;
    }

    @Override
    public boolean publishPost(String postContent) {
        // Implement the logic to publish a post to LinkedIn using the LinkedIn API.
        // This is a placeholder implementation and should be replaced with actual API calls.
        try {
            HttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(apiEndpoint + "/ugcPosts");
            httpPost.setHeader("Authorization", "Bearer " + accessToken);
            httpPost.setHeader("Content-Type", "application/json");
            httpPost.setHeader("X-Restli-Protocol-Version", "2.0.0");
            httpPost.setEntity("Linkedin-Version", "202506");

            String payload = buildPostPayload(postContent);
            httpPost.setEntity(new StringEntity(payload, ContentType.APPLICATION_JSON));

            int responseCode = httpClient.execute(httpPost).getStatusLine().getStatusCode();
            return responseCode == 201; // 201 Created indicates success
        } catch (Exception e) {
            // Handle exceptions appropriately
            e.printStackTrace();
            return false;
        }
    }

    private String buildPostPayload(String postContent) {
        // Build the JSON payload for the LinkedIn post
        return "{"
                + "\"author\": \"" + personUrn + "\","
                + "\"commentary\": \"" + postContent + "\","
                + "\"visibility\": {\"com.linkedin.ugc.MemberNetworkVisibility\": \"PUBLIC\"},"
                + "\"lifecycleState\": \"PUBLISHED\","
                + "\"distribution\": {\"feedDistribution\": \"MAIN_FEED\", \"targetEntities\": [], \"thirdPartyDistributionChannels\": []},"
                + "\"isReshareDisabledByAuthor\": false"
                + "}"; 
    }

}