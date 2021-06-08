package com.web.pj.controller;


import com.baomidou.mybatisplus.annotation.TableField;
import com.web.pj.dto.requestDto.CommentRequestDto;
import com.web.pj.mapper.CommentMapper;
import com.web.pj.service.CommentService;
import com.web.pj.util.Msg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
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

    @Autowired
    private CommentMapper commentMapper;



    @PostMapping("/")
    @ApiOperation("发布回帖")
    public Msg list(@RequestBody CommentRequestDto comment){
        commentService.setComment(comment.getUserId(),comment.getContent(),comment.getQuestionId());

        return Msg.success();
    }
    @GetMapping("/")
    @ApiOperation("查看某个问题的回帖")
    public Msg list(@RequestParam(value = "qid")@ApiParam("问题id") Integer qid,@RequestParam(value = "type")@ApiParam(value = "likeNum/createTime") String type,@RequestParam(value = "sum")@ApiParam(value = "返回总条数") Integer sum,@RequestParam(value = "from")@ApiParam(value = "从第几条返回（初始为0）") Integer from,@RequestParam(value = "id")@ApiParam(value = "用户id") Integer id){

        return Msg.success().add("data",commentService.getComment(id,qid,type,sum,from));
    }

    @GetMapping("/detail")
    @ApiOperation("查看具体id号的回帖")
    public Msg list(@RequestParam(value = "commentId")@ApiParam("问题id") Integer commentId){

        return Msg.success().add("data",commentMapper.getCommentById(commentId));
    }

    @GetMapping("/myQuestion")
    @ApiOperation("查看我发布的评论列表")
    public Msg myList(@RequestParam(value = "type")@ApiParam(value = "likeNum/createTime") String type,@RequestParam(value = "sum")@ApiParam(value = "返回总条数") Integer sum,@RequestParam(value = "from")@ApiParam(value = "从第几条返回（初始为0）") Integer from,@RequestParam(value = "id")@ApiParam(value = "用户id") Integer id){
        return Msg.success().add("data",commentService.getMyComment(id,type,sum,from));
    }

    @GetMapping("/totalComment")
    @ApiOperation("查看某个问题的回帖总数")
    public Msg totalComment(@RequestParam(value = "qid")@ApiParam("问题id") Integer id){
        try{
            return Msg.success().add("data",commentMapper.getTotal(id));
        }catch (Exception e){
            return Msg.success().add("data",0);
        }

    }

    @GetMapping("/totalMyComment")
    @ApiOperation("查看我的回帖总数")
    public Msg totalMyComment(@RequestParam(value = "userId")@ApiParam("用户id") Integer id){
        try{
            return Msg.success().add("data",commentMapper.getMyTotal(id));
        }catch (Exception e){
            return Msg.success().add("data",0);
        }

    }

}

