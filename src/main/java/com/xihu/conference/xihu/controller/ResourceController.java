package com.xihu.conference.xihu.controller;

import com.xihu.conference.xihu.result.Result;
import com.xihu.conference.xihu.service.ResourceService;
import com.xihu.conference.xihu.vo.ResourceVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/resource")
@Api(tags = "资源下载相关接口")
public class ResourceController {


    @Autowired
    private ResourceService resourceService;

    @PostMapping("/download")
    @ApiOperation("下载文件")
    public Result download(Long id, String url, HttpServletResponse response) {
        resourceService.download(id, url, response);
        return Result.success();
    }

    @GetMapping("/list")
    @ApiOperation("显示资源")
    public Result<List<ResourceVO>> listResources(@RequestParam Short type) {
        return Result.success(resourceService.listResources(type));
    }

    @PostMapping("/add")
    @ApiOperation("上传资源")
    public Result upload(MultipartFile file,String cover,String description,Short type){
        resourceService.upload(file,cover,description,type);
        return Result.success();
    }

    @GetMapping("/fetch")
    @ApiOperation("根据资源id返回下载链接")
    public Result<String> fetchUrl(@RequestParam Long id){
        return Result.success(resourceService.fetchUrl(id));
    }

}
