package com.web.pj.service;

import com.web.pj.dto.requestDto.UserRequestDto;
import com.web.pj.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lics
 * @since 2021-04-24
 */
public interface UserService extends IService<User> {

    //String login(UserRequestDto.Simple dto);
    User getUser(String name,String pwd);
    String setUser(String name,String pwd);

}
