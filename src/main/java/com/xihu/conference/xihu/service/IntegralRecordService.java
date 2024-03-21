package com.xihu.conference.xihu.service;

import com.xihu.conference.xihu.entity.IntegralRecord;
import java.util.List;

/**
 * 功能简述
 *
 * @author hssy
 * @version 1.0
 */
public interface IntegralRecordService {

    void insertOne(IntegralRecord integralRecord);

    void deleteById(Long id);

    void updateById(IntegralRecord integralRecord);

    IntegralRecord selectById(Long id);

    List<IntegralRecord> selectAll(Long userId);

    List<IntegralRecord> showAll();
}
