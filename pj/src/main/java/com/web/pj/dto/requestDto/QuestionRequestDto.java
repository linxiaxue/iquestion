package com.web.pj.dto.requestDto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class QuestionRequestDto {
    @ApiModelProperty(value = "问题")
    private String title;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "发布者id")
    private Integer userId;
}
