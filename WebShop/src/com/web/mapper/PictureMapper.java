package com.web.mapper;

import com.web.bean.Picture;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PictureMapper {
    Integer save(Picture picture);

    Integer delete(Integer id);

    Integer update(Picture picture);

    Picture getById(Integer id);

    List<Picture> listAll();

    List<Picture> listByGoodsTitle(String title);
}
