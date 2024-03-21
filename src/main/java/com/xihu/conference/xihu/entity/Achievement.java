package com.xihu.conference.xihu.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import java.sql.Timestamp;
import lombok.Data;

/**
 * 功能简述
 *
 * @author hssy
 * @version 1.0
 */
@Data
public class Achievement {

    private Long id;

    private String title;

    private String description;

    /**
     * 观看数 or 点击数
     */
    private Long count;

    private String images;

    /**
     * 白皮书 or 新品发布 or书籍
     */
    private String category;

    /**
     * 0表示下载，1表示申请试用连接
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Short type;
    /**
     * url可以是下载链接或者是试用申请链接
     */
    private String url;

    @JsonIgnore
    private Timestamp updateTime;

    @JsonIgnore
    private Timestamp createTime;

    @JsonIgnore
    private Short isDelete;


}
