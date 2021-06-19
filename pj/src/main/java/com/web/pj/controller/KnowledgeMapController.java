package com.web.pj.controller;


import com.web.pj.dto.requestDto.AddQuAnswerDto;
import com.web.pj.service.KnowledgeMapService;
import com.web.pj.util.Msg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/knowledgeMap")
@Api("知识图谱")
public class KnowledgeMapController {

    @Autowired
    private KnowledgeMapService knowledgeMapService;

    @PostMapping("/add")
    @ApiOperation("自定义问题答案")
    public Msg addQuAnswer(@RequestBody AddQuAnswerDto dto){
        knowledgeMapService.addQuAnswer(dto.getQuestion(),dto.getAnswer());
        return Msg.success();
    }


    @GetMapping("/search")
    @ApiOperation("根据自定义问题搜索")
    public Msg search(@RequestParam(value = "question")@ApiParam("问题") String question){
        return Msg.success().add("data",knowledgeMapService.getAnswer(question));
    }


}
