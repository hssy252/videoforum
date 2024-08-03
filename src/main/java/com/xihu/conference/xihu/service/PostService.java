package com.xihu.conference.xihu.service;

import com.xihu.conference.xihu.dto.PostDTO;
import com.xihu.conference.xihu.result.PageResult;
import com.xihu.conference.xihu.vo.PostVO;
import java.util.List;

/**
 * 功能简述
 *
 * @author hssy
 * @version 1.0
 */
public interface PostService {

    void addPost(PostDTO postDTO);

    List<PostVO> showByTopic(Long topicId);

    PageResult pageQuery();

    PageResult simplePageQuery(int pageNum, int pageSize);

    void deletePost(Long postId);

    List<PostVO> showByUserId(Long userId);
}
