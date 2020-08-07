package com.example.springsecurityjwt.controller;

import com.example.springsecurityjwt.api.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/depart2")
public class DepartController2 {

    @GetMapping({"/", "/index"})
    public CommonResult<String> index() {
        return CommonResult.success("depart2 index");
    }
}
