package com.finallab.biglab.controller;

import com.finallab.biglab.pojo.Borrow;
import com.finallab.biglab.pojo.Result;
import com.finallab.biglab.pojo.findBorrow;
import com.finallab.biglab.service.BorrowInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/borrow_info")
@CrossOrigin(origins = "http://localhost:8000")
public class BorrowInfoController {
    @Autowired
    private BorrowInfoService borrowInfoService;
    @GetMapping("/all")
    public Result<findBorrow[]> getAllBorrowInfo(@RequestParam String account) {
        if (borrowInfoService.isAdmin(account) == 0) {
            return Result.error("无权限查询");
        }
        findBorrow[] borrows = borrowInfoService.getAllBorrowInfo();
        if (borrows != null) {
            return Result.success(borrows);
        } else {
            return Result.error("未查询到借阅信息");
        }
    }
    @GetMapping("/askbycardnum")
    public Result<Borrow[]> getBorrowInfo(@RequestParam String card_num) {
        Borrow[] borrows = borrowInfoService.getBorrowInfo(card_num);
        if (borrows != null) {
            return Result.success(borrows);
        } else {
            return Result.error("未查询到借阅信息");
        }
    }
    @GetMapping("/askbyname")
    public Result<Borrow[]> getBorrowInfoByName(@RequestParam String account,@RequestParam String reader_name) {
        if (borrowInfoService.isAdmin("admin") == 0) {
            return Result.error("无权限查询");
        }
        Borrow[] borrows = borrowInfoService.getBorrowInfoByName(reader_name);
        if (borrows != null) {
            return Result.success(borrows);
        } else {
            return Result.error("未查询到借阅信息");
        }
    }
    @GetMapping("/askbydepartment")
    public Result<Borrow[]> getBorrowInfoByDepartment(@RequestParam String account,@RequestParam String department) {
        if (borrowInfoService.isAdmin("admin") == 0) {
            return Result.error("无权限查询");
        }
        Borrow[] borrows = borrowInfoService.getBorrowInfoByDepartment(department);
        if (borrows != null) {
            return Result.success(borrows);
        } else {
            return Result.error("未查询到借阅信息");
        }
    }
    @GetMapping("/askbyphone")
    public Result<Borrow[]> getBorrowInfoByPhone(@RequestParam String account,@RequestParam String phone) {
        if (borrowInfoService.isAdmin("admin") == 0) {
            return Result.error("无权限查询");
        }
        Borrow[] borrows = borrowInfoService.getBorrowInfoByPhone(phone);
        if (borrows != null) {
            return Result.success(borrows);
        } else {
            return Result.error("未查询到借阅信息");
        }
    }
    @GetMapping("/outdate")
    public Result<findBorrow[]>getAllOutDateBorrow(@RequestParam String account){
        if (borrowInfoService.isAdmin(account) == 0) {
            return Result.error("无权限查询");
        }
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
        findBorrow[] borrows = borrowInfoService.getAllOutDateBorrow(formattedDate);
        if (borrows != null) {
            return Result.success(borrows);
        } else {
            return Result.error("未查询到借阅信息");
        }
    }
    @GetMapping("/askby_cardnum")
    public Result<findBorrow[]> getFindBorrowInfoByCardNum(@RequestParam String account,@RequestParam String card_num) {
        if (borrowInfoService.isAdmin(account) == 0) {
            return Result.error("无权限查询");
        }
        card_num="%"+card_num+"%";
        findBorrow[] borrows = borrowInfoService.getFindBorrowInfoByCardNum(card_num);
        if (borrows != null) {
            return Result.success(borrows);
        } else {
            return Result.error("未查询到借阅信息");
        }
    }
    @GetMapping("/askby_name")
    public Result<findBorrow[]> getFindBorrowInfoByName(@RequestParam String account,@RequestParam String name) {
        if (borrowInfoService.isAdmin(account) == 0) {
            return Result.error("无权限查询");
        }
        name="%"+name+"%";
        findBorrow[] borrows = borrowInfoService.getFindBorrorInfoByName(name);
        if (borrows != null) {
            return Result.success(borrows);
        } else {
            return Result.error("未查询到借阅信息");
        }
    }
    @GetMapping("/askby_department")
    public Result<findBorrow[]> getFindBorrowInfoByDepartment(@RequestParam String account,@RequestParam String department) {
        if (borrowInfoService.isAdmin(account) == 0) {
            return Result.error("无权限查询");
        }
        department="%"+department+"%";
        findBorrow[] borrows = borrowInfoService.getFindBorrowInfoByDepartment(department);
        if (borrows != null) {
            return Result.success(borrows);
        } else {
            return Result.error("未查询到借阅信息");
        }
    }
    @GetMapping("/askby_phone")
    public Result<findBorrow[]> getFindBorrowInfoByPhone(@RequestParam String account,@RequestParam String phone) {
        if (borrowInfoService.isAdmin(account) == 0) {
            return Result.error("无权限查询");
        }
        phone="%"+phone+"%";
        findBorrow[] borrows = borrowInfoService.getFindBorrowInfoByPhone(phone);
        if (borrows != null) {
            return Result.success(borrows);
        } else {
            return Result.error("未查询到借阅信息");
        }
    }
}
