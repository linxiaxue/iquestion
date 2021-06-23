package com.web.pj.dto;

import lombok.Data;

@Data
public class HttpResponseDto {
    String message;
    Data data;


    @lombok.Data
    public class Data{
        String type;
        Info info;

        @lombok.Data
        public class Info{
            String text;
        }
    }
}
