package com.xihu.conference.xihu.service.serviceImpl;

import com.xihu.conference.xihu.dto.VideoDTO;
import com.xihu.conference.xihu.entity.Video;
import com.xihu.conference.xihu.mapper.VideoMapper;
import com.xihu.conference.xihu.service.VideoService;
import com.xihu.conference.xihu.vo.VideoVO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 功能简述
 *
 * @author hssy
 * @version 1.0
 */
@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoMapper videoMapper;

    @Override
    public void addVideo(VideoDTO video) {
        videoMapper.addVideo(video);
    }

    @Override
    public List<Video> showVideo() {
        return videoMapper.showVideo();
    }

    @Override
    public List<VideoVO> showByColumn(String column) {
        return videoMapper.showByColumn(column);
    }

    @Override
    public List<String> showColumns() {
        return videoMapper.showColumns();
    }
}
