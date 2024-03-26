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
public class SubscriptionAgendaVO {

    /**
     * 订阅id
     */
    private Long id;

    private Long userId;

    private Long agendaId;

    /**
     * 会议标题
     */
    private String title;

    /**
     * 会议分类标签
     */
    private String tag;

    private String cover;

    /**
     * 会议链接
     */
    private String contentUrl;

    /**
     * 是否直播中
     */
    private Short isLive;

    private String subTitle;

    private String timespan;
}
