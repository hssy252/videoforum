package com.xihu.conference.xihu.controller;

import com.xihu.conference.xihu.entity.Agenda;
import com.xihu.conference.xihu.entity.Result;
import com.xihu.conference.xihu.service.AgendaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能简述
 *
 * @author hssy
 * @version 1.0
 */
@RestController
@RequestMapping("/agenda")
@Api(tags = "大会")
public class AgendaController {

    @Autowired
    private AgendaService agendaService;

    @ApiOperation("增加大会")
    @PostMapping("/add")
    public Result insertOne(@RequestBody Agenda agenda) {
        agendaService.insertOne(agenda);
        return Result.success();
    }

    @ApiOperation("逻辑删除某个大会")
    @GetMapping("/delete/{id}")
    public Result deleteById(@PathVariable Long id) {
        agendaService.deleteOneById(id);
        return Result.success();
    }

    @ApiOperation("将会议的状态改成直播中")
    @GetMapping("/live/{id}")
    public Result changeStatus2Live(@PathVariable Long id) {
        agendaService.changeStatus2Live(id);
        return Result.success();
    }

    @ApiOperation("将会议的状态改成回放")
    @GetMapping("/playback/{id}")
    public Result changeStatus2Playback(@PathVariable Long id) {
        agendaService.changeStatus2playback(id);
        return Result.success();
    }

    @ApiOperation("通过id更新大会")
    @PostMapping("/update")
    public Result updateOneById(@RequestBody Agenda agenda) {
        agendaService.updateOneById(agenda);
        return Result.success();
    }

    @ApiOperation("通过id搜索大会")
    @GetMapping("/{id}")
    public Result<Agenda> selectOneById(@PathVariable Long id) {
        return Result.success(agendaService.selectById(id));
    }

    @ApiOperation("通过名字搜索大会")
    @GetMapping("/search")
    public Result<List<Agenda>> selectByName(@RequestParam String name) {
        return Result.success(agendaService.selectByName(name));
    }

    @ApiOperation("根据标签显示会议")
    @GetMapping("/show/tag")
    public Result<List<Agenda>> selectByTag(@RequestParam String tag) {
        return Result.success(agendaService.selectByTag(tag));
    }

    @ApiOperation("根据日期显示会议")
    @GetMapping("/show/date")
    public Result<List<Agenda>> selectByDate(@RequestParam String dateString) {
        return Result.success(agendaService.selectByDate(dateString));
    }

    @ApiOperation("显示所有大会")
    @GetMapping("/show/all")
    public Result<List<Agenda>> showAll() {
        return Result.success(agendaService.selectAll());
    }

    @ApiOperation("显示所有标签")
    @GetMapping("/tags")
    public Result<List<String>> showTags() {
        return Result.success(agendaService.showTags());
    }

    @ApiOperation("显示所有日期")
    @GetMapping("/dates")
    public Result<List<String>> showDates() {
        return Result.success(agendaService.showDates());
    }

    @ApiOperation("点赞会议")
    @GetMapping("/like")
    public Result<Integer> createLike(@RequestParam Long id) {
        //根据会议id实现点赞的增加,未完成
        return Result.success();
    }
}
