package com.finallab.biglab.service;

import com.finallab.biglab.pojo.Reader;

public interface ReaderService {
    public Reader getReaderInfo(String account,String password);
    public void deleteReader(String card_num);
    public int isAdmin(String account);
}
