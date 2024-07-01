package com.finallab.biglab.service.impl;

import com.finallab.biglab.mapper.BookMapper;
import com.finallab.biglab.mapper.BorrowInfoMapper;
import com.finallab.biglab.pojo.Book;
import com.finallab.biglab.pojo.Borrow;
import com.finallab.biglab.pojo.Result;
import com.finallab.biglab.pojo.findBorrow;
import com.finallab.biglab.service.BorrowInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BorrowInfoServicelmpl implements BorrowInfoService {
    @Autowired
    private BorrowInfoMapper borrowInfoMapper;
    @Override
    public Borrow[] getBorrowInfo(String card_num) {
        return borrowInfoMapper.getBorrowInfo(card_num);
    }
    @Override
    public Borrow[] getBorrowInfoByName(String reader_name) {
        return borrowInfoMapper.getBorrowInfoByName(reader_name);
    }
    @Override
    public Borrow[] getBorrowInfoByDepartment(String department) {
        return borrowInfoMapper.getBorrowInfoByDepartment(department);
    }
    @Override
    public Borrow[] getBorrowInfoByPhone(String phone) {
        return borrowInfoMapper.getBorrowInfoByPhone(phone);
    }
    @Override
    public int isAdmin(String account) {
        return borrowInfoMapper.isAdmin(account);
    }
    @Override
    public findBorrow[] getAllBorrowInfo() {
        return borrowInfoMapper.getAllBorrowInfo();
    }
    @Override
    public findBorrow[] getAllOutDateBorrow(String date){
        return borrowInfoMapper.getAllDateBorrow(date);
    }
    @Override
    public findBorrow[] getFindBorrowInfoByCardNum(String card_num){
        return borrowInfoMapper.getFindBorrowInfoByCardNum(card_num);
    }
    @Override
    public findBorrow[] getFindBorrorInfoByName(String reader_name){
        return borrowInfoMapper.getFindBorrowInfoByName(reader_name);
    }
    @Override
    public findBorrow[] getFindBorrowInfoByDepartment(String department){
        return borrowInfoMapper.getFindBorrowInfoByDepartment(department);
    }
    @Override
    public findBorrow[] getFindBorrowInfoByPhone(String phone){
        return borrowInfoMapper.getFindBorrowInfoByPhone(phone);
    }
}
