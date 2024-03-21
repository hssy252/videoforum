package com.xihu.conference.xihu.service;

import com.xihu.conference.xihu.entity.AgendaContent;
import java.util.List;

/**
 * 功能简述
 *
 * @author hssy
 * @version 1.0
 */
public interface AgendaContentService {

    void insertOne(AgendaContent agendaContent);

    void deleteById(Long id);

    void updateOne(AgendaContent agendaContent);

    List<AgendaContent> selectAll(Long agendaId);
}
