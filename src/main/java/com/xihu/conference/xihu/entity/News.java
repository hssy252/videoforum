package com.xihu.conference.xihu.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.sql.Date;
import java.sql.Timestamp;
import lombok.Data;

/**
 * 功能简述
 *
 * @author hssy
 * @version 1.0
 */

@Data
public class News {

    private Long id;

    private String title;

    private String publisher;

    /**
     * 封面的url
     */
    private String image;

    private String description;

    private Date publishTime;


    @JsonIgnore
    private Timestamp createTime;

    @JsonIgnore
    private Timestamp updateTime;

    @JsonIgnore
    private Short isDelete;
}
