package com.finallab.biglab.controller;

import com.finallab.biglab.pojo.Reader;
import com.finallab.biglab.pojo.Result;
import com.finallab.biglab.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reader")
@CrossOrigin(origins = "http://localhost:8000")
public class ReaderController {
    @Autowired
    private ReaderService readerService;
    @GetMapping("/info")
    public Result<Reader> getUserInfo(String account) {

    }
}
