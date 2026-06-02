package com.shashank.aem.cloud.core.workflows;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import com.adobe.granite.workflow.exec.WorkItem;

import org.osgi.service.component.annotations.Component;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

@Component(
		service = WorkflowProcess.class,
		property = {"process.label=Asset Upload Notification Process"}
		)
		
public class AssetUploadNotificationProcess implements WorkflowProcess {
	private static final Logger LOGGER = LoggerFactory.getLogger(AssetUploadNotificationProcess.class);

	@Override
	public void execute(WorkItem item, WorkflowSession session, MetaDataMap args) throws WorkflowException {
		String assetPath = item.getWorkflowData().getPayload().toString();

		LOGGER.info("Asset uploaded at: {}", assetPath);

		// Add Workflow message
		item.getWorkflowData().getMetaDataMap().put("message", "Your asset is successfully uploaded to DAM and waiting for replication.");
	}
}
