package com.tdu.controller;

import com.jfinal.core.Controller;
import com.tdu.model.User;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.util.HashMap;
import java.util.Map;

public class UserController extends Controller
{
    public void index()
    {
        User user = User.dao.findById(25);
        renderJson(user);
    }

    public void toUser()
    {
        setAttr("user", "陶伟");
        render("/user/user.jsp");
    }

    public void testAct()
    {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceByKey("apply");
        Task vacationTask = processEngine.getTaskService().createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
        processEngine.getTaskService().setAssignee(vacationTask.getId(), "1");
        Map<String, Object> map = new HashMap<>();
        map.put("days", 3);
        map.put("formId", 1);
        processEngine.getTaskService().complete(vacationTask.getId(),map);
        renderText("Done!");
    }

}
