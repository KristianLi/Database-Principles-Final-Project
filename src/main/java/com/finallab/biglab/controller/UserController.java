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
    public Result register(String username,String password){
        if(password==null)return Result.error("密码不能为空");
        if(username==null)return Result.error("用户名不能为空");
        User u=userService.findByUsername(username);
        if(u==null){
            userService.register(username,password);
            return Result.success();
        }
        else return Result.error("用户已存在");
    }
    @PostMapping("/login")
    public Result<String> login(String username,String password){
        if(username==null)return Result.error("用户名不能为空");
        if(password==null)return Result.error("密码不能为空");
        User loginU=userService.findByUsername(username);
        if(loginU==null)return Result.error("用户不存在");
        if(loginU.getPassword().equals(password))return Result.success("登陆成功");
        return Result.error("密码错误");
    }
}
