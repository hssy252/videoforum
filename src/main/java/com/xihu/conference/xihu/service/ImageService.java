package com.xihu.conference.xihu.service;

import com.xihu.conference.xihu.entity.Image;
import com.xihu.conference.xihu.vo.ImageVO;
import java.util.List;

/**
 * 功能简述
 *
 * @author hssy
 * @version 1.0
 */
public interface ImageService {

    void addImage(Image image);

    void deleteImageById(Integer id);

    Image selectById(Integer id);

    Image selectByUrl(String url);

    List<Image> selectByType(Short type);

    List<Image> getAll();

    List<String> getColumnType();

    void updateById(Image image);

    List<String> showColumns();

    List<ImageVO> showByColumn(String column);
}
