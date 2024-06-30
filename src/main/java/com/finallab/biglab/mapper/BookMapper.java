package com.finallab.biglab.mapper;

import com.finallab.biglab.pojo.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BookMapper {
    @Select("select * from book_info")
    public Book[] getAllBookInfo();
    @Select("select * from book_info where ISBN=#{ISBN}")
    public Book getBookInfoByISBN(String ISBN);
    @Select("select * from book_info where book_name=#{book_name}")
    public Book getBookInfoByBookName(String book_name);
    @Select("update book_info set avai_num=avai_num-1,borrow_num=borrow_num+1 where ISBN=#{ISBN}")
    public void borrowBook(String ISBN);
    @Select("insert into borrow_info(card_num,ISBN,borrow_date,due_date) values(#{card_num},#{ISBN},#{borrow_date},#{due_date})")
    public void updateBorrowInfo(String card_num, String ISBN, String borrow_date, String due_date);
    @Select("select * from book_info where ISBN=#{ISBN} and avai_num>0 and can_borrow=1")
    public Book BorrowCheck(String ISBN);
    @Select("select * from borrow_info where ISBN=#{ISBN} and card_num=#{card_num} and return_date is null")
    public Book AlreadyBorrowed(String ISBN, String card_num);
    @Select("update book_info set avai_num=avai_num+1,borrow_num=borrow_num-1 where ISBN=#{ISBN}")
    public void returnBook(String ISBN, String card_num);
    @Select("update borrow_info set return_date=#{return_date}, fine=#{fine} where ISBN=#{ISBN} and card_num=#{card_num}")
    public void updateReturnInfo(String ISBN, String card_num, String return_date, String fine);
    @Select("select due_date from borrow_info where ISBN=#{ISBN} and card_num=#{card_num} and return_date is null")
    public String FineCalculation(String ISBN, String card_num);

    @Select("select isAdmin from login where account=#{account}")
    int isAdmin(String account);
    @Select("insert into book_info(ISBN,book_name,publisher,author,avai_num,borrow_num,can_borrow) values(#{ISBN},#{book_name},#{publisher},#{author},#{avai_num},#{borrow_num},#{can_borrow})")
    public void addBook(String ISBN, String book_name, String publisher,String author,int avai_num,int borrow_num,int can_borrow);
    @Select("update book_info set book_name=#{book_name},publisher=#{publisher},author=#{author},avai_num=#{avai_num},borrow_num=#{borrow_num},can_borrow=#{can_borrow} where ISBN=#{ISBN}")
    public void updateBook(String ISBN, String book_name, String publisher,String author,int avai_num,int borrow_num,int can_borrow);
    @Select("delete from book_info where ISBN=#{ISBN}")
    public void deleteBook(String ISBN);
}
