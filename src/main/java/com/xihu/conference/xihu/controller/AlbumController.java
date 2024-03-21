package com.xihu.conference.xihu.controller;

import com.xihu.conference.xihu.entity.Album;
import com.xihu.conference.xihu.entity.Result;
import com.xihu.conference.xihu.service.AlbumService;
import com.xihu.conference.xihu.vo.ImageAlbumVO;
import com.xihu.conference.xihu.vo.VideoAlbumVO;
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
@RequestMapping("/album")
@Api(tags = "图片、视频合集")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @ApiOperation("增加集合")
    @PostMapping("/add")
    public Result addAlbum(@RequestBody Album album){
        albumService.addAlbum(album);
        return Result.success();
    }

    @ApiOperation("显示所有图片集合")
    @GetMapping("/show/image")
    public Result<List<ImageAlbumVO>> showImageAlbum(){
        return Result.success(albumService.showImageAlbum());
    }

    @ApiOperation("显示所有视频集合")
    @GetMapping("/show/video")
    public Result<List<VideoAlbumVO>> showVideoAlbum(){
        return Result.success(albumService.showVideoAlbum());
    }

    @ApiOperation("显示合集的简略信息")
    @GetMapping("/show/type")
    public Result<List<Album>> showByType(@RequestParam Short type){
        return Result.success(albumService.showByType(type));
    }
}
