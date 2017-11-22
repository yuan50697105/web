package com.web.service.imp;

import com.web.bean.Goods;
import com.web.mapper.GoodsMapper;
import com.web.service.ShopCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopCarServiceImp implements ShopCarService {
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public Goods getGoods(Integer integer) {
        return goodsMapper.getById(integer);
    }

}
