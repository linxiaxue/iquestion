package com.web.pj.dto.requestDto;

import lombok.Data;

@Data
public class UserRequestDto {

    private String pwd;
    private String name;

    @Data
    public class Simple{
        private int id;
        private String pwd;
    }
}
