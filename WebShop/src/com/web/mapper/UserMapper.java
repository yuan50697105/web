package com.web.mapper;

import com.web.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {
    Integer save(User user);

    Integer delete(Integer id);

    Integer updateInfo(User user);

    Integer updateActiveStatus(String userid);

    User getById(Integer id);

    User getByUserid(String userid);

    User getByUseridAndPassowrd(@Param("userid") String userid, @Param("password") String password);

    List<User> listAll();

    List<User> listByUserid(String userid);

}
