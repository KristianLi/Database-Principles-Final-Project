package com.finallab.biglab.mapper;

import com.finallab.biglab.pojo.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BookMapper {
    @Select("select * from book_info where ISBN=#{ISBN}")
    public Book getBookInfoByISBN(String ISBN);
    @Select("select * from book_info where book_name=#{book_name}")
    public Book getBookInfoByBookName(String book_name);
}
