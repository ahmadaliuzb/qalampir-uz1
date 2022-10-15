package com.akhmadali.qalampiruz.service;

import com.akhmadali.qalampiruz.dto.ApiResponse;
import com.akhmadali.qalampiruz.dto.CategoryDto;
import com.akhmadali.qalampiruz.entity.Category;
import com.akhmadali.qalampiruz.exceptions.NotFoundException;
import com.akhmadali.qalampiruz.repository.CategoryRepository;
import com.akhmadali.qalampiruz.service.impl.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;


    @Override
    public ApiResponse add(CategoryDto categoryDto) {
        Category category = new Category();
        category.setNameEn(categoryDto.getNameEn());
        category.setNameKiril(categoryDto.getNameKiril());
        category.setNameLatin(categoryDto.getNameLatin());

        if (categoryDto.getCategoryId() != null) {
            Optional<Category> optionalCategory = categoryRepository.findById(categoryDto.getCategoryId());
            if (!optionalCategory.isPresent()) {
                return new ApiResponse("Category not found!!!", false);
            }

            Category category1 = optionalCategory.get();
            category.setCategory(category1);
        }
        categoryRepository.save(category);

        return new ApiResponse("Category saved", true);
    }

    @Override
    public List<Category> getAll() {
        List<Category> categories = categoryRepository.findAll();
        return categories;
    }

    @Override
    public ApiResponse findById(Integer id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        return optionalCategory.map(category -> new ApiResponse(category, true)).orElseGet(() -> new ApiResponse("Category not found", false));
    }

    @Override
    public ApiResponse delete(Integer id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()) {
            return new ApiResponse("Category not found", false);
        }

        categoryRepository.deleteById(id);
        return new ApiResponse(optionalCategory.get(), true);
    }

    @Override
    public ApiResponse update(Integer id, CategoryDto categoryDto) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()) {
            throw new NotFoundException("Category not found");
        }
        Category category = optionalCategory.get();

        if (categoryDto.getNameEn() != null) {
            category.setNameEn(categoryDto.getNameEn());
        }

        if (categoryDto.getNameKiril() != null) {
            category.setNameKiril(categoryDto.getNameKiril());
        }

        if (categoryDto.getNameLatin() != null) {
            category.setNameLatin(categoryDto.getNameLatin());
        }

        if (categoryDto.getCategoryId() != null) {
            Optional<Category> optionalCategory1 = categoryRepository.findById(categoryDto.getCategoryId());
            if (!optionalCategory1.isPresent()) {
                throw new NotFoundException("Category not found");
            }
            Category category1 = optionalCategory1.get();
            category.setCategory(category1);
        }

        categoryRepository.save(category);
        return new ApiResponse(category, true);
    }

}
