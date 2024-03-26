package com.xihu.conference.xihu.controller;

import com.xihu.conference.xihu.entity.News;
import com.xihu.conference.xihu.result.Result;
import com.xihu.conference.xihu.service.NewsService;
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
@RequestMapping("/news")
@Api(tags = "新闻")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @ApiOperation("增加新闻")
    @PostMapping("/add")
    public Result addNews(@RequestBody News news){
        newsService.addNews(news);
        return Result.success();
    }

    @ApiOperation("显示新闻")
    @GetMapping("/show")
    public Result<List<News>> showAll(){
        return Result.success(newsService.showAll());
    }
}
