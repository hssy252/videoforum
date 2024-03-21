package com.xihu.conference.xihu.service.serviceImpl;

import com.xihu.conference.xihu.entity.Goods;
import com.xihu.conference.xihu.mapper.GoodsMapper;
import com.xihu.conference.xihu.service.GoodsService;
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
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public void insertOne(Goods goods) {
        goodsMapper.insertOne(goods);
    }

    @Override
    public void deleteById(Long id) {
        goodsMapper.deleteById(id);
    }

    @Override
    public void updateById(Goods goods) {
        goodsMapper.updateById(goods);
    }

    @Override
    public Goods selectById(Long id) {
        return goodsMapper.selectById(id);
    }

    @Override
    public List<Goods> selectAll() {
        return goodsMapper.selectAll();
    }

    @Override
    public void consumeSome(Integer some,Long id) {
        goodsMapper.consumeSome(some,id);
    }

    @Override
    public List<Goods> selectByTag(String category) {
        return goodsMapper.selectByTag(category);
    }
}
