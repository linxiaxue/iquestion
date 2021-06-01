package com.web.pj.dto.requestDto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CommentRequestDto {

    @ApiModelProperty(value = "发布者id")
    private Integer userId;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "对应问题id")
    private Integer questionId;
}
