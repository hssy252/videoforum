package com.xihu.conference.xihu.controller;

import com.xihu.conference.xihu.entity.Activity;
import com.xihu.conference.xihu.result.Result;
import com.xihu.conference.xihu.service.ActivityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("/activity")
@Api(tags = "活动展示")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @ApiOperation("增加活动")
    @PostMapping("/add")
    public Result addOne(@RequestBody Activity activity) {
        activityService.insertOne(activity);
        return Result.success();
    }

    @ApiOperation("更新活动")
    @PostMapping("/update")
    public Result updateOne(@RequestBody Activity activity) {
        activityService.updateOne(activity);
        return Result.success();
    }

    @ApiOperation("根据标签搜索活动")
    @GetMapping("/byTag")
    public Result<List<Activity>> showByTag(@RequestParam String tag) {
        return Result.success(activityService.selectByTag(tag));
    }

    @ApiOperation("展示所有活动")
    @GetMapping("/showAll")
    public Result<List<Activity>> showAll() {
        return Result.success(activityService.showAll());
    }

    @ApiOperation("逻辑删除某个活动")
    @GetMapping("/delete/{id}")
    public Result deleteById(@PathVariable Long id) {
        activityService.deleteById(id);
        return Result.success();
    }

    @ApiOperation("显示所有标签")
    @GetMapping("/showTags")
    public Result<List<String>> showTags() {
        return Result.success(activityService.showTags());
    }

}
