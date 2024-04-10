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
public class Praise {

    private Long id;

    private Long userId;

    private Long likedId;

    /**
     * 1表示对帖子的点赞，2表示对评论的点赞
     */
    private Short type;

    private Timestamp createTime;

    private Timestamp updateTime;

    private Short isDelete;

}
