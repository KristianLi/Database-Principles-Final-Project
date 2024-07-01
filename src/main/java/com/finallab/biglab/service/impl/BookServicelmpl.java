package com.finallab.biglab.service.impl;
import com.finallab.biglab.mapper.BookMapper;
import com.finallab.biglab.pojo.Book;
import com.finallab.biglab.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServicelmpl implements BookService {
    @Autowired
    private BookMapper bookMapper;
    @Override
    public Book[] getBookInfoByISBN(String ISBN) {
        return bookMapper.getBookInfoByISBN(ISBN);
    }
    @Override
    public Book[] getBookInfoByBookName(String book_name) {
        return bookMapper.getBookInfoByBookName(book_name);
    }
    @Override
    public Book[] getAllBookInfo() {
        return bookMapper.getAllBookInfo();
    }
    @Override
    public Book[] getBookInfoByAuthor(String author) {
        return bookMapper.getBookInfoByAuthor(author);
    }
    @Override
    public void borrowBook(String ISBN) {
        bookMapper.borrowBook(ISBN);
    }
    @Override
    public void updateBorrowInfo(String card_num, String ISBN, String borrow_date, String due_date){
        bookMapper.updateBorrowInfo(card_num, ISBN, borrow_date, due_date);
    }
    @Override
    public Book BorrowCheck(String ISBN){
        return bookMapper.BorrowCheck(ISBN);
    }
    @Override
    public float getFine(String card_num){
        return bookMapper.getFine(card_num);
    }
    @Override
    public Book AlreadyBorrowed(String ISBN, String card_num){
        return bookMapper.AlreadyBorrowed(ISBN, card_num);
    }
    @Override
    public void returnBook(String ISBN, String card_num){
        bookMapper.returnBook(ISBN, card_num);
    }
    @Override
    public void updateReturnInfo(String ISBN, String card_num, String return_date, String fine){
        bookMapper.updateReturnInfo(ISBN, card_num, return_date, fine);
    }
    @Override
    public String FineCalculation(String ISBN, String card_num){
        return bookMapper.FineCalculation(ISBN, card_num);
    }
    @Override
    public void addBook(String ISBN, String book_name, String publisher,String author,int avai_num,int borrow_num,int can_borrow){
        bookMapper.addBook(ISBN, book_name, publisher, author, avai_num, borrow_num, can_borrow);
    }
    @Override
    public int isAdmin(String account){
        return bookMapper.isAdmin(account);
    }
    @Override
    public void updateBook(String ISBN, String book_name, String publisher,String author,int avai_num,int borrow_num,int can_borrow){
        bookMapper.updateBook(ISBN, book_name, publisher, author, avai_num, borrow_num, can_borrow);
    }
    @Override
    public void deleteBook(String ISBN){
        bookMapper.deleteBook(ISBN);
    }
}
