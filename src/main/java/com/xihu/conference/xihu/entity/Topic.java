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
public class Topic {

    private Long id;

    /**
     * 标题
     */
    private String content;

    /**
     * 热度
     */
    private Integer hot;

    /**
     * 封面
     */
    private String cover;

    /**
     * 帖子数
     */
    private Integer postNum;

    /**
     * 观看数
     */
    private Integer watchNum;

    private Timestamp createTime;

    private Timestamp updateTime;

    private Short isDelete;

}
