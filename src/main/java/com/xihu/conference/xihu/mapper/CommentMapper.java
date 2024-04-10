package com.xihu.conference.xihu.mapper;

import com.xihu.conference.xihu.entity.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

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
}
