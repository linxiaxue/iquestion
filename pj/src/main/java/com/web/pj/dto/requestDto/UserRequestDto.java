package com.web.pj.dto.requestDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("用户信息类")
public class UserRequestDto {

    @ApiModelProperty("密码")
    private String pwd;

    @ApiModelProperty("用户名")
    private String name;

    @Data
    public static class Simple{

        public Simple(){

        }

        @ApiModelProperty("id")
        private int id;

        @ApiModelProperty("密码")
        private String pwd;
    }
}
