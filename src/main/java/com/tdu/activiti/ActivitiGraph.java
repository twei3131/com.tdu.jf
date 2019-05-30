package com.tdu.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class ActivitiGraph extends ActivitiGraphUtils
{
    Log logger = LogFactory.getLog(this.getClass());
    public ActivitiGraph()
    {
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        processEngine.getRepositoryService()
		.createDeployment()
		.name("请假")
		.addClasspathResource("/com/tdu/processes/vacate.bpmn")
		.deploy();
        System.out.println("部署完毕");
    }
}
