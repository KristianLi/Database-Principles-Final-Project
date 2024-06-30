package com.finallab.biglab.pojo;
import lombok.Data;

@Data
public class Book {
    private String ISBN;
    private String book_name;
    private String publisher;
    private String author;
    private int avai_num;
    private int borrow_num;
    private int can_borrow;
}
