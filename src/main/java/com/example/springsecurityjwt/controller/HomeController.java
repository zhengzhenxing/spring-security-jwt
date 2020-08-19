package com.example.springsecurityjwt.controller;

import com.example.springsecurityjwt.api.CommonResult;
import com.example.springsecurityjwt.entity.User;
import com.example.springsecurityjwt.service.MQSenderService;
import com.example.springsecurityjwt.utils.RedisUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private MQSenderService mqSenderService;

    @GetMapping({"/", "/index"})
    public CommonResult<String> index() {
        return CommonResult.success("home index");
    }

    @PostMapping("/send-qm-msg")
    public CommonResult<String> sendQMMsg(@RequestBody String message) {
        mqSenderService.send(message);
        return CommonResult.success("home index");
    }

    @GetMapping("/user/{id}")
    public CommonResult<User> userInfo(@PathVariable Long id) {
        final String cacheKey = "user_" + id;

        User cacheUser = (User)redisUtil.get(cacheKey);
        if (cacheUser != null) {
            return CommonResult.success(cacheUser);
        }

        User user = new User();
        user.setId(id);
        user.setName("zzx10");
        user.setSex("男");

        redisUtil.set(cacheKey, user);

        return CommonResult.success(user);
    }

    @GetMapping("/user")
    public CommonResult<User> userInfo2(@RequestParam(required = false, defaultValue = "0") Long id,
                                        @RequestParam String name,
                                        @RequestParam String sex) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setSex(sex);
        return CommonResult.success(user);
    }

    @GetMapping("/usermodel")
    public CommonResult<User> userInfo3(@ModelAttribute User param) {
        User user = new User();
        user.setId(param.getId());
        user.setName(param.getName());
        user.setSex(param.getSex());
        return CommonResult.success(user);
    }

    @PostMapping("/user")
    public CommonResult<User> setUserInfo(@RequestBody User param) {
        User user = new User();
        user.setId(param.getId());
        user.setName(param.getName());
        user.setSex(param.getSex());
        return CommonResult.success(user);
    }
}
