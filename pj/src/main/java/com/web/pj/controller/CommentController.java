package com.web.pj.controller;


import com.web.pj.dto.requestDto.CommentRequestDto;
import com.web.pj.service.CommentService;
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
@RequestMapping("/comment")
@Api("评论")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/")
    @ApiOperation("发布回帖")
    public ResponseEntity<String> list(@RequestBody CommentRequestDto dto){

        return ResponseEntity.ok("success");
    }

}

