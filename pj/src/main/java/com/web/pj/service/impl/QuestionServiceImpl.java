package com.web.pj.service.impl;

import com.web.pj.entity.Question;
import com.web.pj.entity.Search;
import com.web.pj.mapper.QuestionMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.web.pj.mapper.SearchMapper;
import com.web.pj.service.QuestionService;
import com.web.pj.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lics
 * @since 2021-05-31
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements QuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private SearchMapper searchMapper;
    @Override
    public List<Question> getQuestion(String type,Integer sum,Integer from){
        if(type.equals("heat"))
            return questionMapper.getQuestionByHeat(sum,from);
        else
            return questionMapper.getQuestionByTime(sum,from);
    }

    @Override
    public void setQuestion(String title,String content,Integer userId){
        Question question=new Question();
        question.setTitle(title);
        question.setContent(content);
        question.setUserId(userId);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=new Date();
        question.setCreateTime(sdf.format(date));
        question.setCommentCount(0);
        question.setHeat(0);
        saveOrUpdate(question);


    }
    @Override
    public Question getById(Integer id){
        return questionMapper.getQuestionById(id);
    }

    @Override
    public List<Question> getQuestionByUserId(String type,Integer sum,Integer from,Integer userId){
        if(type.equals("heat"))
            return questionMapper.getQuestionByHeatAndUserId(sum,from,userId);
        else
            return questionMapper.getQuestionByTimeAndUserId(sum,from,userId);
    }

    @Override
    public void addHeat(Integer id,Integer num){
        Question question=questionMapper.getQuestionById(id);
        question.setHeat(question.getHeat()+num);
        saveOrUpdate(question);
    }
    @Override
    public void addComment(Integer id){
        Question question=questionMapper.getQuestionById(id);
        question.setCommentCount(question.getCommentCount()+1);
        saveOrUpdate(question);

    }

    @Override
    public List<Question> searchQuestion(String type, Integer sum, Integer from,String str,Integer userId){

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=new Date();
        searchMapper.insert(userId,str,sdf.format(date));
        if(type.equals("heat")){
            return questionMapper.searchQuestionByHeat(sum,from,"%"+str+"%");
        }
        else return questionMapper.searchQuestionByTime(sum,from,"%"+str+"%");
    }

}
