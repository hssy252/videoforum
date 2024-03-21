package com.xihu.conference.xihu.service;

import com.xihu.conference.xihu.entity.Integral;
import java.util.List;

/**
 * 功能简述
 *
 * @author hssy
 * @version 1.0
 */
public interface IntegralService {

    void insertOne(Integral integral);

    void deleteById(Long id);

    Integral selectById(Long id);

    List<Integral> selectAll();

    void updateById(Integral integral);

}
