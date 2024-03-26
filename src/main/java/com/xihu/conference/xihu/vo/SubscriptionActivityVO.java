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
public class SubscriptionActivityVO {

    /**
     * 订阅id
     */
    private Long id;

    private Long userId;

    private Long activityId;

    private String name;
    /**
     * 会议id
     */
    private Long contentId;

    private String cover;

    private String timespan;

}
