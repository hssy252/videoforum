package com.xihu.conference.xihu.controller;

import com.xihu.conference.xihu.dto.LikedDTO;
import com.xihu.conference.xihu.result.Result;
import com.xihu.conference.xihu.service.PraiseService;
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
@RequestMapping("/praise")
public class PraiseController {

    @Autowired
    private PraiseService praiseService;


    @PostMapping("/liked")
    @ApiOperation("查询用户是否点赞过该评论或帖子，包括已经取消点赞")
    public Result<Boolean> queryLiked(@RequestBody LikedDTO likedDTO){
        return Result.success(praiseService.queryLiked(likedDTO));
    }

    /**
     *
     * @param likedId 被点赞的帖子或者评论的id
     * @param type 1表示帖子，2表示评论
     * @return
     */
    @GetMapping("/postLike")
    @ApiOperation("根据帖子id查找点赞用户号id")
    public Result<List<Long>> showUserIds(@RequestParam Long likedId,@RequestParam Short type){
        return Result.success(praiseService.showUserIds(likedId,type));
    }

    /**
     *
     * @param userId
     * @param type 1表示帖子，2表示评论
     * @return
     */
    @GetMapping("/userLike")
    @ApiOperation("根据用户id查找点赞的评论或帖子的id")
    public Result<List<Long>> showPostIds(@RequestParam Long userId,@RequestParam Short type){
        return Result.success(praiseService.showPostIds(userId,type));
    }

    @PostMapping("/like")
    @ApiOperation("点赞")
    public Result like(@RequestBody LikedDTO likedDTO){

        praiseService.like(likedDTO);
        return Result.success();
    }

    @PostMapping("/dislike")
    @ApiOperation("点赞")
    public Result dislike(@RequestBody LikedDTO likedDTO){

        praiseService.disLike(likedDTO);
        return Result.success();
    }
}
