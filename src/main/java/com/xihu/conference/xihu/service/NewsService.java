package com.xihu.conference.xihu.service;

import com.xihu.conference.xihu.entity.News;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * 功能简述
 *
 * @author hssy
 * @version 1.0
 */

@Service
public interface NewsService {

    void addNews(News news);

    List<News> showAll();
}
