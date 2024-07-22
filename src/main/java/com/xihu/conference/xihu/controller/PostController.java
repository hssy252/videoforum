package com.xihu.conference.xihu.controller;

import com.github.pagehelper.Page;
import com.xihu.conference.xihu.dto.PostDTO;
import com.xihu.conference.xihu.result.PageResult;
import com.xihu.conference.xihu.result.Result;
import com.xihu.conference.xihu.service.PostService;
import com.xihu.conference.xihu.vo.PostVO;
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
@Api(tags = "帖子相关接口")
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/add")
    @ApiOperation("添加帖子")
    public Result addPost(@RequestBody PostDTO postDTO) {
        postService.addPost(postDTO);
        return Result.success();
    }

    @PostMapping("/delete")
    public Result deletePost(Long postId){
        postService.deletePost(postId);
        return Result.success();
    }

    @GetMapping("/showByTopic")
    @ApiOperation("根据话题显示帖子")
    public Result<List<PostVO>> showByTopic(@RequestParam Long topicId) {
        return Result.success(postService.showByTopic(topicId));
    }

    @GetMapping("/showAll")
    @ApiOperation("假分页显示所有帖子")
    public PageResult pageQueryAll(){
        return postService.pageQuery();
    }

    @GetMapping("/show")
    @ApiOperation("简单显示帖子,不显示评论")
    public PageResult simplePageQuery(@RequestParam int pageNum,@RequestParam int pageSize){
        return postService.simplePageQuery(pageNum,pageSize);
    }

}
