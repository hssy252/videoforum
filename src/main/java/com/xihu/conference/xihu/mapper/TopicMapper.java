package com.xihu.conference.xihu.mapper;

import com.xihu.conference.xihu.entity.Topic;
import com.xihu.conference.xihu.vo.TopicVO;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * 功能简述
 *
 * @author hssy
 * @version 1.0
 */
@Mapper
public interface TopicMapper {

    @Insert("insert into topic(content, hot, cover) values (#{content},#{hot},#{cover}) ")
    void addTopic(Topic topic);

    List<TopicVO> showTopics();

    @Update("update topic set like_num=like_num+1 where id=#{likedId}")
    void addLikeNum(Long likeId);

    @Update("update topic set like_num=like_num-1 where id=#{likedId}")
    void subLikeNum(Long likeId);
}
