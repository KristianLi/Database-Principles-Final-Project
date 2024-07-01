package com.finallab.biglab.service;

import com.finallab.biglab.pojo.Book;

public interface BookService {
    public Book[] getAllBookInfo();
    public Book[] getBookInfoByISBN(String ISBN);
    public Book getOneBookInfoByISBN(String ISBN);
    public Book[] getBookInfoByBookName(String book_name);
    public Book[] getBookInfoByAuthor(String author);
    public void borrowBook(String ISBN);
    public void updateBorrowInfo(String card_num, String ISBN, String borrow_date, String due_date);
    public Book BorrowCheck(String ISBN);
    public float getFine(String card_num);
    public Book AlreadyBorrowed(String ISBN, String card_num);
    public void returnBook(String ISBN, String card_num);
    public void updateReturnInfo(String ISBN, String card_num, String return_date, String fine);
    public String FineCalculation(String ISBN, String card_num);

    public int isAdmin(String account);
    public void addBook(String ISBN, String book_name, String publisher,String author,int avai_num,int borrow_num,int can_borrow);
    public void updateBook(String ISBN, String book_name, String publisher,String author,int avai_num,int borrow_num,int can_borrow);
    public void deleteBook(String ISBN);
}
