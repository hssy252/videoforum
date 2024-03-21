package com.xihu.conference.xihu.service;

import com.xihu.conference.xihu.entity.Achievement;
import java.util.List;

/**
 * 功能简述
 *
 * @author hssy
 * @version 1.0
 */
public interface AchievementService {

    void insertOne(Achievement achievement);

    void deleteById(Long id);

    void updateOne(Achievement achievement);

    List<Achievement> showDownloadable();

    List<Achievement> showUsable();

    List<Achievement> showByTag(String category);

    void addWatchCount(Long id, Long watchNum);
}
