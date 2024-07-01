package com.finallab.biglab.service;

import com.finallab.biglab.pojo.Borrow;
import com.finallab.biglab.pojo.Result;
import com.finallab.biglab.pojo.findBorrow;

public interface BorrowInfoService {
    public Borrow[] getBorrowInfo(String card_num);
    public Borrow[] getBorrowInfoByName(String reader_name);
    public Borrow[] getBorrowInfoByDepartment(String department);
    public Borrow[] getBorrowInfoByPhone(String phone);
    public int isAdmin(String account);
    public findBorrow[] getAllBorrowInfo();
    public findBorrow[] getAllOutDateBorrow(String date);
    public findBorrow[] getFindBorrowInfoByCardNum(String card_num);
    public findBorrow[] getFindBorrorInfoByName(String reader_name);
    public findBorrow[] getFindBorrowInfoByDepartment(String department);
    public findBorrow[] getFindBorrowInfoByPhone(String phone);
}
