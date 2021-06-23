package com.web.pj.service.impl;

import com.web.pj.entity.Question;
import com.web.pj.entity.Search;
import com.web.pj.mapper.QuestionMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.web.pj.mapper.SearchMapper;
import com.web.pj.service.QuestionService;
import com.web.pj.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

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

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private Logger logger;

    @Override
    public List<Question> getQuestion(String type,Integer sum,Integer from){
        if(type.equals("heat")) {

            //return questionMapper.getQuestionByHeat(sum,from);
            return redisGetByHeat(sum,from);
        } else {
            return questionMapper.getQuestionByTime(sum,from);
        }
    }

    private List<Question> redisGetByHeat(Integer sum,Integer from){
        List<Question> re = new LinkedList<>();
        Set<String> range = stringRedisTemplate.opsForZSet().reverseRange("heat", from, sum);
        for(String qid:range){
            Integer id = Integer.parseInt(qid);
            Question question = questionMapper.getQuestionById(id);
            re.add(question);
        }
        return re;
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
        stringRedisTemplate.opsForZSet().add("heat",question.getId()+"",0);


    }
    @Override
    public Question getById(Integer id){
        return questionMapper.getQuestionById(id);
    }

    @Override
    public List<Question> getQuestionByUserId(String type,Integer sum,Integer from,Integer userId){
        if(type.equals("heat")) {
            return questionMapper.getQuestionByHeatAndUserId(sum,from,userId);
        } else {
            return questionMapper.getQuestionByTimeAndUserId(sum,from,userId);
        }
    }

    @Override
    public void addHeat(Integer id,Integer num){
        Question question=questionMapper.getQuestionById(id);
        question.setHeat(question.getHeat()+num);
        saveOrUpdate(question);

        stringRedisTemplate.opsForZSet().incrementScore("heat",id+"",num);
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
