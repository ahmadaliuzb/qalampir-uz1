package com.akhmadali.qalampiruz.repository;

import com.akhmadali.qalampiruz.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Integer> {

}
