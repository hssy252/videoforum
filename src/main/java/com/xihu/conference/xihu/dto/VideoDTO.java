package com.xihu.conference.xihu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 功能简述
 *
 * @author hssy
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VideoDTO {

    private String name;

    private String columnType;

    private Short type;

    private String cover;

    private String path;

    private Long albumId;
}
