package com.akhmadali.qalampiruz.service.impl;

import com.akhmadali.qalampiruz.dto.ApiResponse;
import com.akhmadali.qalampiruz.dto.NewsDto;
import com.akhmadali.qalampiruz.entity.News;
import com.akhmadali.qalampiruz.entity.User;

import java.util.List;

public interface NewsService {

    ApiResponse add(NewsDto newsDto);

    List<News> getAll();

    News findById(Integer id);


    News delete(Integer id);

    ApiResponse update(Integer id, NewsDto newsDto);
}
