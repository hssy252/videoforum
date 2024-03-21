package com.xihu.conference.xihu.controller;

import com.xihu.conference.xihu.entity.AgendaContent;
import com.xihu.conference.xihu.entity.Result;
import com.xihu.conference.xihu.service.AgendaContentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/agendaContent")
@Api(tags = "会议内容", description = "每个会议侧边栏的文字")
public class AgendaContentController {

    @Autowired
    private AgendaContentService agendaContentService;

    @ApiOperation("增加会议内容")
    @PostMapping("/add")
    public Result insertOne(@RequestBody AgendaContent agendaContent) {
        agendaContentService.insertOne(agendaContent);
        return Result.success();
    }

    @ApiOperation("逻辑删除会议内容")
    @GetMapping("/delete/{id}")
    public Result deleteOneById(@PathVariable Long id) {
        agendaContentService.deleteById(id);
        return Result.success();
    }

    @ApiOperation("更新会议内容")
    @PostMapping("/update")
    public Result updateOneById(@RequestBody AgendaContent agendaContent) {
        agendaContentService.updateOne(agendaContent);
        return Result.success();
    }

    @ApiOperation("显示一个会议的会议内容")
    @GetMapping("/show/{id}")
    public Result<List<AgendaContent>> selectAllById(@PathVariable Long id) {
        return Result.success(agendaContentService.selectAll(id));
    }
}
