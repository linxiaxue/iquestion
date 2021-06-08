package com.web.pj.service;

import com.web.pj.entity.Message;
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
public interface MessageService extends IService<Message> {
    void setMessage(Integer fromId,Integer toId,String content);
    List<Message> getMessage(Integer toId);
    Message getMessageDetail(Integer id);


}
