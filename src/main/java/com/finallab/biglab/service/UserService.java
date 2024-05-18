package com.finallab.biglab.service;

import com.finallab.biglab.pojo.User;

public interface UserService {
    public User findByUsername(String username);

    void register(String username, String password);
}
