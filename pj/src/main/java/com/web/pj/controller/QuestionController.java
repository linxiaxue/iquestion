package com.web.pj.controller;


import com.web.pj.dto.requestDto.QuestionRequestDto;
import com.web.pj.dto.requestDto.UserRequestDto;
import com.web.pj.service.QuestionService;
import com.web.pj.service.UserService;
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
@RequestMapping("/question")
@Api("问题")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    @ApiOperation("查看问题列表")
    public ResponseEntity<String> list(){

        return ResponseEntity.ok("success");
    }

    @PostMapping("/post")
    @ApiOperation("发布问题")
    public ResponseEntity<String> post(@RequestBody QuestionRequestDto dto){

        return ResponseEntity.ok("success");
    }

    @GetMapping("/")
    @ApiOperation("查看某个问题的回帖")
    public ResponseEntity<String> list(@RequestParam(value = "qid")@ApiParam("问题id") int qid){

        return ResponseEntity.ok("success");
    }
}

