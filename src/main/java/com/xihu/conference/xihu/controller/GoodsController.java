package com.xihu.conference.xihu.controller;

import com.xihu.conference.xihu.entity.Goods;
import com.xihu.conference.xihu.entity.Result;
import com.xihu.conference.xihu.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能简述
 *
 * @author hssy
 * @version 1.0
 */
@RestController
@RequestMapping("/goods")
@Api(tags = "商品", description ="用积分兑换的礼品")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @ApiOperation("增加商品")
    @PostMapping("/add")
    public Result insertOne(@RequestBody Goods goods) {
        goodsService.insertOne(goods);
        return Result.success();
    }

    @ApiOperation("逻辑删除商品")
    @GetMapping("/delete/{id}")
    public Result deleteOne(@PathVariable Long id) {
        goodsService.deleteById(id);
        return Result.success();
    }

    @ApiOperation("通过id更新商品")
    @PostMapping("/update")
    public Result updateOne(@RequestBody Goods goods) {
        goodsService.updateById(goods);
        return Result.success();
    }

    @ApiOperation("通过id显示商品")
    @GetMapping("/show/{id}")
    public Result<Goods> selectById(@PathVariable Long id) {
        return Result.success(goodsService.selectById(id));
    }

    @ApiOperation("通过类别显示商品")
    @GetMapping("/show/tag")
    public Result<List<Goods>> selectByTag(@RequestParam String category){
        return Result.success(goodsService.selectByTag(category));
    }

    @ApiOperation("显示所有商品")
    @GetMapping("/show")
    public Result<List<Goods>> selectAll() {
        return Result.success(goodsService.selectAll());
    }

    @ApiOperation("减少商品数量")
    @GetMapping("/consume")
    public Result consumeSome(@RequestParam Integer num, @RequestParam Long id) {
        goodsService.consumeSome(num, id);
        return Result.success();
    }
}
