package com.finallab.biglab.pojo;

import lombok.Data;

@Data
public class User {
    private int account_id;
    private String account;
    private String password;
    private String card_num;
    private int isAdmin;
}