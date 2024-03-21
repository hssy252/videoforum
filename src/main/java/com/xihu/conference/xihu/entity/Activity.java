package com.xihu.conference.xihu.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.sql.Timestamp;
import lombok.Data;

/**
 * 功能简述
 *
 * @author hssy
 * @version 1.0
 */

@Data
public class Activity {

    private Long id;

    private String name;

    private String columnName;

    /**
     * 0代表会议，1则代表文章
     */
    private Short contentType;

    private Long contentId;

    private String cover;

    private String description;

    private Integer bookCount;

    private String timespan;

    private Short isBookable;

    @JsonIgnore
    private Timestamp updateTime;

    @JsonIgnore
    private Timestamp createTime;

    @JsonIgnore
    private Short isDelete;
}
