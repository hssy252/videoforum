package com.xihu.conference.xihu.mapper;

import com.xihu.conference.xihu.entity.Comment;
import com.xihu.conference.xihu.vo.CommentVO;
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
public interface CommentMapper {

    @Insert("insert into comment(user_id, post_id, content,ip) values(#{userId},#{postId},#{content},#{ip})")
    void addComment(Comment comment);

    List<CommentVO> showByPostId(Long id);

    @Update("update comment set like_count=like_count+1 where id=#{likedId}")
    void addLikeNum(Long likeId);

    @Update("update comment set like_count=like_count-1 where id=#{likedId}")
    void subLikeNum(Long likeId);
}
