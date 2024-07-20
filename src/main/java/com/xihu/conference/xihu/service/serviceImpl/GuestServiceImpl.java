package com.xihu.conference.xihu.service.serviceImpl;

import com.xihu.conference.xihu.entity.Guest;
import com.xihu.conference.xihu.mapper.GuestMapper;
import com.xihu.conference.xihu.service.GuestService;
import com.xihu.conference.xihu.vo.ExpertVO;
import com.xihu.conference.xihu.vo.GuestVO;
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
public class GuestServiceImpl implements GuestService {

    @Autowired
    private GuestMapper guestMapper;

    @Override
    public void addExpert(Guest guest) {
        guestMapper.addExpert(guest);
    }

    @Override
    public List<GuestVO> showAll() {
        return guestMapper.showAll();
    }

    @Override
    public List<ExpertVO> showExperts() {
        return guestMapper.showExperts();
    }
}
