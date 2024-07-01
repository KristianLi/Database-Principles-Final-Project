package com.finallab.biglab.controller;
import com.finallab.biglab.pojo.Reader;
import com.finallab.biglab.pojo.Result;
import com.finallab.biglab.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8000")
@RequestMapping("/reader")
public class ReaderController {
    @Autowired
    private ReaderService readerService;

    @GetMapping("/info")
    public Result<Reader[]> getReaderMessage() {
        Reader[] reader = readerService.getReaderInfo();
        if (reader != null) {
            return Result.success(reader);
        } else {
            return Result.error("Reader not found");
        }
    }
    @PostMapping("/delete")
    public Result deleteReader(@RequestParam String card_num, @RequestParam String account) {
        if(readerService.isAdmin(account) == 0) {
            return Result.error("无权限删除用户");
        }
        readerService.deleteReader(card_num);
        return Result.success();
    }
    @PostMapping("/add")
    public Result addReader(@RequestParam String account,@RequestParam String card_num,@RequestParam String name,@RequestParam String gender,@RequestParam String title,@RequestParam String department,@RequestParam String phone) {
        if (readerService.isAdmin(account) == 0) {
            return Result.error("无权限添加用户");
        }
        if(readerService.getReaderInfoByCardNum(card_num) != null) {
            return Result.error("该用户已存在");
        }
        readerService.addReader(account,card_num,name,gender,title,department,phone);
        return Result.success();
    }
    @GetMapping("/card_num")
    public Result<Reader[]> getReaderMessage1(@RequestParam String card_num) {
        card_num='%' + card_num + '%';
        Reader[] readers = readerService.getReaderInfoByCardNum(card_num);
        if (readers != null) {
            return Result.success(readers);
        } else {
            return Result.error("未找到用户");
        }
    }
    @GetMapping("/name")
    public Result<Reader[]> getReaderMessage2(@RequestParam String name) {
        name='%' + name + '%';
        Reader[] readers = readerService.getReaderInfoByname(name);
        if (readers != null) {
            return Result.success(readers);
        } else {
            return Result.error("未找到用户");
        }
    }
    @GetMapping("/department")
    public Result<Reader[]> getReaderMessage3(@RequestParam String department) {
        department='%' + department + '%';
        Reader[] readers = readerService.getReaderInfoByDepartment(department);
        if (readers != null) {
            return Result.success(readers);
        } else {
            return Result.error("未找到用户");
        }
    }
    @GetMapping("/phone")
    public Result<Reader[]> getReaderMessage4(@RequestParam String phone) {
        phone='%' + phone + '%';
        Reader[] readers = readerService.getReaderInfoByPhone(phone);
        if (readers != null) {
            return Result.success(readers);
        } else {
            return Result.error("未找到用户");
        }
    }
}