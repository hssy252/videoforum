package com.xihu.conference.xihu.controller;

import com.xihu.conference.xihu.entity.Achievement;
import com.xihu.conference.xihu.result.Result;
import com.xihu.conference.xihu.service.AchievementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
@RequestMapping("/achievement")
@Api(tags = "成果展示")
public class AchievementController {

    @Autowired
    private AchievementService achievementService;

    @ApiOperation("增加成果")
    @PostMapping("/add")
    public Result insertOne(@RequestBody Achievement achievement) {
        achievementService.insertOne(achievement);
        return Result.success();
    }

    @ApiOperation("逻辑删除成果")
    @GetMapping("/{id}")
    public Result deleteOneById(@PathVariable Long id) {
        achievementService.deleteById(id);
        return Result.success();
    }

    @ApiOperation("更新成果")
    @PostMapping("/update")
    public Result updateOne(@RequestBody Achievement achievement) {
        achievementService.updateOne(achievement);
        return Result.success();
    }

    @ApiOperation("根据category显示成果")
    @GetMapping Result<List<Achievement>> showByTag(@RequestParam String category){
        return Result.success(achievementService.showByTag(category));
    }

    @ApiOperation("增加观看人数")
    @PutMapping("/watch/{id}/{watchNum}")
    public Result addWatchCount(@PathVariable Long id,@PathVariable Long watchNum){
        achievementService.addWatchCount(id,watchNum);
        return Result.success();
    }

    @ApiOperation("显示标签为可下载的成果")
    @GetMapping("/downloadable")
    public Result<List<Achievement>> showDownloadable() {
        return Result.success(achievementService.showDownloadable());
    }

    @ApiOperation("显示标签为申请试用的成功")
    @GetMapping("/usable")
    public Result<List<Achievement>> showUsable() {
        return Result.success(achievementService.showUsable());
    }
}
