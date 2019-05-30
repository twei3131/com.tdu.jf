package com.tdu.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;

public class ActivitiGraph extends ActivitiGraphUtils
{
    public ActivitiGraph()
    {
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        processEngine.getRepositoryService()
		.createDeployment()
		.name("请假")
		.addClasspathResource("/com/tdu/processes/vacate.bpmn")
		.deploy();

        try
        {
            convertToModel(ActivitiPlugin.processEngine, "Urge:4:17504");
            createModel(ActivitiPlugin.processEngine);
        }
        catch (Exception exp)
        {
           System.out.println("error");
        }
    }
}
