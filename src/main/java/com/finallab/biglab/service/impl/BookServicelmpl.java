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
    public Book getBookInfoByISBN(String ISBN) {
        return bookMapper.getBookInfoByISBN(ISBN);
    }
    @Override
    public Book getBookInfoByBookName(String book_name) {
        return bookMapper.getBookInfoByBookName(book_name);
    }
}
