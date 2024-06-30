package com.finallab.biglab.mapper;

import com.finallab.biglab.pojo.Reader;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ReaderMapper {
    @Select("select * from reader_info where card_num=(select card_num from login where account=#{account} and password=#{password})")
    public Reader getReaderInfo(String account,String password);
    @Select("delete from reader_info where card_num=#{card_num}")
    public void deleteReader(String card_num);
    @Select("select isAdmin from login where account=#{account}")
    int isAdmin(String account);
}
