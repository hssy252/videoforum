package com.xihu.conference.xihu.controller;

import com.xihu.conference.xihu.dto.TopicDTO;
import com.xihu.conference.xihu.result.Result;
import com.xihu.conference.xihu.service.TopicService;
import com.xihu.conference.xihu.vo.TopicVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能简述
 *
 * @author hssy
 * @version 1.0
 */
@RestController
@RequestMapping("/topic")
@Api(tags = "话题相关接口")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @PostMapping("/add")
    @ApiOperation("添加话题")
    public Result addTopic(@RequestBody TopicDTO topicDTO){
        topicService.addTopic(topicDTO);
        return Result.success();
    }

    @GetMapping
    @ApiOperation("显示所有话题")
    public Result<List<TopicVO>> showTopics(){
        return Result.success(topicService.showTopics());
    }

}
