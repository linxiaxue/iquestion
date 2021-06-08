package com.web.pj.service.impl;

import com.web.pj.entity.Message;
import com.web.pj.mapper.MessageMapper;
import com.web.pj.service.MessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {
    @Autowired
    private MessageMapper messageMapper;
    @Override
    public void setMessage(Integer fromId,Integer toId,String content){
        Message message=new Message();
        message.setContent(content);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=new Date();
        message.setCreateTime(sdf.format(date));
        message.setFromId(fromId);
        message.setHasRead(0);
        message.setToId(toId);
        saveOrUpdate(message);
    }
    @Override
    public List<Message> getMessage(Integer toId){

        return messageMapper.getMessageByTime(toId);
    }
    @Override
    public Message getMessageDetail(Integer id){
        Message message=messageMapper.getMessageById(id);
        message.setHasRead(1);
        saveOrUpdate(message);
        return message;
    }

}
