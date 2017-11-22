package com.web.service;

import com.web.bean.Picture;

import java.util.List;

public interface PictureService {

    List<Picture> list(String title);

    Integer save(Picture picture);

    Integer delete(Integer id);

    Picture getPicture(Integer id);

    Integer update(Picture picture);

}
