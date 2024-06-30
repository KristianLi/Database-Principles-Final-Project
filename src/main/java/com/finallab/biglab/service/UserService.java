package com.finallab.biglab.service;

import com.finallab.biglab.pojo.User;

public interface UserService {
    public User findByaccount_id(String account_id);

    void register(String accout_id, String password);
}
