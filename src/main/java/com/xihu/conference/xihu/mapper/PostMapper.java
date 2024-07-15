package com.xihu.conference.xihu.mapper;

import com.github.pagehelper.Page;
import com.xihu.conference.xihu.entity.Post;
import com.xihu.conference.xihu.result.PageResult;
import com.xihu.conference.xihu.vo.PostVO;
import com.xihu.conference.xihu.vo.SimplePostVO;
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



    List<PostVO> pageQuery();

    Page<SimplePostVO> simplePageQuery();
}
