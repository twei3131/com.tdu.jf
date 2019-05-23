package com.tdu.controller;

import com.jfinal.core.Controller;
import com.tdu.model.User;

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

    public void addUser()
    {

    }
}
