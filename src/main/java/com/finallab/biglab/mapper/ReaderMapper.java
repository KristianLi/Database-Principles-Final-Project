package com.finallab.biglab.mapper;

import com.finallab.biglab.pojo.Reader;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ReaderMapper {
    @Select("select * from reader_info")
    public Reader[] getReaderInfo();
    @Select("delete from reader_info where card_num=#{card_num}")
    public void deleteReader(String card_num);
    @Select("select isAdmin from login where account=#{account}")
    int isAdmin(String account);
    @Select("insert into reader_info(card_num,name,gender,title,department,phone)"+" values(#{card_num},#{name},#{gender},#{title},#{department},#{phone})")
    public void addReader(String account,String card_num,String name,String gender,String title,String department,String phone);
    @Select("select * from reader_info where card_num like #{card_num}")
    public Reader[] getReaderInfoByCardNum(String card_num);
    @Select("select * from reader_info where name like #{name}")
    public Reader[] getReaderInfoByname(String name);
    @Select("select * from reader_info where department like #{department}")
    public Reader[] getReaderInfoByDepartment(String title);
    @Select("select * from reader_info where phone like #{phone}")
    public Reader[] getReaderInfoByPhone(String phone);
}
