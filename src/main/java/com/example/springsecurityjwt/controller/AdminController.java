package com.example.springsecurityjwt.controller;

import com.example.springsecurityjwt.api.CommonResult;
import com.example.springsecurityjwt.dao.UserMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Api(tags = "管理员相关接口")
@RequestMapping("/admin")
public class AdminController {

    @Resource
    UserMapper userMapper;

    @GetMapping({"/", "/index"})
    @ApiOperation("管理员首页信息")
    public CommonResult<String> index() {
        return CommonResult.success("admin index");
    }

    @GetMapping("userInfoById")
    @ApiOperation("通过ID获取用户信息")
    public CommonResult<UserDetails> userInfoById(@RequestParam(value = "id") Long id) {
        UserDetails userDetail = userMapper.selectByPrimaryKey(id);
        if (userDetail == null) {
            return CommonResult.failed("找不到用户");
        }
        return CommonResult.success(userDetail);
    }

    @GetMapping("userInfoByName")
    @ApiOperation("通过名字获取用户信息")
    @ApiImplicitParam(name = "name", value = "用户名称", defaultValue = "user1", required = true)
    public CommonResult<UserDetails> userInfoByName(@RequestParam(value = "name") String name) {
        UserDetails userDetail = userMapper.selectByUsername(name);
        if (userDetail == null) {
            return CommonResult.failed("找不到用户");
        }
        return CommonResult.success(userDetail);
    }
}
