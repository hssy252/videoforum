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
public class Image {

    private Long id;

    private String name;

    private String columnType;
    /**
     * 0代表视频封面，1代表会议图片，2代表人物头像，3表示精彩图片
     */
    private Short type;

    /**
     * 对应的合集的id
     */
    private Long albumId;

    private String url;

    @JsonIgnore
    private Timestamp updateTime;

    @JsonIgnore
    private Timestamp createTime;

    @JsonIgnore
    private Short isDelete;

}
