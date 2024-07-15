package com.xihu.conference.xihu.service;

import com.xihu.conference.xihu.dto.CommentDTO;
import com.xihu.conference.xihu.vo.CommentVO;
import java.util.List;

/**
 * 功能简述
 *
 * @author hssy
 * @version 1.0
 */
public interface CommentService {

    void addComment(CommentDTO commentDTO);

    List<CommentVO> showByPostId(Long id);
}
