package com.xihu.conference.xihu.service;

import com.xihu.conference.xihu.entity.Guest;
import com.xihu.conference.xihu.vo.ExpertVO;
import com.xihu.conference.xihu.vo.GuestVO;
import java.util.List;

/**
 * 功能简述
 *
 * @author hssy
 * @version 1.0
 */
public interface GuestService {

    void addExpert(Guest guest);

    List<GuestVO> showAll();

    List<ExpertVO> showExperts();
}
