package com.xihu.conference.xihu.service.serviceImpl;

import com.xihu.conference.xihu.entity.Image;
import com.xihu.conference.xihu.mapper.ImageMapper;
import com.xihu.conference.xihu.service.ImageService;
import com.xihu.conference.xihu.vo.ImageVO;
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
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageMapper imageMapper;

    @Override
    public void addImage(Image image) {
        imageMapper.addImage(image);
    }

    @Override
    public void deleteImageById(Integer id) {

    }

    @Override
    public Image selectById(Integer id) {
        return null;
    }

    @Override
    public Image selectByUrl(String url) {
        return null;
    }

    @Override
    public List<Image> selectByType(Short type) {
        return imageMapper.selectByType(type);
    }

    @Override
    public List<Image> getAll() {
        return null;
    }

    @Override
    public List<String> getColumnType() {
        return null;
    }

    @Override
    public void updateById(Image image) {

    }

    @Override
    public List<String> showColumns() {
        return imageMapper.showColumns();
    }

    @Override
    public List<ImageVO> showByColumn(String column) {
        return imageMapper.showByColumn(column);
    }
}
