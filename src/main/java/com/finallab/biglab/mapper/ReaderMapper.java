package com.finallab.biglab.mapper;

import com.finallab.biglab.pojo.Reader;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ReaderMapper {
    @Select("select * from reader_info where card_num=(select card_num from login where account=#{account})")
    public Reader getReaderInfo(String account);
}
