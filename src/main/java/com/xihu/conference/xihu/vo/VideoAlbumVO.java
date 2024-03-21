package com.xihu.conference.xihu.vo;

import java.util.List;
import lombok.Data;

/**
 * 功能简述
 *
 * @author hssy
 * @version 1.0
 */
@Data
public class VideoAlbumVO {

    private Long id;

    private Integer count;

    private Integer likeNum;

    private String category;

    private List<VideoVO> videos;

}
