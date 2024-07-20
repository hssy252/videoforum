package com.xihu.conference.xihu.controller;

import com.xihu.conference.xihu.entity.Image;
import com.xihu.conference.xihu.entity.Video;
import com.xihu.conference.xihu.result.Result;
import com.xihu.conference.xihu.service.ImageService;
import com.xihu.conference.xihu.service.VideoService;
import com.xihu.conference.xihu.utils.AliOssUtil;
import io.swagger.annotations.Api;
import java.io.IOException;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 功能简述
 *
 * @author hssy
 * @version 1.0
 */
@RestController
@RequestMapping("/upload")
@Api(tags = "上传", description = "后台上传阿里云api")
public class UploadController {

    @Autowired
    private AliOssUtil aliOssUtil;

    @Autowired
    private ImageService imageService;

    @Autowired
    private VideoService videoService;


    @PostMapping("/image")
    public Result<String> uploadImage(MultipartFile file, String imageName, String fileDirectory, Short type,
        @RequestParam(required = false, defaultValue = "0") Long albumId,
        @RequestParam(required = false, defaultValue = "") String columnName) throws IOException {
        String originalName = file.getOriginalFilename();
        String name = UUID.randomUUID() + originalName.substring(originalName.lastIndexOf("."));
        name = fileDirectory + "/" + name;
        String url = aliOssUtil.upload(file.getBytes(), name);
        Image image = Image.builder()
            .name(imageName)
            .albumId(albumId)
            .columnType(columnName)
            .type(type)
            .url(url)
            .build();
        imageService.addImage(image);
        return Result.success(url);
    }


    @PostMapping("/video")
    public Result<String> uploadVideo(MultipartFile file, String videoName, String cover, String fileDirectory,Short type,
        @RequestParam(required = false, defaultValue = "0") Long albumId,
        @RequestParam(required = false, defaultValue = "") String columnName) throws IOException {
        String originalName = file.getOriginalFilename();
        String name = UUID.randomUUID() + originalName.substring(originalName.lastIndexOf("."));
        name = fileDirectory + "/" + name;
        String url = aliOssUtil.upload(file.getBytes(), name);
        Video video = Video.builder()
            .name(videoName)
            .path(url)
            .albumId(albumId)
            .columnType(columnName)
            .type(type)
            .cover(cover)
            .build();
        videoService.addVideo(video);
        return Result.success(url);
    }
}
