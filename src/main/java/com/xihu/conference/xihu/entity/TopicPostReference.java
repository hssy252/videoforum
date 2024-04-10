package com.xihu.conference.xihu.entity;

import java.sql.Timestamp;
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
public class TopicPostReference {

    private Long id;

    private Long postId;

    private Long topicId;

    private Timestamp createTime;

    private Timestamp updateTime;

    private Short isDelete;
}
