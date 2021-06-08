package com.web.pj.service;

import com.web.pj.entity.Question;
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
public interface QuestionService extends IService<Question> {
    List<Question> getQuestion(String type, Integer sum, Integer from);
    void setQuestion(String title,String content,Integer userId);
    Question getById(Integer id);
    List<Question> getQuestionByUserId(String type,Integer sum,Integer from,Integer userId);
    void addHeat(Integer id,Integer num);
    void addComment(Integer id);
    List<Question> searchQuestion(String type, Integer sum, Integer from,String str,Integer userId);

}
