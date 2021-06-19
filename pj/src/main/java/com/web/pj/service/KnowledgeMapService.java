package com.web.pj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.web.pj.entity.SelfQuAnswer;

public interface KnowledgeMapService extends IService<SelfQuAnswer> {

    void addQuAnswer(String question,String answer);

    String getAnswer(String question);

}
