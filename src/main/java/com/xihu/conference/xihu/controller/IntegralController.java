package com.xihu.conference.xihu.controller;

import com.xihu.conference.xihu.constant.RedisConstant;
import com.xihu.conference.xihu.entity.Integral;
import com.xihu.conference.xihu.result.Result;
import com.xihu.conference.xihu.service.IntegralService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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

@Slf4j
@RestController
@RequestMapping("/integral")
@Api(tags = "积分表",description = "用户界面的积分规则表")
public class IntegralController {

    @Autowired
    private IntegralService integralService;

    @Autowired
    private RedisTemplate redisTemplate;

    @ApiOperation("增加积分获取途径")
    @PostMapping("/add")
    public Result add(@RequestBody Integral integral) {
        integralService.insertOne(integral);
        redisTemplate.delete(RedisConstant.INTEGRAL_ALL);
        return Result.success();
    }

    @ApiOperation("逻辑删除某个积分获取途径")
    @GetMapping("/delete/{id}")
    public Result deleteById(@PathVariable Long id) {
        integralService.deleteById(id);
        redisTemplate.delete(RedisConstant.INTEGRAL_ALL);
        return Result.success();
    }

    //TODO  缓存数据更新通知是否要使用消息队列？
    @ApiOperation("显示所有积分获取途径")
    @GetMapping("/showAll")
    public Result<List<Integral>> showAll() {
        List<Integral> integrals = null;
        try {
            integrals = (List<Integral>) redisTemplate.opsForValue().get(RedisConstant.INTEGRAL_ALL);
            if (integrals != null) {
                //log.info("读取缓存");
                return Result.success(integrals);
            }
        } catch (Exception e) {
            log.error("redis服务器异常" + e.getMessage());
        }
        //log.info("读取数据库");
        List<Integral> integralList = integralService.selectAll();
        try {
            redisTemplate.opsForValue().set(RedisConstant.INTEGRAL_ALL, integralList);
            //log.info("数据存入redis");
        } catch (Exception e) {
            log.error("redis服务器异常" + e.getMessage());
        }
        return Result.success(integralList);
    }

    @ApiOperation("显示某个积分获取途径")
    @GetMapping("showOne")
    public Result<Integral> showOne(@RequestParam Long id) {
        return Result.success(integralService.selectById(id));
    }

    @ApiOperation("更新某个积分获取途径")
    @PostMapping("/update")
    public Result updateOne(@RequestBody Integral integral) {
        integralService.updateById(integral);
        redisTemplate.delete(RedisConstant.INTEGRAL_ALL);
        return Result.success();
    }


}
