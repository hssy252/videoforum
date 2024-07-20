package com.xihu.conference.xihu.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentVO {

    private Long id;

    private String content;

    private String ip;

    private Integer likeCount;

    @JsonFormat(pattern = "yyyy年MM月dd日 hh:mm")
    private Timestamp createTime;

    private Long parentId;

    private SimpleUserVO simpleUserVO;

}
