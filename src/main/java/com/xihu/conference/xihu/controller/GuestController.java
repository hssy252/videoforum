package com.xihu.conference.xihu.controller;

import com.xihu.conference.xihu.entity.Guest;
import com.xihu.conference.xihu.result.Result;
import com.xihu.conference.xihu.service.GuestService;
import com.xihu.conference.xihu.vo.ExpertVO;
import com.xihu.conference.xihu.vo.GuestVO;
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
@RequestMapping("/guest")
@Api(tags = "专家相关接口")
public class GuestController {

    @Autowired
    private GuestService guestService;

    @PostMapping("/add")
    @ApiOperation("增加嘉宾或专家相关信息")
    public Result addExpert(@RequestBody Guest guest){
        guestService.addExpert(guest);
        return Result.success();
    }

    @GetMapping("/showAll")
    @ApiOperation("显示所有嘉宾及其会议")
    public Result<List<GuestVO>> showAll(){
        return Result.success(guestService.showAll());
    }

    @GetMapping("/showExperts")
    @ApiOperation("显示专家委员会成员")
    public Result<List<ExpertVO>> showExperts(){
        return Result.success(guestService.showExperts());
    }


}
