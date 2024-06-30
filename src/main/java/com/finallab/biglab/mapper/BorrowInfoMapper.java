package com.finallab.biglab.mapper;

import com.finallab.biglab.pojo.Borrow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BorrowInfoMapper {
    @Select("select * from borrow_info where card_num=#{card_num}")
    public Borrow[] getBorrowInfo(String card_num);
}
