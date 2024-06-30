package com.finallab.biglab.controller;

import com.finallab.biglab.pojo.Result;
import com.finallab.biglab.pojo.User;
import com.finallab.biglab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:8000") // 允许来自 http://localhost:8000 的跨域请求
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(String account_id,String password){
        if(password==null)return Result.error("密码不能为空");
        if(account_id==null)return Result.error("用户名不能为空");
        User u=userService.findByaccount_id(account_id);
        if(u==null){
            userService.register(account_id,password);
            return Result.success();
        }
        else return Result.error("用户已存在");
    }
    @PostMapping("/login")
    public Result<String> login(String account_id,String password){
        if(account_id==null)return Result.error("用户名不能为空");
        if(password==null)return Result.error("密码不能为空");
        User loginU=userService.findByaccount_id(account_id);
        if(loginU==null)return Result.error("用户不存在");
        if(loginU.getPassword().equals(password))return Result.success("登陆成功");
        return Result.error("密码错误");
    }
}
