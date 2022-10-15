package com.akhmadali.qalampiruz.service;

import com.akhmadali.qalampiruz.dto.ApiResponse;
import com.akhmadali.qalampiruz.dto.NewsDto;
import com.akhmadali.qalampiruz.entity.Category;
import com.akhmadali.qalampiruz.entity.File;
import com.akhmadali.qalampiruz.entity.News;
import com.akhmadali.qalampiruz.entity.User;
import com.akhmadali.qalampiruz.repository.CategoryRepository;
import com.akhmadali.qalampiruz.repository.FileRepository;
import com.akhmadali.qalampiruz.repository.NewsRepository;
import com.akhmadali.qalampiruz.repository.UserRepository;
import com.akhmadali.qalampiruz.service.impl.NewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class NewsServiceImpl implements NewsService {

    @Autowired
    NewsRepository newsRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    FileService fileService;

    @Autowired
    FileRepository fileRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public ApiResponse add(NewsDto newsDto) {
        News news = new News();
        news.setNameEn(newsDto.getNameEn());
        news.setNameKiril(newsDto.getNameKiril());
        news.setNameLatin(newsDto.getNameLatin());
        news.setTextEn(newsDto.getTextEn());
        news.setTextKiril(newsDto.getTextKiril());
        news.setTextLatin(newsDto.getTextLatin());

        Optional<Category> optionalCategory = categoryRepository.findById(newsDto.getCategoryId());
        if (!optionalCategory.isPresent()) {
            return new ApiResponse("Category not found!!!", false);
        }

        Optional<User> optionalUser = userRepository.findById(newsDto.getCreatedById());
        if (!optionalUser.isPresent()) {
            return new ApiResponse("User not found!!!", false);
        }

        List<File> allById = fileRepository.findAllById(newsDto.getFilesId());
        if (allById == null) {
            return new ApiResponse("File not found!!!", false);
        }

        news.setCategory(optionalCategory.get());
        news.setCreatedBy(optionalUser.get());
        news.setFiles(allById);
        news.setLikes(0);
        news.setRating(0d);
        news.setViews(0);

        newsRepository.save(news);
        return new ApiResponse(news, true);

    }

    @Override
    public List<News> getAll() {
        List<News> news = newsRepository.findAll();
        return news;
    }

    @Override
    public News findById(Integer id) {
        Optional<News> optionalNews = newsRepository.findById(id);
        return optionalNews.orElse(null);
    }

    @Override
    public News delete(Integer id) {
        Optional<News> optionalNews = newsRepository.findById(id);
        if (optionalNews.isPresent()) {
            newsRepository.deleteById(id);
            return optionalNews.get();
        }
        return null;
    }

    @Override
    public ApiResponse update(Integer id, NewsDto newsDto) {
        Optional<News> optionalNews = newsRepository.findById(id);
        if (!optionalNews.isPresent()) {
            return null;
        }
        News updatingNews = optionalNews.get();
        updatingNews.setNameEn(newsDto.getNameEn());
        updatingNews.setNameKiril(newsDto.getNameKiril());
        updatingNews.setNameLatin(newsDto.getNameLatin());
        updatingNews.setTextEn(newsDto.getTextEn());
        updatingNews.setTextKiril(newsDto.getTextKiril());
        updatingNews.setTextLatin(newsDto.getTextLatin());

        Optional<Category> optionalCategory = categoryRepository.findById(newsDto.getCategoryId());
        if (!optionalCategory.isPresent()) {
            return new ApiResponse("Category not found!!!", false);
        }

        Optional<User> optionalUser = userRepository.findById(newsDto.getCreatedById());
        if (!optionalUser.isPresent()) {
            return new ApiResponse("User not found!!!", false);
        }

        List<File> allById = fileRepository.findAllById(newsDto.getFilesId());
        if (allById == null) {
            return new ApiResponse("File not found!!!", false);
        }

        updatingNews.setCategory(optionalCategory.get());
        updatingNews.setCreatedBy(optionalUser.get());
        updatingNews.setFiles(allById);


        newsRepository.save(updatingNews);
        return new ApiResponse(updatingNews, true);
    }
}
