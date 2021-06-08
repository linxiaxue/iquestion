package com.web.pj.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.web.pj.entity.Comment;
import com.web.pj.entity.History;
import com.web.pj.entity.Like;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
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
public interface HistoryMapper extends BaseMapper<History> {
    @Select("SELECT * FROM `history` where userId= #{param1} order by createTime desc")
    public List<History> getSearchByUserId(int userId);

    @Delete("DELETE FROM `history` where userId= #{param1} ")
    public void deleteSearch(int userId);

    @Insert("INSERT INTO `history` (userId,questionId,title,createTime) VALUES (#{param1},#{param2},#{param3},#{param4})")
    public void insert(int userId,int questionId,String title,String time);
}
