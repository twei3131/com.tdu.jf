package com.tdu.controller;

import com.google.gson.Gson;
import com.jfinal.core.Controller;
import com.tdu.model.User;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserController extends Controller
{
    Log logger = LogFactory.getLog(UserController.class);
    public void index()
    {
        User user = User.dao.findById(25);
        setAttr("user", user);
        renderFreeMarker("/user/user.ftl");
        //renderJson(user);
    }

    public void toUser()
    {
        setAttr("user", "陶伟");
        render("/user/user2.ftl");
    }

    public void testAct()
    {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceByKey("apply");
        HistoryService historyService = processEngine.getHistoryService();
        List<HistoricProcessInstance> list = historyService.createHistoricProcessInstanceQuery().orderByProcessInstanceStartTime()
                .asc()//排序
                .list();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        Task vacationTask = processEngine.getTaskService().createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
        processEngine.getTaskService().setAssignee(vacationTask.getId(), "1");
        Map<String, Object> map = new HashMap<>();
        map.put("days", 3);
        map.put("formId", 1);
        processEngine.getTaskService().complete(vacationTask.getId(),map);
        renderText("Done! " + json);
    }

}
