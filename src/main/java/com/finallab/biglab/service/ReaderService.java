package com.finallab.biglab.service;

import com.finallab.biglab.pojo.Reader;

public interface ReaderService {
    public Reader[] getReaderInfo();
    public Reader[] getReaderInfoByCardNum(String card_num);
    public Reader getOneReaderInfoByCardNum(String card_num);
    public Reader[] getReaderInfoByname(String name);
    public Reader[] getReaderInfoByDepartment(String title);
    public Reader[] getReaderInfoByPhone(String phone);
    public void deleteReader(String card_num);
    public int isAdmin(String account);
    public void addReader(String card_num,String name,String gender,String title,String borrow_num,String borrowed_num,String department,String phone);
    public void updateReader(String card_num,String name,String gender,String title,String borrow_num,String borrowed_num,String department,String phone);
}
