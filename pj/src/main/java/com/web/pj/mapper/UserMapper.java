package com.web.pj.mapper;

import com.web.pj.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.mapstruct.Mapper;
//import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lics
 * @since 2021-04-24
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT * FROM `user` WHERE name=#{param1} AND pwd=#{param2}")
    public User getUser(String name,String pwd);

    @Select("SELECT * FROM `user` WHERE name=#{param1}")
    public User getUserByName(String name);


}
