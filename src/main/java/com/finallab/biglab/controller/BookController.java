package com.finallab.biglab.controller;
import com.finallab.biglab.pojo.Book;
import com.finallab.biglab.pojo.Result;
import com.finallab.biglab.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
    @GetMapping("/all")
    public Result<Book[]> getBookMessage3() {
        Book[] books = bookService.getAllBookInfo();
        if (books != null) {
            return Result.success(books);
        } else {
            return Result.error("Book not found");
        }
    }
    @PostMapping("/borrow")
    public Result<String> borrowBook(String ISBN,String card_num) {
        if (bookService.BorrowCheck(ISBN) == null) {
            return Result.error(ISBN + " is not available");
        }
        if (bookService.AlreadyBorrowed(ISBN, card_num) != null) {
            return Result.error("You have already borrowed this book");
        }
        bookService.borrowBook(ISBN);
        // 获取当前日期
        LocalDate currentDate = LocalDate.now();
        LocalDate dueDate = currentDate.plusDays(30);
        // 定义日期格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // 将日期格式化为字符串
        String formattedDate = currentDate.format(formatter);
        String formattedDueDate = dueDate.format(formatter);
        bookService.updateBorrowInfo(card_num, ISBN, formattedDate, formattedDueDate);
        return Result.success("Borrow successfully");
    }
    @PostMapping("/return")
    public Result<String> returnBook(String ISBN, String card_num) {
        if(bookService.AlreadyBorrowed(ISBN, card_num) == null) {
            return Result.error("You haven't borrowed this book");
        }
        int fine=0;
        String dueDate = bookService.FineCalculation(ISBN, card_num);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            // 将字符串解析为LocalDate对象
            LocalDate date = LocalDate.parse(dueDate, formatter);
        } catch (DateTimeParseException e) {
            System.err.println("日期格式错误: " + e.getMessage());
        }
        bookService.returnBook(ISBN,card_num);
        // 获取当前日期
        LocalDate currentDate = LocalDate.now();
        // 计算逾期天数
        int days = (int) (currentDate.toEpochDay() - LocalDate.parse(dueDate, formatter).toEpochDay());
        if (days > 0) {
            fine = days * 2;
        }
        // 将日期格式化为字符串
        String formattedDate = currentDate.format(formatter);
        bookService.updateReturnInfo(ISBN, card_num, formattedDate, String.valueOf(fine));
        return Result.success("Return successfully");
    }
    @PostMapping("/addbook")
    public Result<String> AddBook(String ISBN, String book_name, String publisher, String author, int avai_num, int borrow_num, int can_borrow,String account) {
        if(bookService.isAdmin(account)==0){
            return Result.error("You are not an administrator");
        }
        if(ISBN == null || book_name == null || publisher == null || author == null || avai_num < 0 || borrow_num < 0 || can_borrow < 0) {
            return Result.error("Invalid input");
        }
        if(bookService.getBookInfoByISBN(ISBN) != null) {
            return Result.error("Book already exists");
        }
        bookService.addBook(ISBN, book_name, publisher, author, avai_num, borrow_num, can_borrow);
        return Result.success("Add book successfully");
    }
    @PostMapping("/updatebook")
    public Result<String> UpdateBook(String ISBN, String book_name, String publisher, String author, int avai_num, int borrow_num, int can_borrow,String account) {
        if(bookService.isAdmin(account)==0){
            return Result.error("You are not an administrator");
        }
        if(ISBN == null || book_name == null || publisher == null || author == null || avai_num < 0 || borrow_num < 0 || can_borrow < 0) {
            return Result.error("Invalid input");
        }
        if(bookService.getBookInfoByISBN(ISBN) == null) {
            return Result.error("Book not found");
        }
        bookService.updateBook(ISBN, book_name, publisher, author, avai_num, borrow_num, can_borrow);
        return Result.success("Update book successfully");
    }
    @PostMapping("/deletebook")
    public Result<String> DeleteBook(String ISBN,String account) {
        if(bookService.isAdmin(account)==0){
            return Result.error("You are not an administrator");
        }
        if(bookService.getBookInfoByISBN(ISBN) == null) {
            return Result.error("Book not found");
        }
        bookService.deleteBook(ISBN);
        return Result.success("Delete book successfully");
    }
}
