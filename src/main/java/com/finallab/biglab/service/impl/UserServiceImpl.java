package com.finallab.biglab.service.impl;

import com.finallab.biglab.mapper.UserMapper;
import com.finallab.biglab.pojo.User;
import com.finallab.biglab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByaccount(String account){
        return userMapper.findByaccount(account);
    }
    @Override
    public void register(String account,String password,String card_num){
        userMapper.add(account,password,card_num);
        return;
    }
    @Override
    public int isAdmin(String account){
        return userMapper.isAdmin(account);
    }
    @Override
    public void updatePassword(String account,String password){
        userMapper.updatePassword(account,password);
        return;
    }
    @Override
    public void deleteUser(String account){
        userMapper.deleteUser(account);
        return;
    }
}
