package com.web.service;

import com.web.bean.TimeLimit;

import java.util.List;

public interface TimeLimitService {

    List<TimeLimit> list(String title);

    Integer save(TimeLimit timeLimit);

    Integer delete(Integer id);

    TimeLimit getTimeLimit(Integer id);

    Integer update(TimeLimit timeLimit);

}
