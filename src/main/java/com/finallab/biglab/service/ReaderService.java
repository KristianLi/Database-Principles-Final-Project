package com.finallab.biglab.service;

import com.finallab.biglab.pojo.Reader;

public interface ReaderService {
    public Reader getReaderInfo(String account,String password);
    public void deleteReader(String card_num);
    public int isAdmin(String account);
    public void addReader(String account,String card_num,String name,String gender,String title,String department,String phone);
    public Reader getReaderInfoByCardNum(String card_num);
}
