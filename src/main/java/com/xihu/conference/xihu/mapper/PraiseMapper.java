package com.xihu.conference.xihu.mapper;

import com.xihu.conference.xihu.dto.LikedDTO;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 功能简述
 *
 * @author hssy
 * @version 1.0
 */
@Mapper
public interface PraiseMapper {

    @Select("select user_id from praise where type=#{type} and liked_id=#{likedId} and is_delete=0")
    List<Long> showUserIds(Long likedId, Short type);

    @Insert("insert into praise(user_id,liked_id,type) values(#{userId},#{likedId},#{type})")
    void like(LikedDTO likedDTO);

    @Select("select count(*) from praise where type=#{type} and liked_id=#{likedId} and user_id=#{userId} ")
    Integer queryLiked(LikedDTO likedDTO);

    @Select("select liked_id from praise where type=#{type} and user_id=#{userId} and is_delete=0")
    List<Long> showPostIds(Long userId, Short type);

    @Update("update praise set is_delete=0 where user_id=#{userId} and liked_id=#{likedId} and type=#{type}")
    void likeAgain(LikedDTO likedDTO);

    @Update("update praise set is_delete=1 where user_id=#{userId} and liked_id=#{likedId} and type=#{type}")
    void disLike(LikedDTO likedDTO);
}
