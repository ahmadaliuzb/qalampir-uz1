package com.akhmadali.qalampiruz.service.impl;

import com.akhmadali.qalampiruz.dto.ApiResponse;
import com.akhmadali.qalampiruz.dto.CategoryDto;
import com.akhmadali.qalampiruz.dto.NewsDto;
import com.akhmadali.qalampiruz.entity.Category;
import com.akhmadali.qalampiruz.entity.News;

import java.util.List;

public interface CategoryService {

    ApiResponse add(CategoryDto categoryDto);

    List<Category> getAll();

    ApiResponse findById(Integer id);


    ApiResponse delete(Integer id);

    ApiResponse update(Integer id, CategoryDto categoryDto);
}
