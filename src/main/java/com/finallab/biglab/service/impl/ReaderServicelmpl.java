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
    public Reader[] getReaderInfo(){
        return readerMapper.getReaderInfo();
    }
    @Override
    public void deleteReader(String card_num){
        readerMapper.deleteReader(card_num);
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
    public Reader[] getReaderInfoByCardNum(String card_num){
        return readerMapper.getReaderInfoByCardNum(card_num);
    }
    @Override
    public Reader[] getReaderInfoByname(String name){
        return readerMapper.getReaderInfoByname(name);
    }
    @Override
    public Reader[] getReaderInfoByDepartment(String title){
        return readerMapper.getReaderInfoByDepartment(title);
    }
    @Override
    public Reader[] getReaderInfoByPhone(String phone){
        return readerMapper.getReaderInfoByPhone(phone);
    }
}
