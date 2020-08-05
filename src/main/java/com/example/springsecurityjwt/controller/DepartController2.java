package com.example.springsecurityjwt.controller;

import com.example.springsecurityjwt.api.AjaxResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/depart2")
public class DepartController2 {

    @GetMapping({"/", "/index"})
    public AjaxResponseBody index() {
        AjaxResponseBody responseBody = new AjaxResponseBody();

        responseBody.setStatus("1");
        responseBody.setMsg("depart2 index");

        return responseBody;
    }
}
