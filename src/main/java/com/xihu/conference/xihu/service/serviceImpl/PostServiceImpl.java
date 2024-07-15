package com.xihu.conference.xihu.service.serviceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xihu.conference.xihu.dto.PostDTO;
import com.xihu.conference.xihu.entity.Post;
import com.xihu.conference.xihu.entity.TopicPostReference;
import com.xihu.conference.xihu.mapper.PostMapper;
import com.xihu.conference.xihu.mapper.TopicPostReferenceMapper;
import com.xihu.conference.xihu.result.PageResult;
import com.xihu.conference.xihu.service.PostService;
import com.xihu.conference.xihu.vo.PostVO;
import com.xihu.conference.xihu.vo.SimplePostVO;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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
public class PostServiceImpl implements PostService {

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private TopicPostReferenceMapper topicPostReferenceMapper;


    @Override
    public void addPost(PostDTO postDTO) {

        Post post = new Post();
        BeanUtils.copyProperties(postDTO, post);

        //将image转化为用逗号分隔的字符串
        List<String> images = postDTO.getImages();
        String collect = images.stream().map(String::valueOf).collect(Collectors.joining(","));
        post.setImages(collect);

        //先向表中插入post数据，然后获得返回的主键,即帖子的id
        postMapper.addPost(post);
        Long postId = post.getId();

        //再向标签帖子关系表中添加记录
        List<Long> topicIds = postDTO.getTopicIds();
        List<TopicPostReference> references = new ArrayList<>();
        for (Long topicId : topicIds) {
            TopicPostReference topicPostReference = new TopicPostReference();
            topicPostReference.setTopicId(topicId);
            topicPostReference.setPostId(postId);
            references.add(topicPostReference);
        }

        topicPostReferenceMapper.addReferences(references);

    }

    // TODO 加入缓存
    @Override
    public List<PostVO> showByTopic(Long topicId) {

        return postMapper.showByTopicWithComments(topicId);
    }

    @Override
    public PageResult pageQuery() {
        List<PostVO> pages = postMapper.pageQuery();
        return new PageResult(pages.size(), pages);
    }

    @Override
    public PageResult simplePageQuery(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        Page<SimplePostVO> posts =  postMapper.simplePageQuery();
        return new PageResult(posts.getTotal(),posts.getResult());
    }


}
