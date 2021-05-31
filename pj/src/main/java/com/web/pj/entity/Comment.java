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
@ApiModel(value="Comment对象", description="")
@TableName("comment")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("userId")
    @ApiModelProperty(value = "发布者id")
    private Integer userId;

    @ApiModelProperty(value = "内容")
    private String content;

    @TableField("createTime")
    private String createTime;

    @ApiModelProperty(value = "对应问题id")
    @TableField("questionId")
    private Integer questionId;

    @ApiModelProperty(value = "赞数")
    @TableField("likeNum")
    private Integer likeNum;


}
