package com.xihu.conference.xihu.service;

import com.xihu.conference.xihu.entity.Goods;
import java.util.List;

/**
 * 功能简述
 *
 * @author hssy
 * @version 1.0
 */

public interface GoodsService {

    void insertOne(Goods goods);

    void deleteById(Long id);

    void updateById(Goods goods);

    Goods selectById(Long id);

    List<Goods> selectAll();

    /**
     * 用户兑换商品时，对应商品数量要相应减少
     * @param some 商品被兑换的数量，即要减少的数量
     * @param id 商品的id
     */
    void consumeSome(Integer some,Long id);

    List<Goods> selectByTag(String category);
}
