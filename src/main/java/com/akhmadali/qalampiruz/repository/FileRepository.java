package com.akhmadali.qalampiruz.repository;

import com.akhmadali.qalampiruz.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Integer> {
}
