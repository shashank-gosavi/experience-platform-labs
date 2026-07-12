package com.shashank.aem.cloud.core.servlets;

import com.shashank.aem.cloud.core.services.LinkedInService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(
    service = Servlet.class,
    property = {
        "sling.servlet.paths=/bin/linkedin/callback",
        "sling.servlet.methods=POST"
    }
)

public class LinkedInOAuthCallbackServlet extends SlingAllMethodsServlet {

    @Reference
    private LinkedInService linkedInService;

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        String authorizationCode = request.getParameter("code");
        String accessToken = linkedInService.exchangeAuthorizationCodeForAccessToken(authorizationCode);

        if (accessToken != null) {
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("Access Token: " + accessToken);
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Failed to obtain access token");
        }
    }
}