package com.xihu.conference.xihu.dto;

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
public class PostDTO {

    private Long userId;

    private String content;

    private List<String> images;

    private List<Long> topicIds;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp publishTime;
}
