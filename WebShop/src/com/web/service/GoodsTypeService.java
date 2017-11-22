package com.web.service;

import com.web.bean.GoodsType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GoodsTypeService {

    List<GoodsType> list(String name);

    Integer save(GoodsType goodsType);

    Integer delete(Integer id);

    GoodsType getGoodsType(Integer id);

    Integer update(GoodsType goodsType);

    List<GoodsType> listFatherTypes();

    List<GoodsType> listAll();

}
