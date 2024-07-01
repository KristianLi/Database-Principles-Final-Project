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
    @PostMapping("/userinfo")
    public Result<User> userinfo(String account){
        if(account==null)return Result.error("用户名不能为空");
        User u=userService.findByaccount(account);
        if(u==null)return Result.error("用户不存在");
        return Result.success(u);
    }

    @PostMapping("/register")
    public Result register(String account,String password,String card_num){
        if(password==null)return Result.error("密码不能为空");
        if(account==null)return Result.error("用户名不能为空");
        User u=userService.findByaccount(account);
        if(u==null){
            userService.register(account,password,card_num);
            return Result.success();
        }
        else return Result.error("用户已存在");
    }
    @PostMapping("/login")
    public Result<String> login(String account,String password){
        if(account==null)return Result.error("用户名不能为空");
        if(password==null)return Result.error("密码不能为空");
        User loginU=userService.findByaccount(account);
        if(loginU==null)return Result.error("用户不存在");
        if(loginU.getPassword().equals(password))return Result.success("登陆成功");
        return Result.error("密码错误");
    }
    @PostMapping("/updatePassword")
    public Result updatePassword(String account,String password){
        if(account==null)return Result.error("用户名不能为空");
        if(password==null)return Result.error("密码不能为空");
        User loginU=userService.findByaccount(account);
        if(loginU==null)return Result.error("用户不存在");
        userService.updatePassword(account,password);
        return Result.success();
    }
    @PostMapping("/deleteUser")
    public Result deleteUser(String account,String deleteAccount){
        if(userService.isAdmin(account)==0)return Result.error("无权限删除用户");
        if(deleteAccount==null)return Result.error("用户名不能为空");
        User loginU=userService.findByaccount(deleteAccount);
        if(loginU==null)return Result.error("用户不存在");
        userService.deleteUser(deleteAccount);
        return Result.success();
    }
}
