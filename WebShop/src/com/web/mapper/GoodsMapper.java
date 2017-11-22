package com.web.mapper;

import com.web.bean.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface GoodsMapper {
    Integer save(Goods goods);

    Integer delete(Integer id);

    Integer update(Goods goods);

    Goods getById(Integer id);

    List<Goods> listAll();

    List<Goods> listByConditon(String condition);

    List<Goods> listByType(Integer type);
}
