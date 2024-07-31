package com.xihu.conference.xihu.vo;

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
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResourceVO {

    private Long id;

    private String name;

    private String cover;

    private String description;

    private String url;

    private Short type;

}
