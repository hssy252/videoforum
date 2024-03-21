package com.xihu.conference.xihu.service.serviceImpl;

import com.xihu.conference.xihu.entity.Integral;
import com.xihu.conference.xihu.entity.IntegralRecord;
import com.xihu.conference.xihu.mapper.IntegralRecordMapper;
import com.xihu.conference.xihu.service.IntegralRecordService;
import com.xihu.conference.xihu.service.IntegralService;
import java.sql.Timestamp;
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
public class IntegralRecordServiceImpl implements IntegralRecordService {

    @Autowired
    private IntegralRecordMapper integralRecordMapper;

    @Override
    public void insertOne(IntegralRecord integralRecord) {
        integralRecordMapper.insertOne(integralRecord);
    }

    @Override
    public void deleteById(Long id) {
        integralRecordMapper.deleteById(id);
    }

    @Override
    public void updateById(IntegralRecord integralRecord) {

        integralRecordMapper.updateById(integralRecord);
    }

    @Override
    public IntegralRecord selectById(Long id) {
        return integralRecordMapper.selectById(id);
    }

    @Override
    public List<IntegralRecord> selectAll(Long userId) {
        return integralRecordMapper.selectAll(userId);
    }

    @Override
    public List<IntegralRecord> showAll() {
        return integralRecordMapper.showAll();
    }
}
