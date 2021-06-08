package com.web.pj.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.web.pj.entity.Comment;
import com.web.pj.entity.Like;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface LikeMapper extends BaseMapper<Like>{
    @Select("SELECT * FROM `like` where userId= #{param1} order by createTime desc")
    public List<Like> getLikeByUserId(int userId);

    @Select("SELECT * FROM `like` where userId= #{param1} AND commentId=#{param2}")
    public Like getLikeByUserIdAndQuestionId(int userId,int commentId);

    @Delete("DELETE FROM `like` where userId= #{param1} AND commentId=#{param2}")
    public void deleteLike(int userId,int commentId);

    @Insert("INSERT INTO `like` (userId,commentId,createTime) VALUES (#{param1},#{param2},#{param3})")
    public void insert(int userId,int commentId,String time);


}
