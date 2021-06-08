package com.web.pj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.web.pj.entity.Comment;
import com.web.pj.entity.Like;

import com.web.pj.entity.Search;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SearchMapper extends BaseMapper<Search> {
    @Select("SELECT * FROM `search` where userId= #{param1} order by createTime desc")
    public List<Search> getSearchByUserId(int userId);

    @Delete("DELETE FROM `search` where userId= #{param1} ")
    public void deleteSearch(int userId);

    @Insert("INSERT INTO `search` (userId,title,createTime) VALUES (#{param1},#{param2},#{param3})")
    public void insert(int userId,String title,String time);
}
