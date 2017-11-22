package com.web.service.imp;

import com.web.bean.TimeLimit;
import com.web.mapper.TimeLimitMapper;
import com.web.service.TimeLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class TimeLimitServiceImp implements TimeLimitService {
    @Autowired
    private TimeLimitMapper timeLimitMapper;

    @Override
    public List<TimeLimit> list(String title) {
        return StringUtils.isEmpty(title) ? timeLimitMapper.listAll() : timeLimitMapper.listByGoodsTitle(title);
    }

    @Override
    public Integer save(TimeLimit timeLimit) {
        return timeLimitMapper.save(timeLimit);
    }

    @Override
    public Integer delete(Integer id) {
        return timeLimitMapper.delete(id);
    }

    @Override
    public TimeLimit getTimeLimit(Integer id) {
        return timeLimitMapper.getById(id);
    }

    @Override
    public Integer update(TimeLimit timeLimit) {
        return timeLimitMapper.update(timeLimit);
    }

}
