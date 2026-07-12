package com.shashank.aem.cloud.core.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectTypeDefinition;

@ObjectTypeDefinition(
    name = "LinkedIn Configuration",
    description = "Configuration for LinkedIn API integration"
)

public @interface LinkedInConfig {

    @AttributeDefinition(
        name = "Client ID",
        description = "LinkedIn application client ID"
    )
    String clientId();

    @AttributeDefinition(
        name = "Client Secret",
        description = "LinkedIn application client secret"
    )
    String clientSecret();

    @AttributeDefinition(
        name = "Redirect URI",
        description = "Redirect URI for LinkedIn OAuth"
    )
    String redirectUri();

    @AttributeDefinition(
        name = "Access Token Endpoint",
        description = "Endpoint to obtain access token from LinkedIn"
    )
    String accessTokenEndpoint();

    @AttributeDefinition(
        name = "Access Token",
        description = "Access token for LinkedIn API"
    )
    String accessToken();

    @AttributeDefinition(
        name = "API Endpoint",
        description = "Base API endpoint for LinkedIn"
    )
    String apiEndpoint();

    @AttributeDefinition(
        name = "Person URN",
        description = "URN of the authenticated user on LinkedIn"
    )
    String personUrn();
}