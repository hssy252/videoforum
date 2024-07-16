package com.xihu.conference.xihu.service.serviceImpl;

import com.xihu.conference.xihu.dto.LikedDTO;
import com.xihu.conference.xihu.mapper.PraiseMapper;
import com.xihu.conference.xihu.service.PraiseService;
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
public class PraiseServiceImpl implements PraiseService {

    @Autowired
    private PraiseMapper praiseMapper;

    @Override
    public List<Long> showUserIds(Long likedId, Short type) {
        return praiseMapper.showUserIds(likedId,type);
    }

    @Override
    public void like(LikedDTO likedDTO) {
        if (queryLiked(likedDTO)) {
            praiseMapper.likeAgain(likedDTO);
        }
        else praiseMapper.like(likedDTO);
    }

    @Override
    public Boolean queryLiked(LikedDTO likedDTO) {
        return praiseMapper.queryLiked(likedDTO) == 1;
    }

    @Override
    public List<Long> showPostIds(Long userId, Short type) {
        return praiseMapper.showPostIds(userId,type);
    }

    @Override
    public void likeAgain(LikedDTO likedDTO) {
        praiseMapper.likeAgain(likedDTO);
    }

    @Override
    public void disLike(LikedDTO likedDTO) {
        praiseMapper.disLike(likedDTO);
    }
}
