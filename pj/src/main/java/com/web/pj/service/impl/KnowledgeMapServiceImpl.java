package com.web.pj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.web.pj.entity.Like;
import com.web.pj.entity.SelfQuAnswer;
import com.web.pj.mapper.LikeMapper;
import com.web.pj.mapper.SelfQuAnswerMapper;
import com.web.pj.service.KnowledgeMapService;
import com.web.pj.service.LikeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KnowledgeMapServiceImpl extends ServiceImpl<SelfQuAnswerMapper, SelfQuAnswer> implements KnowledgeMapService {
    @Override
    public void addQuAnswer(String question, String answer) {
        SelfQuAnswer selfQuAnswer = new SelfQuAnswer();
        selfQuAnswer.setQuestion(question);
        selfQuAnswer.setAnswer(answer);
        save(selfQuAnswer);
    }

    @Override
    public String getAnswer(String question) {
        LambdaQueryWrapper<SelfQuAnswer> quAnswerQueryWrapper = new QueryWrapper<SelfQuAnswer>().lambda();
        quAnswerQueryWrapper.eq(SelfQuAnswer::getQuestion,question);
        List<SelfQuAnswer> selfQuAnswer = getBaseMapper().selectList(quAnswerQueryWrapper);
        if (selfQuAnswer.size() != 0){
            return selfQuAnswer.get(0).getAnswer();
        }else {
            return "";
        }

    }
}
