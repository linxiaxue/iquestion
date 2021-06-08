package com.web.pj.controller;


import com.web.pj.service.MessageService;
import com.web.pj.util.Msg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lics
 * @since 2021-05-31
 */
@RestController
@RequestMapping("/message")
@Api("通知")
public class MessageController {
    @Autowired
    private MessageService messageService;
    @GetMapping("/")
    @ApiOperation("查看用户的消息")
    public Msg list(@RequestParam(value = "toid")@ApiParam("收件人id") Integer toid){
        return Msg.success().add("data",messageService.getMessage(toid));
    }
    @GetMapping("/detail")
    @ApiOperation("查看消息详情")
    public Msg detail(@RequestParam(value = "messageId")@ApiParam("messageId") Integer id){
        return Msg.success().add("data",messageService.getMessageDetail(id));
    }
}

