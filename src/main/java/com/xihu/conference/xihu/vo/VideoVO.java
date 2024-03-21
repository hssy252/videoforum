package com.xihu.conference.xihu.vo;

import lombok.Data;

/**
 * 功能简述
 *
 * @author hssy
 * @version 1.0
 */
@Data
public class VideoVO {

    private Long id;

    private String name;

    private String columnType;

    private Short type;

    private String cover;

    private String path;

    private Long albumId;

    private Integer watchCount;

    private Integer likeNum;

    private Long resourceId;

}
