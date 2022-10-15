package com.akhmadali.qalampiruz.controller;

import com.akhmadali.qalampiruz.dto.ApiResponse;
import com.akhmadali.qalampiruz.dto.NewsDto;
import com.akhmadali.qalampiruz.entity.News;
import com.akhmadali.qalampiruz.service.impl.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/news")
public class NewsRestControllerV1 {

    @Autowired
    NewsService newsService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR','ROLE_USER')")
    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        List<News> news = newsService.getAll();
        return ResponseEntity.ok(news);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR','ROLE_USER')")
    @GetMapping("/{id}")
    public ResponseEntity<?> news(@PathVariable Integer id) {
        News news = newsService.findById(id);
        return news != null ? ResponseEntity.ok(news) : ResponseEntity.status(HttpStatus.NOT_FOUND).body("News not found");

    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    @PostMapping()
    public ResponseEntity<?> create(@RequestBody NewsDto newsDto) {
        ApiResponse apiResponse = newsService.add(newsDto);
        return apiResponse.getSuccess() ?
                ResponseEntity.ok(apiResponse) : ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse.getMessage());

    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody NewsDto newsDto, @PathVariable Integer id) {
        ApiResponse apiResponse = newsService.update(id, newsDto);

        return apiResponse.getSuccess() ?
                ResponseEntity.ok(apiResponse.getMessage()) : ResponseEntity.status(HttpStatus.NO_CONTENT).body(apiResponse.getMessage());

    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        News news = newsService.delete(id);
        return news != null ?
                ResponseEntity.ok(news) : ResponseEntity.status(HttpStatus.NOT_FOUND).body("News not found");

    }


}
