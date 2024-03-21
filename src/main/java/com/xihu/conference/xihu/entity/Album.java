package com.xihu.conference.xihu.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import java.sql.Timestamp;
import lombok.Data;

/**
 * 功能简述
 *
 * @author hssy
 * @version 1.0
 */
@Data
public class Album {

    private Long id;

    /**
     * 集合中资源的数量
     */
    private Integer count;

    /**
     * 集合的点赞数
     */
    private Integer likeNum;

    /**
     * 集合的类型，1代表图片集合，2代表视频集合
     */
    @ApiModelProperty(value = "集合的类型，1代表图片集合，2代表视频集合")
    private Short type;

    /**
     * 集合的title
     */
    private String category;

    @JsonIgnore
    private Timestamp createTime;

    @JsonIgnore
    private Timestamp updateTime;

    @JsonIgnore
    private Short isDelete;
}
