package com.web.mapper;

import com.web.bean.GoodsType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface GoodsTypeMapper {
    Integer save(GoodsType goodType);

    Integer delete(Integer id);

    Integer update(GoodsType goodsType);

    GoodsType getById(Integer id);

    List<GoodsType> listAll();

    List<GoodsType> listByName(String name);

    List<GoodsType> listFatherTypes();

    List<GoodsType> listChildType(Integer fatherid);

    String getFatherTypName(Integer id);
}
