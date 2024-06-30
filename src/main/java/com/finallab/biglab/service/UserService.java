package com.finallab.biglab.service;

import com.finallab.biglab.pojo.User;

public interface UserService {
    public User findByaccount(String account);

    void register(String accout_id, String password);
}
