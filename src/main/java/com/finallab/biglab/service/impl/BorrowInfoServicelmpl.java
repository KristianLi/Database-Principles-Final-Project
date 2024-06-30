package com.finallab.biglab.service.impl;

import com.finallab.biglab.mapper.BookMapper;
import com.finallab.biglab.mapper.BorrowInfoMapper;
import com.finallab.biglab.pojo.Book;
import com.finallab.biglab.pojo.Borrow;
import com.finallab.biglab.pojo.Result;
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
}
