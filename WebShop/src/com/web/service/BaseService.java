package com.web.service;

import com.web.bean.*;

import java.util.List;

public interface BaseService {

    List<FatherAndChildType> listTypes();

    List<Picture> listPictures();

    List<TimeLimit> listTimeLimit();

    List<Goods> listGoodsByType(Integer type);

    List<Goods> listGoodsByCondition(String condition);

    Goods getGoods(Integer goodsid);

    GoodsType getGoodsType(Integer type);

    List<Goods> listGoods();

    String getFatherTypeName(Integer id);
}
