package com.xihu.conference.xihu.controller;

import com.xihu.conference.xihu.dto.CommentDTO;
import com.xihu.conference.xihu.result.Result;
import com.xihu.conference.xihu.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/comment")
@Api(tags = "评论相关接口")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/add")
    @ApiOperation("增加评论")
    public Result addComment(@RequestBody CommentDTO commentDTO){
        commentService.addComment(commentDTO);
        return Result.success();
    }

}
