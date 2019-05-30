package com.tdu.activiti;

import com.jfinal.plugin.IPlugin;
import org.apache.log4j.Logger;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import com.jfinal.plugin.activerecord.DbKit;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.lang3.StringUtils;

public class ActivitiPlugin implements IPlugin
{
    private static Logger logger = Logger.getLogger(ActivitiPlugin.class);

    public static ProcessEngine processEngine = null;
    private static ProcessEngineConfiguration processEngineConfiguration = null;
    private boolean isStarted = false;

    @Override
    public boolean start()
    {
        try
        {
            createProcessEngine();
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean stop()
    {
        ProcessEngines.destroy();
        isStarted = false;
        return true;
    }

    private Boolean createProcessEngine() throws Exception
    {
        if (isStarted)
            return true;

        StandaloneProcessEngineConfiguration conf = (StandaloneProcessEngineConfiguration) ProcessEngineConfiguration
                .createStandaloneProcessEngineConfiguration();
//		conf.setDatabaseSchema("cwbase35_9999");
        conf.setDataSource(DbKit.getConfig().getDataSource())
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE)
                .setDbHistoryUsed(true);
//		conf.setTransactionsExternallyManaged(true); // 使用托管事务工厂
        conf.setTransactionFactory(new ActivitiTransactionFactory());
        ActivitiPlugin.processEngine = conf.buildProcessEngine();
        isStarted = true;
        //开启流程引擎
        logger.info("启动流程引擎.......");
        ActivitiGraph activitiGraph = new ActivitiGraph();
        logger.info("启动流程引擎完成");
        return isStarted;
    }
}
