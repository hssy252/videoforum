package com.xihu.conference.xihu.controller;

import com.xihu.conference.xihu.entity.Result;
import com.xihu.conference.xihu.utils.AliOssUtil;
import io.swagger.annotations.Api;
import java.io.IOException;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
@Api(tags = "上传" ,description = "后台上传阿里云api")
public class UploadController {

    @Autowired
    private AliOssUtil aliOssUtil;


    //TODO 未向数据库中添加记录,以及放入文件夹
    @PostMapping("/image")
    public Result<String> uploadImage(MultipartFile file,String imageName) throws IOException {
        String originalName = file.getOriginalFilename();
        String name = UUID.randomUUID().toString() + originalName.substring(originalName.lastIndexOf("."));
        String url = aliOssUtil.upload(file.getBytes(),name);
        return Result.success(url);
    }

    //TODO 未向数据库中添加记录,以及放入文件夹
    @PostMapping("/video")
    public Result<String> uploadVideo(MultipartFile file,String videoName,String cover) throws IOException {
        String originalName = file.getOriginalFilename();
        String name = UUID.randomUUID().toString() + originalName.substring(originalName.lastIndexOf("."));
        String url = aliOssUtil.upload(file.getBytes(),name);
        return Result.success(url);
    }
}
