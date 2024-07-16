package com.xihu.conference.xihu.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.sql.Timestamp;
import java.util.List;
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
public class SimplePostVO {

    /**
     * 帖子的id
     */
    private Long id;

    private SimpleUserVO user;

    private String content;

    private String images;

    private Integer hot;

    private Integer commentNum;

    private Integer likeNum;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp publishTime;
    /**
     * 帖子关联的topicId列表
     */
    private List<Long> topicIds;

}
