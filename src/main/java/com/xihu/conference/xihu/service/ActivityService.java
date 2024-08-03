package com.xihu.conference.xihu.service;

import com.xihu.conference.xihu.entity.Activity;
import java.util.List;

/**
 * 功能简述
 *
 * @author hssy
 * @version 1.0
 */
public interface ActivityService {

    void insertOne(Activity activity);

    void deleteById(Long id);

    void updateOne(Activity activity);

    List<String> showTags();

    List<Activity> selectByTag(String tag);

    List<Activity> showAll();

    void addWatchNum(Long id);
}
