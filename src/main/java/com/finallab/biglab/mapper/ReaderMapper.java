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
    @Select("insert into reader_info(card_num,name,gender,title,borrow_num,borrowed_num,department,phone) values(#{card_num},#{name},#{gender},#{title},#{borrow_num},#{borrowed_num},#{department},#{phone})")
    public void addReader(String card_num,String name,String gender,String title,String borrow_num,String borrowed_num,String department,String phone);
    @Select("update reader_info set name=#{name},gender=#{gender},title=#{title},borrow_num=#{borrow_num},borrowed_num=#{borrowed_num},department=#{department},phone=#{phone} where card_num=#{card_num}")
    public void updateReader(String card_num,String name,String gender,String title,String borrow_num,String borrowed_num,String department,String phone);
    @Select("select * from reader_info where card_num like #{card_num}")
    public Reader[] getReaderInfoByCardNum(String card_num);
    @Select("select * from reader_info where card_num=#{card_num}")
    public Reader getOneReaderInfoByCardNum(String card_num);
    @Select("select * from reader_info where name like #{name}")
    public Reader[] getReaderInfoByname(String name);
    @Select("select * from reader_info where department like #{department}")
    public Reader[] getReaderInfoByDepartment(String title);
    @Select("select * from reader_info where phone like #{phone}")
    public Reader[] getReaderInfoByPhone(String phone);
}
