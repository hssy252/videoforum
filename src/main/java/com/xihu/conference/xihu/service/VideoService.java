package com.xihu.conference.xihu.service;

import com.xihu.conference.xihu.entity.Video;
import java.util.List;

/**
 * 功能简述
 *
 * @author hssy
 * @version 1.0
 */
public interface VideoService {


    void addVideo(Video video);

    List<Video> showVideo();
}
