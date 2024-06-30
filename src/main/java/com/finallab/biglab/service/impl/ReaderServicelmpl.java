package com.finallab.biglab.service.impl;
import com.finallab.biglab.mapper.ReaderMapper;
import com.finallab.biglab.pojo.Reader;
import com.finallab.biglab.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReaderServicelmpl implements ReaderService {
    @Autowired
    private ReaderMapper readerMapper;

    @Override
    public Reader getReaderInfo(String account,String password){
        return readerMapper.getReaderInfo(account,password);
    }
    @Override
    public void deleteReader(String card_num){
        readerMapper.deleteReader(card_num);
        return;
    }
    @Override
    public int isAdmin(String account){
        return readerMapper.isAdmin(account);
    }
    @Override
    public void addReader(String account,String card_num,String name,String gender,String title,String department,String phone){
        readerMapper.addReader(account,card_num,name,gender,title,department,phone);
    }
    @Override
    public Reader getReaderInfoByCardNum(String card_num){
        return readerMapper.getReaderInfoByCardNum(card_num);
    }
}
