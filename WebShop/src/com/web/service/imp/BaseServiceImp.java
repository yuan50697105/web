package com.web.service.imp;

import com.web.bean.*;
import com.web.mapper.GoodsMapper;
import com.web.mapper.GoodsTypeMapper;
import com.web.mapper.PictureMapper;
import com.web.mapper.TimeLimitMapper;
import com.web.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class BaseServiceImp implements BaseService {
    @Autowired
    private GoodsTypeMapper goodsTypeMapper;
    @Autowired
    private PictureMapper pictureMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private TimeLimitMapper timeLimitMapper;

    @Override
    public List<FatherAndChildType> listTypes() {
        List<FatherAndChildType> types = new ArrayList<>();
        List<GoodsType> fTypes = goodsTypeMapper.listFatherTypes();
        for (GoodsType fType : fTypes) {
            List<GoodsType> cTypes = goodsTypeMapper.listChildType(fType.getId());
            FatherAndChildType type = new FatherAndChildType();
            type.setfType(fType);
            type.setcTypes(cTypes);
            types.add(type);
        }
        return types;
    }

    @Override
    public List<Picture> listPictures() {
        return pictureMapper.listAll();
    }

    @Override
    public List<TimeLimit> listTimeLimit() {
        return timeLimitMapper.listAll();
    }

    @Override
    public List<Goods> listGoodsByType(Integer type) {
        return goodsMapper.listByType(type);
    }

    @Override
    public List<Goods> listGoodsByCondition(String condition) {
        if (StringUtils.isEmpty(condition)) {
            return goodsMapper.listAll();
        } else {
            return goodsMapper.listByConditon(condition);
        }
    }

    @Override
    public Goods getGoods(Integer goodsid) {
        return goodsMapper.getById(goodsid);
    }

    @Override
    public GoodsType getGoodsType(Integer type) {
        return goodsTypeMapper.getById(type);
    }

    @Override
    public List<Goods> listGoods() {
        return goodsMapper.listAll();
    }

    @Override
    public String getFatherTypeName(Integer id) {
        return goodsTypeMapper.getFatherTypName(id);
    }

}
