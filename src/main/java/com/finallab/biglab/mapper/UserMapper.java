package com.finallab.biglab.mapper;

import com.finallab.biglab.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from login where account_id=#{account_id}")
    public User findByaccount_id(String account_id);
    @Select("insert into login(account_id,password)"+" values(#{account_id},#{password})")
    void add(String account_id, String password);
}
