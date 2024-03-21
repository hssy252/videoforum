package com.xihu.conference.xihu.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 功能简述
 *
 * @author hssy
 * @version 1.0
 */
@Data
public class Integral {

    private Long id;

    /**
     * 1表示可进行多次，0表示只能进行一次
     */
    private Integer type;

    /**
     * 具体描述
     */
    private String description;

    private Integer amount;

    /**
     * 活动的name
     */
    private String detail;

    /**
     * 未完成时显示的字样
     */
    private String toDo;

    /**
     * 完成时显示的字样
     */
    private String finishedWords;

    /**
     * 进行该活动而前往的url
     */
    private String url;

    @JsonIgnore
    private Timestamp updateTime;

    @JsonIgnore
    private Timestamp createTime;

    @JsonIgnore
    private Short isDelete;
}
