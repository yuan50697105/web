package com.web.mapper;

import com.web.bean.TimeLimit;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TimeLimitMapper {
    Integer save(TimeLimit timeLimit);

    Integer delete(Integer id);

    Integer update(TimeLimit timeLimit);

    TimeLimit getById(Integer id);

    List<TimeLimit> listAll();

    List<TimeLimit> listByGoodsTitle(String title);
}
