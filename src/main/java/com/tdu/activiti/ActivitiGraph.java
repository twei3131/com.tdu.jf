package com.tdu.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.apache.log4j.Logger;

public class ActivitiGraph extends ActivitiGraphUtils
{
	Logger logger = Logger.getLogger(ActivitiGraph.class);
    public ActivitiGraph()
    {
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        processEngine.getRepositoryService()
		.createDeployment()
		.name("请假")
		.addClasspathResource("/com/tdu/processes/vacate.bpmn")
		.deploy();
        logger.info("部署完毕");
        System.out.println("部署完毕");
    }
}
