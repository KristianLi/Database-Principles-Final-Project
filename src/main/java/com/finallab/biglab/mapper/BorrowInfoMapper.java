package com.finallab.biglab.mapper;

import com.finallab.biglab.pojo.Borrow;
import com.finallab.biglab.pojo.findBorrow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BorrowInfoMapper {
    @Select("select * from borrow_info,book_info,reader_info where borrow_info.ISBN=book_info.ISBN and borrow_info.card_num=reader_info.card_num")
    public findBorrow[] getAllBorrowInfo();
    @Select("select * from borrow_info where card_num like #{card_num}")
    public Borrow[] getBorrowInfo(String card_num);
    @Select("select * from borrow_info where card_num in (select card_num from reader_info where reader_name like #{reader_name}))")
    public Borrow[] getBorrowInfoByName(String reader_name);
    @Select("select * from borrow_info where card_num in (select card_num from reader_info where department like #{department})")
    public Borrow[] getBorrowInfoByDepartment(String department);
    @Select("select * from borrow_info where card_num in (select card_num from reader_info where phone like #{phone})")
    public Borrow[] getBorrowInfoByPhone(String phone);
    @Select("select isAdmin from login where account = #{account}")
    public int isAdmin(String account);
    @Select("select * from borrow_info,book_info,reader_info where borrow_info.ISBN=book_info.ISBN and borrow_info.card_num=reader_info.card_num and due_date<#{date}")
    public findBorrow[] getAllDateBorrow(String date);
    @Select("select * from borrow_info,book_info,reader_info where borrow_info.ISBN=book_info.ISBN and borrow_info.card_num=reader_info.card_num and borrow_info.card_num like #{card_num}")
    public findBorrow[] getFindBorrowInfoByCardNum(String card_num);
    @Select("select * from borrow_info,book_info,reader_info where borrow_info.ISBN=book_info.ISBN and borrow_info.card_num=reader_info.card_num and reader_info.name like #{name}")
    public findBorrow[] getFindBorrowInfoByName(String name);
    @Select("select * from borrow_info,book_info,reader_info where borrow_info.ISBN=book_info.ISBN and borrow_info.card_num=reader_info.card_num and reader_info.department like #{department}")
    public findBorrow[] getFindBorrowInfoByDepartment(String department);
    @Select("select * from borrow_info,book_info,reader_info where borrow_info.ISBN=book_info.ISBN and borrow_info.card_num=reader_info.card_num and reader_info.phone like #{phone}")
    public findBorrow[] getFindBorrowInfoByPhone(String phone);
}
