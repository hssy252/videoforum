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
public class Post {

    private Long id;
    /**
     * 发帖用户id
     */
    private Long userId;
    /**
     *  帖子的文字内容
     */
    private String content;

    private Integer hot;


    /**
     * 帖子中的图片，用逗号隔开
     */
    private String images;

    private Integer commentNum;

    private Integer likeNum;

    /**
     * 是否置顶，1表示是，0表否
     */
    private Short topFlag;

    /**
     * 发帖时间
     */
    private Timestamp publishTime;

    private Timestamp createTime;

    private Timestamp updateTime;

    private Short isDelete;
}
