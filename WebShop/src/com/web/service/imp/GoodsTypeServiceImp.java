package com.web.service.imp;

import com.web.bean.GoodsType;
import com.web.mapper.GoodsTypeMapper;
import com.web.service.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class GoodsTypeServiceImp implements GoodsTypeService {
    @Autowired
    private GoodsTypeMapper goodsTypeMapper;

    @Override
    public List<GoodsType> list(String name) {
        return StringUtils.isEmpty(name) ? goodsTypeMapper.listAll() : goodsTypeMapper.listByName(name);
    }

    @Override
    public Integer save(GoodsType goodsType) {
        return goodsTypeMapper.save(goodsType);
    }

    @Override
    public Integer delete(Integer id) {
        return goodsTypeMapper.delete(id);
    }

    @Override
    public GoodsType getGoodsType(Integer id) {
        return goodsTypeMapper.getById(id);
    }

    @Override
    public Integer update(GoodsType goodsType) {
        return goodsTypeMapper.update(goodsType);
    }

    @Override
    public List<GoodsType> listFatherTypes() {
        return goodsTypeMapper.listFatherTypes();
    }

    @Override
    public List<GoodsType> listAll() {
        return goodsTypeMapper.listAll();
    }

}
