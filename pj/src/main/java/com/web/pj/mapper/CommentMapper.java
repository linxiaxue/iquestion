package com.web.pj.mapper;

import com.web.pj.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.web.pj.entity.Question;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lics
 * @since 2021-05-31
 */
public interface CommentMapper extends BaseMapper<Comment> {
    @Select("SELECT * FROM `comment` where questionId=#{param3} order by createTime desc Limit #{param2}, #{param1}")
    public List<Comment> getCommentByTime(int sum, int from, int questionId);

    @Select("SELECT * FROM `comment` where questionId=#{param3} order by likeNum desc Limit #{param2}, #{param1}")
    public List<Comment> getCommentByLikeNum(int sum,int from,int questionId);

    @Select("SELECT * FROM `comment` where userId=#{param3} order by createTime desc Limit #{param2}, #{param1}")
    public List<Comment> getCommentByTimeAndUserID(int sum, int from,int userId);

    @Select("SELECT * FROM `comment` where userId=#{param3} order by likeNum desc Limit #{param2}, #{param1}")
    public List<Comment> getCommentByLikeNumAndUserId(int sum,int from,int userId);

    @Select("SELECT * FROM `comment` where id=#{param1}")
    public Comment getCommentById(int id);

    @Select("SELECT count(*) as total FROM `comment` where questionId=#{param1}")
    public int getTotal(int id);

    @Select("SELECT count(*) as total FROM `comment` where userId=#{param1}")
    public int getMyTotal(int id);
}
