package com.web.pj.controller;


import com.web.pj.dto.requestDto.UserRequestDto;
import com.web.pj.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lics
 * @since 2021-04-24
 */
@RestController
@RequestMapping("/")
@Api("用户接口")
public class UserController  {

    @Autowired
    private UserService userService;

    @PostMapping("login")
    @ApiOperation("登录")
    public ResponseEntity<String> login(@RequestBody UserRequestDto dto){

        return ResponseEntity.ok(userService.login(dto));
    }
}

