package com.xihu.conference.xihu.service.serviceImpl;

import com.xihu.conference.xihu.entity.Activity;
import com.xihu.conference.xihu.mapper.ActivityMapper;
import com.xihu.conference.xihu.service.ActivityService;
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
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public void insertOne(Activity activity) {
        activityMapper.insertOne(activity);
    }

    @Override
    public void deleteById(Long id) {
        activityMapper.deleteById(id);
    }

    @Override
    public void updateOne(Activity activity) {
        activityMapper.updateOne(activity);
    }

    @Override
    public List<String> showTags() {
        return activityMapper.showTags();
    }

    @Override
    public List<Activity> selectByTag(String tag) {
        return activityMapper.selectByTags(tag);
    }

    @Override
    public List<Activity> showAll() {
        return activityMapper.showAll();
    }
}
