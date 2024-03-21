package com.xihu.conference.xihu.service.serviceImpl;

import com.xihu.conference.xihu.entity.Album;
import com.xihu.conference.xihu.mapper.AlbumMapper;
import com.xihu.conference.xihu.service.AlbumService;
import com.xihu.conference.xihu.vo.ImageAlbumVO;
import com.xihu.conference.xihu.vo.VideoAlbumVO;
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
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumMapper albumMapper;

    @Override
    public List<ImageAlbumVO> showImageAlbum() {
            return albumMapper.showImageAlbum();
    }

    @Override
    public void addAlbum(Album album) {
        albumMapper.addAlbum(album);
    }

    @Override
    public void addCount(Long albumId) {
        albumMapper.addCount(albumId);
    }

    @Override
    public List<VideoAlbumVO> showVideoAlbum() {
        return albumMapper.showVideoAlbum();
    }

    @Override
    public List<Album> showByType(Short type) {
        return albumMapper.showByType(type);
    }
}
