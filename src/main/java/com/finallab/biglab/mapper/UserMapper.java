package com.finallab.biglab.mapper;

import com.finallab.biglab.pojo.Reader;
import com.finallab.biglab.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from login where account=#{account}")
    public User findByaccount(String account);
    @Select("insert into login(account,password)"+" values(#{account},#{password})")
    void add(String account, String password);
}
