package com.web.service.imp;

import com.web.bean.Goods;
import com.web.mapper.GoodsMapper;
import com.web.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class GoodsServiceImp implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public List<Goods> list(String conditon) {
        return StringUtils.isEmpty(conditon) ? goodsMapper.listAll() : goodsMapper.listByConditon(conditon);
    }

    @Override
    public Integer save(Goods goods) {
        return goodsMapper.save(goods);
    }

    @Override
    public Integer delete(Integer id) {
        return goodsMapper.delete(id);
    }

    @Override
    public Goods getGoods(Integer id) {
        return goodsMapper.getById(id);
    }

    @Override
    public Integer update(Goods goods) {
        return goodsMapper.update(goods);
    }

    @Override
    public List<Goods> listAll() {
        return goodsMapper.listAll();
    }

}
