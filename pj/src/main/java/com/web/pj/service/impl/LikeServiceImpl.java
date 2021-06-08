package com.web.pj.service.impl;

import com.web.pj.entity.Comment;
import com.web.pj.entity.Like;
import com.web.pj.mapper.CommentMapper;
import com.web.pj.mapper.LikeMapper;
import com.web.pj.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.web.pj.service.LikeService;
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
public class LikeServiceImpl extends ServiceImpl<LikeMapper,Like> implements LikeService{
    @Autowired
    LikeMapper likeMapper;
    @Autowired
    CommentService commentService;
    @Override
    public List<Like> getLikeOfUser(Integer userId){
        return likeMapper.getLikeByUserId(userId);
    }
    @Override
    public boolean haveGiveALike(Integer userId,Integer commentId){
        Like like=likeMapper.getLikeByUserIdAndQuestionId(userId,commentId);
        if(like==null){
            return false;
        }
        else return true;
    }
    @Override
    public String giveALike(Integer userId,Integer commentId){
        Like like=likeMapper.getLikeByUserIdAndQuestionId(userId,commentId);
        if(like==null){
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date=new Date();

            likeMapper.insert(userId,commentId,sdf.format(date));
            commentService.addLike(commentId,1);
            return "点赞成功";
        }
        else {
            likeMapper.deleteLike(userId,commentId);
            commentService.addLike(commentId,-1);
            return "取消点赞";


        }
    }

}
