package com.xihu.conference.xihu.service;

import com.xihu.conference.xihu.dto.VideoDTO;
import com.xihu.conference.xihu.entity.Video;
import com.xihu.conference.xihu.vo.VideoVO;
import java.util.List;

/**
 * 功能简述
 *
 * @author hssy
 * @version 1.0
 */
public interface VideoService {


    void addVideo(VideoDTO video);

    List<Video> showVideo();

    List<VideoVO> showByColumn(String column);

    List<String> showColumns();
}
