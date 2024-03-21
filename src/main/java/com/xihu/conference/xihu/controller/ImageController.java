package com.xihu.conference.xihu.controller;

import com.xihu.conference.xihu.entity.Image;
import com.xihu.conference.xihu.entity.Result;
import com.xihu.conference.xihu.service.AlbumService;
import com.xihu.conference.xihu.service.ImageService;
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
@RequestMapping("/image")
@Api(tags = "图片")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private AlbumService albumService;

    @ApiOperation("添加图片")
    @PostMapping("/add")
    public Result addImage(@RequestBody Image image){
        imageService.addImage(image);
        albumService.addCount(image.getAlbumId());
        return Result.success();
    }

    @ApiOperation("根据type显示图片")
    @GetMapping("/show/type")
    public Result<List<Image>> showByType(@RequestParam Short type){
        return Result.success(imageService.selectByType(type));
    }

}
