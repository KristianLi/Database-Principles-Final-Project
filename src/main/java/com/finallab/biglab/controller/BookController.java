package com.finallab.biglab.controller;
import com.finallab.biglab.pojo.Book;
import com.finallab.biglab.pojo.Result;
import com.finallab.biglab.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
@CrossOrigin(origins = "http://localhost:8000")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/ISBN")
    public Result<Book> getBookMessage1(@RequestParam String ISBN) {
        Book book = bookService.getBookInfoByISBN(ISBN);
        if (book != null) {
            return Result.success(book);
        } else {
            return Result.error("Book not found");
        }
    }
    @GetMapping("/name")
    public Result<Book> getBookMessage2(@RequestParam String book_name) {
        Book book = bookService.getBookInfoByBookName(book_name);
        if (book != null) {
            return Result.success(book);
        } else {
            return Result.error("Book not found");
        }
    }
}
