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
    public Result<Reader> getReaderMessage(@RequestParam String account,@RequestParam String password) {
        Reader reader = readerService.getReaderInfo(account,password);
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
}