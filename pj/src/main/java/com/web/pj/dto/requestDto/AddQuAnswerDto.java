package com.web.pj.dto.requestDto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AddQuAnswerDto {
    @ApiModelProperty(value = "问题")
    private String question;

    @ApiModelProperty(value = "答案")
    private String answer;
}
