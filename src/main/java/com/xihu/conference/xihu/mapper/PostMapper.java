package com.xihu.conference.xihu.mapper;

import com.xihu.conference.xihu.entity.Post;
import com.xihu.conference.xihu.vo.PostVO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * 功能简述
 *
 * @author hssy
 * @version 1.0
 */
@Mapper
public interface PostMapper {

    void addPost(Post post);

    List<PostVO> showByTopicWithComments(Long topicId);

    List<Long> getTopicIdsByPostId(Long id);


}
