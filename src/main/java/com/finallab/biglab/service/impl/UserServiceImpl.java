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
    public User findByaccount_id(String account_id){
        return userMapper.findByaccount_id(account_id);
    }
    @Override
    public void register(String account_id,String password){
        userMapper.add(account_id,password);
        return;
    }
}
