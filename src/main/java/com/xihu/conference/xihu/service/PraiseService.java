package com.xihu.conference.xihu.service;

import com.xihu.conference.xihu.dto.LikedDTO;
import java.util.List;

/**
 * 功能简述
 *
 * @author hssy
 * @version 1.0
 */
public interface PraiseService {

    List<Long> showUserIds(Long likedId, Short type);

    void like(LikedDTO likedDTO);

    Boolean queryLiked(LikedDTO likedDTO);

    List<Long> showPostIds(Long userId, Short type);

    void likeAgain(LikedDTO likedDTO);

    void disLike(LikedDTO likedDTO);

}
