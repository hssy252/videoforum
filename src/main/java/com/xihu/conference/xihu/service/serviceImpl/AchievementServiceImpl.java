package com.xihu.conference.xihu.service.serviceImpl;

import com.xihu.conference.xihu.entity.Achievement;
import com.xihu.conference.xihu.mapper.AchievementMapper;
import com.xihu.conference.xihu.service.AchievementService;
import java.sql.Timestamp;
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
public class AchievementServiceImpl implements AchievementService {

    @Autowired
    private AchievementMapper achievementMapper;

    @Override
    public void insertOne(Achievement achievement) {
        achievementMapper.insertOne(achievement);
    }

    @Override
    public void deleteById(Long id) {
        achievementMapper.deleteById(id);
    }

    @Override
    public void updateOne(Achievement achievement) {
        achievementMapper.updateOne(achievement);
    }

    @Override
    public List<Achievement> showDownloadable() {
        return achievementMapper.showDownloadable();
    }

    @Override
    public List<Achievement> showUsable() {
        return achievementMapper.showUsable();
    }

    @Override
    public List<Achievement> showByTag(String category) {
        return achievementMapper.showByCategory(category);
    }

    @Override
    public void addWatchCount(Long id, Long watchNum) {
        achievementMapper.addWatchCount(id,watchNum);
    }
}
