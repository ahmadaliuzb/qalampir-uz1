package com.akhmadali.qalampiruz.service.impl;

import com.akhmadali.qalampiruz.dto.ApiResponse;
import com.akhmadali.qalampiruz.dto.UserDto;
import com.akhmadali.qalampiruz.entity.User;
import com.akhmadali.qalampiruz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User register(UserDto userDto);

    List<User> getAll();

    User findByUsername(String username);

    User findById(Integer id);


    User delete(Integer id);

    User update(Integer id, UserDto userDto);
}
