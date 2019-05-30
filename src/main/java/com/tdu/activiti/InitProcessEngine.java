package com.tdu.activiti;

import com.jfinal.plugin.activerecord.DbKit;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.activiti.engine.ProcessEngineConfiguration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class InitProcessEngine extends HttpServlet
{
    public void init(ServletConfig config) throws ServletException
    {
        StandaloneProcessEngineConfiguration conf = (StandaloneProcessEngineConfiguration) ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
//		conf.setDatabaseSchema("root");
        conf.setDataSource(DbKit.getConfig().getDataSource()).setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE).setDbHistoryUsed(true);
        conf.setTransactionFactory(new ActivitiTransactionFactory());
        ActivitiPlugin.processEngine = conf.buildProcessEngine();
    }
}
