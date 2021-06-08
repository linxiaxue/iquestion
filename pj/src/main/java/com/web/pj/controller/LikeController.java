package com.web.pj.controller;
import com.baomidou.mybatisplus.annotation.TableField;
import com.web.pj.dto.requestDto.CommentRequestDto;
import com.web.pj.mapper.CommentMapper;
import com.web.pj.service.CommentService;
import com.web.pj.service.LikeService;
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
@RequestMapping("/like")
@Api("点赞")
public class LikeController {
    @Autowired
    private LikeService likeService;

    @PostMapping("/")
    @ApiOperation("点赞或取消点赞")
    public Msg like(@RequestParam(value = "userId")@ApiParam("用户id") Integer userId,@RequestParam(value = "commentId")@ApiParam("评论id") Integer commentId){
        return Msg.success().add("data",likeService.giveALike(userId,commentId));
    }
    @GetMapping("/whether")
    @ApiOperation("查看某个评论是否点过赞false为没有点赞，true为已经点过赞")
    public Msg whether(@RequestParam(value = "userId")@ApiParam("用户id") Integer userId,@RequestParam(value = "commentId")@ApiParam("评论id") Integer commentId){

        return Msg.success().add("data",likeService.haveGiveALike(userId,commentId));
    }
    @GetMapping("/totalLikesOfAUser")
    @ApiOperation("查看某人的所有赞")
    public Msg total(@RequestParam(value = "userId")@ApiParam("用户id") Integer userId){

        return Msg.success().add("data",likeService.getLikeOfUser(userId));
    }

}

