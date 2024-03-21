package com.xihu.conference.xihu.service.serviceImpl;

import com.xihu.conference.xihu.entity.Integral;
import com.xihu.conference.xihu.mapper.IntegralMapper;
import com.xihu.conference.xihu.service.IntegralService;
import java.sql.Time;
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
public class IntegralServiceImpl implements IntegralService {

    @Autowired
    private IntegralMapper integralMapper;

    @Override
    public void insertOne(Integral integral) {
        integralMapper.insertOne(integral);
    }

    @Override
    public void deleteById(Long id) {
        integralMapper.deleteById(id);
    }

    @Override
    public Integral selectById(Long id) {
        return integralMapper.selectById(id);
    }

    @Override
    public List<Integral> selectAll() {
        return integralMapper.selectAll();
    }

    @Override
    public void updateById(Integral integral) {
        integralMapper.updateById(integral);
    }
}
