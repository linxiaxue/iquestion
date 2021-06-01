package com.web.pj.service.impl;

import com.web.pj.entity.Message;
import com.web.pj.mapper.MessageMapper;
import com.web.pj.service.MessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
