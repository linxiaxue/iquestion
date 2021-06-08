package com.web.pj.mapper;

import com.web.pj.entity.Question;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.web.pj.entity.User;
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
public interface QuestionMapper extends BaseMapper<Question> {
    @Select("SELECT * FROM `question` order by createTime desc Limit #{param2}, #{param1}")
    public List<Question> getQuestionByTime(int sum, int from);

    @Select("SELECT * FROM `question` order by heat desc Limit #{param2}, #{param1}")
    public List<Question> getQuestionByHeat(int sum,int from);

    @Select("SELECT * FROM `question` where id= #{param1}")
    public Question getQuestionById(int id);

    @Select("SELECT * FROM `question` where userid=#{param3} order by createTime desc Limit #{param2}, #{param1}")
    public List<Question> getQuestionByTimeAndUserId(int sum,int from,int userId);

    @Select("SELECT * FROM `question` where userid=#{param3} order by heat desc Limit #{param2}, #{param1}")
    public List<Question> getQuestionByHeatAndUserId(int sum,int from,int userId);

    @Select("SELECT * FROM `question` where title LIKE #{param3}% order by createTime desc Limit #{param2}, #{param1}")
    public List<Question> searchQuestionByTime(int sum,int from,String str);

    @Select("SELECT * FROM `question` where title LIKE #{param3} order by heat desc Limit #{param2}, #{param1}")
    public List<Question> searchQuestionByHeat(int sum,int from,String str);

    @Select("SELECT count(*) as total FROM `question`")
    public int getTotal();

    @Select("SELECT count(*) as total FROM `question` where userid=#{param1}")
    public int getMyTotal(int userId);

}
