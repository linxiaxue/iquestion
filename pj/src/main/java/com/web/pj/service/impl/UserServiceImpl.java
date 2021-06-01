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
    public String login(UserRequestDto.Simple dto){
        int id = dto.getId();
        User user = getById(id);
        if (user != null) {
            if (dto.getPwd().equals(user.getPwd())) {
                return "登录成功";
            } else {
                throw new WrongPasswordException();
            }
        }else {
            throw new UserNotFoundException();
        }
    }

}
