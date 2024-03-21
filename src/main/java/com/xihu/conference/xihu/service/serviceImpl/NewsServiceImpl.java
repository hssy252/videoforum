package com.xihu.conference.xihu.service.serviceImpl;

import com.xihu.conference.xihu.entity.News;
import com.xihu.conference.xihu.mapper.NewsMapper;
import com.xihu.conference.xihu.service.NewsService;
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
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsMapper newsMapper;

    @Override
    public void addNews(News news) {
        newsMapper.addNews(news);
    }

    @Override
    public List<News> showAll() {
        return newsMapper.showAll();
    }
}
