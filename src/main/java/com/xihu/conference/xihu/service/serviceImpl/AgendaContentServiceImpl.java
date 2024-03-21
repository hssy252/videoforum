package com.xihu.conference.xihu.service.serviceImpl;

import com.xihu.conference.xihu.entity.AgendaContent;
import com.xihu.conference.xihu.mapper.AgendaContentMapper;
import com.xihu.conference.xihu.service.AgendaContentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 功能简述
 *
 * @author hssy
 * @version 1.0
 */
@Service
public class AgendaContentServiceImpl implements AgendaContentService {

    @Autowired
    private AgendaContentMapper agendaContentMapper;

    @Override
    public void insertOne(AgendaContent agendaContent) {
        agendaContentMapper.insertOne(agendaContent);
    }

    @Override
    public void deleteById(Long id) {
        agendaContentMapper.deleteById(id);
    }

    @Override
    public void updateOne(AgendaContent agendaContent) {
        agendaContentMapper.updateOne(agendaContent);
    }

    @Override
    public List<AgendaContent> selectAll(Long agendaId) {
        return agendaContentMapper.selectAllById(agendaId);
    }
}
