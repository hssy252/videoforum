package com.xihu.conference.xihu.service.serviceImpl;

import com.xihu.conference.xihu.dto.LikedDTO;
import com.xihu.conference.xihu.mapper.CommentMapper;
import com.xihu.conference.xihu.mapper.PostMapper;
import com.xihu.conference.xihu.mapper.PraiseMapper;
import com.xihu.conference.xihu.mapper.TopicMapper;
import com.xihu.conference.xihu.service.PraiseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private TopicMapper topicMapper;

    @Override
    public List<Long> showUserIds(Long likedId, Short type) {
        return praiseMapper.showUserIds(likedId, type);
    }

    @Override
    @Transactional
    public void like(LikedDTO likedDTO) {
        Long likedId = likedDTO.getLikedId();
        if (queryLiked(likedDTO)) {
            praiseMapper.likeAgain(likedDTO);
        } else {
            praiseMapper.like(likedDTO);
        }
        updateLikeNum(likedDTO.getType(), likedId, (short) 1);
    }

    @Override
    public Boolean queryLiked(LikedDTO likedDTO) {
        return praiseMapper.queryLiked(likedDTO) == 1;
    }

    @Override
    public List<Long> showPostIds(Long userId, Short type) {
        return praiseMapper.showPostIds(userId, type);
    }

    @Override
    public void likeAgain(LikedDTO likedDTO) {
        praiseMapper.likeAgain(likedDTO);
    }

    @Override
    @Transactional
    public void disLike(LikedDTO likedDTO) {
        Long likedId = likedDTO.getLikedId();
        praiseMapper.disLike(likedDTO);
        updateLikeNum(likedDTO.getType(), likedId, (short) 0);
    }

    private void updateLikeNum(Short integer, Long likedId, Short changeType) {
        if (changeType == 1) {
            switch (integer) {
                case 1: {
                    postMapper.addLikeNum(likedId);
                }
                break;
                case 2: {
                    commentMapper.addLikeNum(likedId);
                }
                break;
                case 3: {
                    topicMapper.addLikeNum(likedId);
                }
                break;
                default:
                    break;
            }
        }
        if (changeType == 0) {
            switch (integer) {
                case 1: {
                    postMapper.subLikeNum(likedId);
                }
                break;
                case 2: {
                    commentMapper.subLikeNum(likedId);
                }
                break;
                case 3: {
                    topicMapper.subLikeNum(likedId);
                }
                break;
                default:
                    break;
            }
        }
    }
}
