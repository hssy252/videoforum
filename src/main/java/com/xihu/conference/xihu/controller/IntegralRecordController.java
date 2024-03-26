package com.xihu.conference.xihu.controller;

import com.xihu.conference.xihu.entity.IntegralRecord;
import com.xihu.conference.xihu.result.Result;
import com.xihu.conference.xihu.service.IntegralRecordService;
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
@RequestMapping("/integralRecord")
@Api(tags = "积分记录",description = "用户积分变化的记录")
public class IntegralRecordController {

    @Autowired
    private IntegralRecordService integralRecordService;

    @ApiOperation("增加记录")
    @PostMapping("/add")
    public Result add(@RequestBody IntegralRecord integralRecord) {
        integralRecordService.insertOne(integralRecord);
        return Result.success();
    }

    @ApiOperation("逻辑删除某条记录")
    @GetMapping("/delete/{id}")
    public Result deleteById(@PathVariable Long id) {
        integralRecordService.deleteById(id);
        return Result.success();
    }

    @ApiOperation("获取某个用户的积分获取记录")
    @GetMapping("/show")
    public Result<List<IntegralRecord>> showAllOfUser(@RequestParam Long userId) {
        return Result.success(integralRecordService.selectAll(userId));
    }

    @ApiOperation("获取一条积分记录")
    @GetMapping("/show/{id}")
    public Result<IntegralRecord> showOne(@PathVariable Long id) {
        return Result.success(integralRecordService.selectById(id));
    }

    @ApiOperation("更新积分记录")
    @PostMapping("/update")
    public Result updateOne(@RequestBody IntegralRecord integralRecord){
        integralRecordService.updateById(integralRecord);
        return Result.success();
    }

}
