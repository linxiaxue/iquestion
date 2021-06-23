package com.web.pj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.web.pj.entity.SelfQuAnswer;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public interface KnowledgeMapService extends IService<SelfQuAnswer> {

    void addQuAnswer(String question,String answer);

    String getAnswer(String question) throws IOException;

}
