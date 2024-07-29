package com.xihu.conference.xihu.controller;

import com.xihu.conference.xihu.dto.VideoDTO;
import com.xihu.conference.xihu.result.Result;
import com.xihu.conference.xihu.entity.Video;
import com.xihu.conference.xihu.service.VideoService;
import com.xihu.conference.xihu.vo.VideoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
@Api(tags = "视频")
@RequestMapping("/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @ApiOperation("上传视频记录")
    @PostMapping("/add")
    public Result addVideo(@RequestBody VideoDTO video) {
        videoService.addVideo(video);
        return Result.success();
    }

    @ApiOperation("显示视频")
    @GetMapping("/show")
    public Result<List<Video>> showVideo() {
        return Result.success(videoService.showVideo());
    }

    @ApiOperation("根据栏目显示视频")
    @GetMapping("/showByColumn")
    public Result<List<VideoVO>> showByColumn(@RequestParam String column){
        return Result.success(videoService.showByColumn(column));
    }

    @ApiOperation("显示栏目")
    @GetMapping("/showColumns")
    public Result<List<String>> showColumns(){
        return Result.success(videoService.showColumns());
    }
}
