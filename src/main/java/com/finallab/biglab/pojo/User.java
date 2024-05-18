package com.finallab.biglab.pojo;

import lombok.Data;

@Data
public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String address;
    private String sex;
    private String img;
    private int level;
    private int year,month,day;
}
