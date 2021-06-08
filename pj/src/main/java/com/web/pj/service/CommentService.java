package com.web.pj.service;

import com.web.pj.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lics
 * @since 2021-05-31
 */
public interface CommentService extends IService<Comment> {
    void setComment(Integer userId,String content,Integer questionId);
    List<Comment> getComment(Integer userId,Integer questionId, String type, Integer sum, Integer from);
    List<Comment> getMyComment(Integer userId,String type,Integer sum,Integer from);
    void addLike(Integer id,Integer num);
}
