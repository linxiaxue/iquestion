package com.web.pj.service.impl;

import com.web.pj.entity.Comment;
import com.web.pj.mapper.CommentMapper;
import com.web.pj.service.CommentService;
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
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
