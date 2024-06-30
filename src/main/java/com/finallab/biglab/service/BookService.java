package com.finallab.biglab.service;

import com.finallab.biglab.pojo.Book;

public interface BookService {
    public Book[] getAllBookInfo();
    public Book getBookInfoByISBN(String ISBN);
    public Book getBookInfoByBookName(String book_name);
    public void borrowBook(String ISBN);
    public void updateBorrowInfo(String card_num, String ISBN, String borrow_date, String due_date);
    public Book BorrowCheck(String ISBN);
    public Book AlreadyBorrowed(String ISBN, String card_num);
    public void returnBook(String ISBN, String card_num);
    public void updateReturnInfo(String ISBN, String card_num, String return_date, String fine);
    public String FineCalculation(String ISBN, String card_num);
}
