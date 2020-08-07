package com.example.springsecurityjwt.controller;

import com.example.springsecurityjwt.api.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping({"/", "/index"})
    public CommonResult<String> index() {
        return CommonResult.success("admin index");
    }
}
