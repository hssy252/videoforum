package com.xihu.conference.xihu.mapper;

import com.xihu.conference.xihu.entity.TopicPostReference;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * 功能简述
 *
 * @author hssy
 * @version 1.0
 */
@Mapper
public interface TopicPostReferenceMapper {

    void addReferences(List<TopicPostReference> references);
}
