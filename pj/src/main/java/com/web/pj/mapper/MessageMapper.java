package com.web.pj.mapper;

import com.web.pj.entity.Comment;
import com.web.pj.entity.Message;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lics
 * @since 2021-05-31
 */
public interface MessageMapper extends BaseMapper<Message> {
    @Select("SELECT * FROM `message` where toId=#{param1} order by createTime desc")
    public List<Message> getMessageByTime(int toId);

    @Select("SELECT count(*) as total FROM `message`")
    public int getTotal();

    @Select("SELECT * FROM `message` where id=#{param1}")
    public Message getMessageById(int id);
}
