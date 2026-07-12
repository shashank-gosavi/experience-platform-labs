package com.shashank.aem.cloud.core.models;

import com.shashank.aem.cloud.core.models.TemplateItem;
import com.shashank.aem.cloud.core.models.ImageItem;

@Model(
    adaptables = Resource.class,
    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class LinkedInPublisher {

    @ChildResource(name = "templates")
    private List<TemplateItem> templates;

    @ChildResource(name = "images")
    private List<ImageItem> images;

    public List<TemplateItem> getTemplates() {
        return templates;
    }

    public List<ImageItem> getImages() {
        return images;
    }
}
