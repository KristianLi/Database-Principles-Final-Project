package com.finallab.biglab.controller;

import com.finallab.biglab.pojo.Borrow;
import com.finallab.biglab.pojo.Result;
import com.finallab.biglab.service.BorrowInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/borrow_info")
@CrossOrigin(origins = "http://localhost:8000")
public class BorrowInfoController {
    @Autowired
    private BorrowInfoService borrowInfoService;
    @GetMapping("/ask")
    public Result<Borrow[]> getBorrowInfo(@RequestParam String card_num) {
        Borrow[] borrows = borrowInfoService.getBorrowInfo(card_num);
        if (borrows != null) {
            return Result.success(borrows);
        } else {
            return Result.error("Borrow not found");
        }
    }
}
