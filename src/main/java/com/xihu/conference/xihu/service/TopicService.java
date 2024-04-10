package com.xihu.conference.xihu.service;

import com.xihu.conference.xihu.dto.TopicDTO;
import com.xihu.conference.xihu.vo.TopicVO;
import java.util.List;

/**
 * 功能简述
 *
 * @author hssy
 * @version 1.0
 */
public interface TopicService {

    void addTopic(TopicDTO topicDTO);

    List<TopicVO> showTopics();
}
