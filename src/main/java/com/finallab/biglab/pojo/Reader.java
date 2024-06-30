package com.finallab.biglab.pojo;
import lombok.Data;
@Data
public class Reader {
    private String card_num;
    private String name;
    private String gender;
    private String  title;
    int borrow_num;
    int borrowed_num;
    private String department;
    private String phone;
}
