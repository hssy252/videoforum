package com.xihu.conference.xihu.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.sql.Timestamp;
import lombok.Data;

/**
 * 功能简述
 *
 * @author hssy
 * @version 1.0
 */

@Data
public class User {

    private Long id;

    private String name;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private String image;

    private String identification;

    private String enterprise;

    private String department;

    private String position;

    private String tel;

    private String email;

    private Integer integral;

    @JsonIgnore
    private Timestamp createTime;

    @JsonIgnore
    private Timestamp updateTime;

    @JsonIgnore
    private Short isDelete;
}
