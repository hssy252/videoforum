package com.xihu.conference.xihu.service;

import com.xihu.conference.xihu.entity.Album;
import com.xihu.conference.xihu.vo.ImageAlbumVO;
import com.xihu.conference.xihu.vo.VideoAlbumVO;
import java.util.List;

/**
 * 功能简述
 *
 * @author hssy
 * @version 1.0
 */
public interface AlbumService {

    List<ImageAlbumVO> showImageAlbum();

    void addAlbum(Album album);

    void addCount(Long albumId);

    List<VideoAlbumVO> showVideoAlbum();

    List<Album> showByType(Short type);
}
