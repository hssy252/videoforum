package com.xihu.conference.xihu.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Video {

    private Long id;

    private String name;

    private String columnType;

    /**
     * 封面的url
     */
    private String cover;

    private String path;

    /**
     * 对应集合的id
     */
    private Long albumId;

    /**
     * 0代表会议，1表示普通视频
     */
    private Short type;

    private Integer watchCount;

    private Integer likeNum;

    /**
     * 0表示没有可下载资源
     */
    private Long resourceId;

    @JsonIgnore
    private Timestamp updateTime;

    @JsonIgnore
    private Timestamp createTime;

    @JsonIgnore
    private Short isDelete;

}
