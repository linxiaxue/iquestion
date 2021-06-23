package com.web.pj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.web.pj.dto.HttpResponseDto;
import com.web.pj.entity.Like;
import com.web.pj.entity.SelfQuAnswer;
import com.web.pj.mapper.LikeMapper;
import com.web.pj.mapper.SelfQuAnswerMapper;
import com.web.pj.service.KnowledgeMapService;
import com.web.pj.service.LikeService;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@Service
public class KnowledgeMapServiceImpl extends ServiceImpl<SelfQuAnswerMapper, SelfQuAnswer> implements KnowledgeMapService {

    @Autowired
    private ObjectMapper mapper;

    @Override
    public void addQuAnswer(String question, String answer) {
        SelfQuAnswer selfQuAnswer = new SelfQuAnswer();
        selfQuAnswer.setQuestion(question);
        selfQuAnswer.setAnswer(answer);
        save(selfQuAnswer);
    }

    @Override
    public String getAnswer(String question) throws IOException {


        LambdaQueryWrapper<SelfQuAnswer> quAnswerQueryWrapper = new QueryWrapper<SelfQuAnswer>().lambda();
        quAnswerQueryWrapper.eq(SelfQuAnswer::getQuestion,question);
        List<SelfQuAnswer> selfQuAnswer = getBaseMapper().selectList(quAnswerQueryWrapper);
        if (selfQuAnswer.size() != 0){
            return selfQuAnswer.get(0).getAnswer();
        }else {
            String INFO = URLEncoder.encode(question, "utf-8");
            String requesturl = "https://api.ownthink.com/bot?appid=xiaosi&userid=test&spoken="+INFO;
            HttpGet request = new HttpGet(requesturl);
            HttpClient httpClient = new DefaultHttpClient();
            HttpResponse response = httpClient.execute(request);
            if(response.getStatusLine().getStatusCode()==200){
                String result = EntityUtils.toString(response.getEntity());
                HttpResponseDto dto = mapper.readValue(result,HttpResponseDto.class);
                System.out.println("返回结果："+dto.getData().getInfo().getText());
                if(dto.getData().getInfo().getText()!=null){
                    return dto.getData().getInfo().getText();
                }else {
                    return "";
                }
            }
        }

        return "";

    }
}
