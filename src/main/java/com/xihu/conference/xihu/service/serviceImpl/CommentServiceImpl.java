package com.xihu.conference.xihu.service.serviceImpl;

import com.xihu.conference.xihu.dto.CommentDTO;
import com.xihu.conference.xihu.entity.Comment;
import com.xihu.conference.xihu.mapper.CommentMapper;
import com.xihu.conference.xihu.service.CommentService;
import com.xihu.conference.xihu.vo.CommentVO;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 功能简述
 *
 * @author hssy
 * @version 1.0
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    // TODO 暂未获取用户的ip
    @Override
    public void addComment(CommentDTO commentDTO) {
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentDTO, comment);
        //获取ip
        comment.setIp("中国");
        commentMapper.addComment(comment);
    }

    @Override
    public List<CommentVO> showByPostId(Long id) {
        return commentMapper.showByPostId(id);
    }
}
