package com.web.pj.service.impl;

import com.web.pj.entity.Comment;
import com.web.pj.entity.History;
import com.web.pj.entity.Question;
import com.web.pj.entity.User;
import com.web.pj.mapper.CommentMapper;
import com.web.pj.mapper.HistoryMapper;
import com.web.pj.mapper.QuestionMapper;
import com.web.pj.mapper.UserMapper;
import com.web.pj.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.web.pj.service.MessageService;
import com.web.pj.service.QuestionService;
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
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private MessageService messageService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private HistoryMapper historyMapper;
    @Override
    public void setComment(Integer userId,String content,Integer questionId){
        Comment comment=new Comment();
        comment.setContent(content);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=new Date();
        comment.setCreateTime(sdf.format(date));
        comment.setLikeNum(0);
        comment.setQuestionId(questionId);
        comment.setUserId(userId);
        try {
            Integer toId=questionService.getById(questionId).getUserId();
            saveOrUpdate(comment);
            User fromUser = userMapper.selectById(userId);
            Question question = questionMapper.getQuestionById(questionId);
            String string = "用户"+fromUser.getName()+"回复了你的问题("+question.getTitle()+")："+content;
            messageService.setMessage(userId,toId,string);
            questionService.addHeat(questionId,2);
            questionService.addComment(questionId);
        }catch (Exception e){

        }


    }
    @Override
    public List<Comment> getComment(Integer userId,Integer questionId, String type, Integer sum, Integer from){
        questionService.addHeat(questionId,1);
        String title=questionMapper.getQuestionById(questionId).getTitle();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=new Date();
        historyMapper.insert(userId,questionId,title,sdf.format(date));
        if(type.equals("createTime")){
           // System.out.println("ok");
            return commentMapper.getCommentByTime(sum,from,questionId);
        }

        else
            return commentMapper.getCommentByLikeNum(sum,from,questionId);

    }

    @Override
    public  List<Comment> getMyComment(Integer userId,String type,Integer sum,Integer from){
        if(type.equals("createTime"))
            return commentMapper.getCommentByTimeAndUserID(sum,from,userId);
        else
            return commentMapper.getCommentByLikeNumAndUserId(sum,from,userId);

    }
    @Override
    public void addLike(Integer id,Integer num){
        Comment comment=commentMapper.getCommentById(id);
        comment.setLikeNum(comment.getLikeNum()+num);
        saveOrUpdate(comment);
    }
}
