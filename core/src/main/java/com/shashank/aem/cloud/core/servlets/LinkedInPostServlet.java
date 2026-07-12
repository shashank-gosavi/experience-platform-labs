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

@component(
    service = Servlet.class,
    property = {
        "sling.servlet.paths=/bin/linkedin/post",
        "sling.servlet.methods=POST"
    }
)

public class LinkedInServlet extends SlingAllMethodsServlet {

    @Reference
    private LinkedInService linkedInService;

    @Override
    protected void doPost( SlingHttpServletRequest request, SlingHttpServletResponse response ) throws ServletException, IOException {
        String postContent = request.getParameter("content");
        String accessToken = request.getParameter("accessToken");

        if (postContent == null || accessToken == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Missing required parameters: content and accessToken");
            return;
        }

        try {
            boolean success = linkedInService.postToLinkedIn(postContent, accessToken);
            if (success) {
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write("Post successful");
            } else {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("Failed to post to LinkedIn");
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error occurred: " + e.getMessage());
        }
    }
}