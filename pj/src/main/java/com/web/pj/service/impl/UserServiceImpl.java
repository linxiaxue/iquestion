package com.web.pj.service.impl;

import com.web.pj.Exception.UserNotFoundException;
import com.web.pj.Exception.WrongPasswordException;
import com.web.pj.dto.requestDto.UserRequestDto;
import com.web.pj.entity.User;
import com.web.pj.mapper.UserMapper;
import com.web.pj.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lics
 * @since 2021-04-24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public User getUser(String name,String pwd) {

        User u = userMapper.getUser(name,pwd);
        return u;
    }

    @Override
    public String setUser(String name,String pwd){
        if(name==null||pwd==null){
            return "用户名或密码不能为空";
        }
        User user=userMapper.getUserByName(name);
        if (user!=null){
            return "用户名已经存在";
        }
        User u=new User();
        u.setName(name);
        u.setPwd(pwd);
        saveOrUpdate(u);
        return "注册成功";
    }

    //@Override
    //public String login(UserRequestDto.Simple dto){
        //int id = dto.getId();
        //User user = getById(id);
        //if (user != null) {
            //if (dto.getPwd().equals(user.getPwd())) {
              //  return "登录成功";
            //} else {
              //  throw new WrongPasswordException();
            //}
        //}else {
          //  throw new UserNotFoundException();
        //}
    //}

}
