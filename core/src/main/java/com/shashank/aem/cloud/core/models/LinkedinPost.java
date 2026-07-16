package com.shashank.aem.cloud.core.models;

import aQute.bnd.annotation.ConsumerType;
import com.adobe.cq.export.json.ComponentExporter;

/**
 * Defines the {@code LinkedinPost} Sling Model used for the {@code aem-cloud-practice/components/linkedin-post} component.
 */
@ConsumerType
public interface LinkedinPost extends ComponentExporter {

    String getTemplate();
    
}
