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
}
