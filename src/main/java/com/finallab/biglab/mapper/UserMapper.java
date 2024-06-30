package com.finallab.biglab.mapper;

import com.finallab.biglab.pojo.Reader;
import com.finallab.biglab.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from login where account=#{account}")
    public User findByaccount(String account);
    @Select("insert into login(account,password,card_num,isAdmin)"+" values(#{account},#{password},#{card_num},0)")
    void add(String account, String password, String card_num);
    @Select("select isAdmin from login where account=#{account}")
    int isAdmin(String account);
    @Select("update login set password=#{password} where account=#{account}")
    void updatePassword(String account, String password);
    @Select("delete from login where account=#{account}")
    void deleteUser(String account);
}
