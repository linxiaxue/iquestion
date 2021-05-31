package com.web.pj.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author lics
 * @since 2021-05-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Message对象", description="")
@TableName("message")
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "发送方id")
    @TableField("fromId")
    private Integer fromId;

    @ApiModelProperty(value = "接收方id")
    @TableField("toId")
    private Integer toId;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "0未读，1已读")
    @TableField("hasRead")
    private Integer hasRead;

    @TableField("createTime")
    private String createTime;


}
