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
public class Goods {

    private Integer id;

    private String name;

    private Integer price;

    private String description;

    private Integer remainNum;
    /**
     * 商品的分类
     */
    private String category;

    private String url;

    @JsonIgnore
    private Timestamp updateTime;

    @JsonIgnore
    private Timestamp createTime;

    @JsonIgnore
    private Short isDelete;
}
