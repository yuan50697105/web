package com.web.service;

import com.web.bean.Goods;

import java.util.List;

public interface GoodsService {

    List<Goods> list(String conditon);

    Integer save(Goods goods);

    Integer delete(Integer id);

    Goods getGoods(Integer id);

    Integer update(Goods goods);

    List<Goods> listAll();

}
