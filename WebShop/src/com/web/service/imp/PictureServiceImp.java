package com.web.service.imp;

import com.web.bean.Picture;
import com.web.mapper.PictureMapper;
import com.web.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class PictureServiceImp implements PictureService {
    @Autowired
    private PictureMapper pictureMapper;

    @Override
    public List<Picture> list(String title) {
        return StringUtils.isEmpty(title) ? pictureMapper.listAll() : pictureMapper.listByGoodsTitle(title);
    }

    @Override
    public Integer save(Picture picture) {
        return pictureMapper.save(picture);
    }

    @Override
    public Integer delete(Integer id) {
        return pictureMapper.delete(id);
    }

    @Override
    public Picture getPicture(Integer id) {
        return pictureMapper.getById(id);
    }

    @Override
    public Integer update(Picture picture) {
        return pictureMapper.update(picture);
    }

}
