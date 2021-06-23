package com.web.pj.controller;


import com.web.pj.dto.requestDto.UserRequestDto;
import com.web.pj.mapper.HistoryMapper;
import com.web.pj.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.web.pj.util.Msg;
import com.web.pj.entity.User;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lics
 * @since 2021-04-24
 */
@RestController
@RequestMapping("/user")
@Api("用户接口")
public class UserController  {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public static class UserReq{
        private String name;
        private String pwd;

        public String getName() {
            return name;
        }

        public String getPwd() {
            return pwd;
        }
    }

    @Autowired
    private UserService userService;

    @Autowired
    private HistoryMapper historyMapper;



    @PostMapping("/login")
    @ApiOperation("登录")
    public Msg login(@RequestBody UserReq user){

        User u = userService.getUser(user.getName(),user.getPwd());
        if (u != null) {
            String token = UUID.randomUUID().toString().replaceAll("-", "");
            //stringRedisTemplate.opsForValue().set(token, String.valueOf(u.getId()), 3600, TimeUnit.SECONDS);//将用户的ID信息存入redis缓存，并设置一小时的过期时间
            return Msg.success().add("token",token).add("info","登录成功").add("id",u.getId());
        }else {
            //User u1=new User();
            //u1.setId(1);
            //String token = UUID.randomUUID().toString().replaceAll("-", "");
            //stringRedisTemplate.opsForValue().set(token, String.valueOf(u1.getId()), 3600, TimeUnit.SECONDS);//将用户的ID信息存入redis缓存，并设置一小时的过期时间
            //return Msg.success().add("token",token).add("info","登录成功");

            return Msg.fail().add("info", "用户名或密码错误");
        }



        //return ResponseEntity.ok(userService.login(dto));
    }

    @PostMapping("/register")
    @ApiOperation("注册")
    public Msg register(@RequestBody UserReq user ){
        String ret=userService.setUser(user.getName(),user.getPwd());
        return Msg.success().add("info",ret);
    }
    @GetMapping("/history")
    @ApiOperation("查看某人浏览历史")
    public Msg search( @RequestParam(value = "id")@ApiParam(value = "用户id") Integer id){

        return Msg.success().add("data",historyMapper.getSearchByUserId(id));
    }
    @PutMapping("/history")
    @ApiOperation("删除某人浏览历史")
    public Msg delate( @RequestParam(value = "id")@ApiParam(value = "用户id") Integer id){
        historyMapper.deleteSearch(id);
        return Msg.success();
    }
}

