package com.xihu.conference.xihu.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class AgendaContent {

    private Long id;

    /**
     * 对应的会议的id
     */
    private Long agendaId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp endTime;

    private String timespan;

    private String content;

    private String guestList;

    @JsonIgnore
    private Timestamp updateTime;

    @JsonIgnore
    private Timestamp createTime;

    @JsonIgnore
    private Short isDelete;
}
