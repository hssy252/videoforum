package com.xihu.conference.xihu.service.serviceImpl;

import com.xihu.conference.xihu.dto.TopicDTO;
import com.xihu.conference.xihu.entity.Topic;
import com.xihu.conference.xihu.mapper.TopicMapper;
import com.xihu.conference.xihu.service.TopicService;
import com.xihu.conference.xihu.vo.TopicVO;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 功能简述
 *
 * @author hssy
 * @version 1.0
 */
@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicMapper topicMapper;

    @Override
    public void addTopic(TopicDTO topicDTO) {
        Topic topic = new Topic();
        BeanUtils.copyProperties(topicDTO,topic);
        topicMapper.addTopic(topic);
    }

    @Override
    public List<TopicVO> showTopics() {
        return topicMapper.showTopics();
    }
}
