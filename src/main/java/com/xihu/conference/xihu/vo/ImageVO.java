package com.xihu.conference.xihu.vo;

import lombok.Data;

/**
 * 功能简述
 *
 * @author hssy
 * @version 1.0
 */
@Data
public class ImageVO {

    private Long id;

    private String name;

    private String columnType;

    private Short type;

    /**
     * 图片的url
     */
    private String url;

    private Long albumId;

}
