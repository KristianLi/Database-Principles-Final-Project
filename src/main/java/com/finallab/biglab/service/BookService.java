package com.finallab.biglab.service;

import com.finallab.biglab.pojo.Book;

public interface BookService {
    public Book getBookInfoByISBN(String ISBN);
    public Book getBookInfoByBookName(String book_name);
}
