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
public class TopicVO {

    private Long id;

    private String content;

    private Integer hot;

    private Integer likeNum;

    private String cover;

    private Integer postNum;

    private Integer watchNum;

}
