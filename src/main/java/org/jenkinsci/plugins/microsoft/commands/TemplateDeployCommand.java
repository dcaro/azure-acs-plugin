/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 */
package org.jenkinsci.plugins.microsoft.commands;

import org.jenkinsci.plugins.microsoft.commands.DeploymentState;
import org.jenkinsci.plugins.microsoft.exceptions.AzureCloudException;
import org.jenkinsci.plugins.microsoft.services.AzureManagementServiceDelegate;
import org.jenkinsci.plugins.microsoft.services.IARMTemplateServiceData;

public class TemplateDeployCommand implements ICommand<TemplateDeployCommand.ITemplateDeployCommandData> {
	public void execute(TemplateDeployCommand.ITemplateDeployCommandData context) {
	    	context.logStatus("Starting deployment");
	        try {
	        	String deploymentName = AzureManagementServiceDelegate.deploy(context.getArmTemplateServiceData());
				context.setDeploymentState(DeploymentState.Success);
				context.setDeploymentName(deploymentName);
		        context.logStatus("Deployment started.");
			} catch (AzureCloudException e) {
				context.logError("Error starting deployment:", e);
			}
	}
	
	public interface ITemplateDeployCommandData extends IBaseCommandData {
		public IARMTemplateServiceData getArmTemplateServiceData();
		public void setDeploymentName(String deploymentName);
	}
}
