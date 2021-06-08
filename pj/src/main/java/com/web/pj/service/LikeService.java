package com.web.pj.service;
import com.baomidou.mybatisplus.extension.service.IService;

import com.web.pj.entity.Like;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lics
 * @since 2021-05-31
 */
public interface LikeService extends IService<Like>{
    List<Like> getLikeOfUser(Integer userId);
    boolean haveGiveALike(Integer userId,Integer commentId);
    String giveALike(Integer userId,Integer commentId);
}
