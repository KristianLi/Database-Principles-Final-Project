package com.finallab.biglab.pojo;

import lombok.Data;

@Data
public class Borrow {
    private String ISBN;
    private String card_num;
    private String borrow_date;
    private String due_date;
    private String return_date;
    private String fine;
}
