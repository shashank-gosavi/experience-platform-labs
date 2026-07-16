package com.shashank.aem.cloud.core.models.impl;

import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;
import com.shashank.aem.cloud.core.models.LinkedinPost;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.InjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(
        adaptables = {SlingHttpServletRequest.class,Resource.class},
        adapters = {LinkedinPost.class, ComponentExporter.class},
        resourceType = LinkedinPostImpl.RESOURCE_TYPE
)
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class LinkedinPostImpl extends BaseModel implements LinkedinPost {

    public static final String RESOURCE_TYPE = "aem-cloud-practice/components/linkedin-post";

    @ValueMapValue(injectionStrategy = InjectionStrategy.OPTIONAL)
    private String template;

    @Override
    public String getTemplate() {
        return template;
    }

    @Override
    public String getExportedType() {
        return RESOURCE_TYPE;
    }
}
