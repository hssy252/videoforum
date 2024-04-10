package com.xihu.conference.xihu.entity;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 功能简述
 *
 * @author hssy
 * @version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    private Long id;

    private Long userId;

    private Long postId;

    private String content;

    private String ip;

    private Integer likeCount;
    /**
     * 父评论id，为0则表示没有父评论
     */
    private Long parentId;

    private Timestamp createTime;

    private Timestamp updateTime;

    private Short isDelete;
}
