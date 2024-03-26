package com.xihu.conference.xihu.controller;

import com.xihu.conference.xihu.result.Result;
import com.xihu.conference.xihu.entity.Subscription;
import com.xihu.conference.xihu.service.SubscriptionService;
import com.xihu.conference.xihu.vo.SubscriptionActivityVO;
import com.xihu.conference.xihu.vo.SubscriptionAgendaVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/subscription")
@Api(tags = "用户订阅关系",description = "用户订阅的活动或者会议")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @ApiOperation("订阅会议")
    @GetMapping("/subscribe")
    public Result subscribeAgenda(@RequestParam Long userId, @RequestParam Long matterId, @RequestParam Short type) {
        Subscription subscription = subscriptionService.checkIfExist(userId,matterId,type);
        if (subscription == null){
            subscriptionService.insertOne(userId, matterId, type);
        }
        else {
            subscriptionService.subscriptionOne(userId,matterId,type);
        }
        return Result.success();
    }


    @ApiOperation("取消订阅")
    @GetMapping("/subscribe/off")
    public Result cancelSubscription(@RequestParam Long userId, @RequestParam Long matterId, @RequestParam Short type) {
        subscriptionService.deleteOne(userId, matterId, type);
        return Result.success();
    }

    // TODO 显示的结果应该是vo
    @ApiOperation("获取订阅会议")
    @GetMapping("/showAgendas")
    public Result<List<SubscriptionAgendaVO>> showAgendas(@RequestParam Long userId) {
        return Result.success(subscriptionService.selectAllAgendas(userId));
    }

    //检查是否订阅会议
    @ApiOperation("是否订阅会议")
    @GetMapping("/check")
    public Result<String> check(@RequestParam Long userId, @RequestParam Long matterId, @RequestParam Short type) {
        Subscription subscription = subscriptionService.selectOne(userId, matterId, type);
        if (subscription == null) {
            return Result.success("未订阅");
        } else {
            return Result.success("已订阅");
        }
    }

    @ApiOperation("获取订阅活动")
    @GetMapping("/showActivities")
    public Result<List<SubscriptionActivityVO>> showActivities(@RequestParam Long userId){
        return Result.success(subscriptionService.selectAllActivities(userId));
    }

}
