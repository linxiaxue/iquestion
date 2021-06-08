package com.web.pj.controller;


import com.web.pj.dto.requestDto.QuestionRequestDto;
import com.web.pj.dto.requestDto.UserRequestDto;
import com.web.pj.mapper.QuestionMapper;
import com.web.pj.mapper.SearchMapper;
import com.web.pj.service.QuestionService;
import com.web.pj.service.UserService;
import com.web.pj.util.Msg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.json.JSONObject;
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

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private SearchMapper searchMapper;



    @GetMapping("/")
    @ApiOperation("查看问题列表")
    public Msg list(@RequestParam(value = "type")@ApiParam(value = "heat/createTime") String type,@RequestParam(value = "sum")@ApiParam(value = "返回总条数") Integer sum,@RequestParam(value = "from")@ApiParam(value = "从第几条返回（初始为0）") Integer from){
        return Msg.success().add("data",questionService.getQuestion(type,sum,from));
    }

    @GetMapping("/search")
    @ApiOperation("搜索问题")
    public Msg search(@RequestParam(value = "str")@ApiParam(value = "搜索内容") String str,@RequestParam(value = "type")@ApiParam(value = "heat/createTime") String type,@RequestParam(value = "sum")@ApiParam(value = "返回总条数") Integer sum,@RequestParam(value = "from")@ApiParam(value = "从第几条返回（初始为0）") Integer from,@RequestParam(value = "id")@ApiParam(value = "用户id") Integer id){
        return Msg.success().add("data",questionService.searchQuestion(type,sum,from,str,id));


    }
    @GetMapping("/searchHistory")
    @ApiOperation("搜索问题历史")
    public Msg searchHistory(@RequestParam(value = "id")@ApiParam(value = "用户id") Integer id){
        return Msg.success().add("data",searchMapper.getSearchByUserId(id));


    }

    @PutMapping("/searchHistory")
    @ApiOperation("清除问题历史")
    public Msg deleteHistory(@RequestParam(value = "id")@ApiParam(value = "用户id") Integer id){
        searchMapper.deleteSearch(id);
        return Msg.success();


    }

    @PostMapping("/")
    @ApiOperation("发布问题")
    public Msg post(@RequestBody QuestionRequestDto question){
        questionService.setQuestion(question.getTitle(),question.getContent(),question.getUserId());


        return Msg.success();
    }
    @GetMapping("/detail")
    @ApiOperation("查看具体id号的问题")
    public Msg list(@RequestParam(value = "questionId")@ApiParam("问题id") Integer questionId){

        return Msg.success().add("data",questionMapper.getQuestionById(questionId));
    }

    @GetMapping("/myQuestion")
    @ApiOperation("查看我发布的问题列表")
    public Msg myList(@RequestParam(value = "type")@ApiParam(value = "heat/createTime") String type,@RequestParam(value = "sum")@ApiParam(value = "返回总条数") Integer sum,@RequestParam(value = "from")@ApiParam(value = "从第几条返回（初始为0）") Integer from,@RequestParam(value = "id")@ApiParam(value = "用户id") Integer id){
        return Msg.success().add("data",questionService.getQuestionByUserId(type,sum,from,id));
    }

    @GetMapping("/totalQuestion")
    @ApiOperation("查看问题列表总数")
    public Msg totalQuestion(){
        try {
            return Msg.success().add("data",questionMapper.getTotal());
        }catch (Exception e){
            return Msg.success().add("data",0);
        }

    }

    @GetMapping("/totalMyQuestion")
    @ApiOperation("查看我的问题总数")
    public Msg totalMyQuestion(@RequestParam(value = "id")@ApiParam(value = "用户ID") Integer id){
        try {
            return Msg.success().add("data",questionMapper.getMyTotal(id));
        }catch (Exception e){
            return Msg.success().add("data",0);
        }
    }




}

