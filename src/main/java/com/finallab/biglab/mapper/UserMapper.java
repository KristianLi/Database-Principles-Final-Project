package com.finallab.biglab.mapper;

import com.finallab.biglab.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from user where username=#{username}")
    public User findByUsername(String username);
    @Select("insert into user(username,password)"+" values(#{username},#{password})")
    void add(String username, String password);
}
