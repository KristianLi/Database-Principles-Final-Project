package com.finallab.biglab.service;

import com.finallab.biglab.pojo.Borrow;
import com.finallab.biglab.pojo.Result;

public interface BorrowInfoService {
    public Borrow[] getBorrowInfo(String card_num);
}
