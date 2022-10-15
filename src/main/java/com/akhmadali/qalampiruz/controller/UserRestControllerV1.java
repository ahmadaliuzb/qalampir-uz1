package com.akhmadali.qalampiruz.controller;

import com.akhmadali.qalampiruz.dto.UserDto;
import com.akhmadali.qalampiruz.entity.User;
import com.akhmadali.qalampiruz.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/users")
public class UserRestControllerV1 {
    private final UserService userService;

    @Autowired
    public UserRestControllerV1(UserService userService) {
        this.userService = userService;
    }


    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer id) {
        User user = userService.findById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        UserDto result = UserDto.fromUser(user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    public ResponseEntity<?> getAllUser() {
        List<User> users = userService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(users);

    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDto userDto) {
        User register = userService.register(userDto);
        return register != null ? ResponseEntity.ok(register) : ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("Bunday user mavjud");

    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR','ROLE_USER')")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody UserDto userDto) {
        User update = userService.update(id, userDto);
        return update != null ? ResponseEntity.ok(update) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(userDto);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR','ROLE_USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        User delete = userService.delete(id);
        return delete != null ? ResponseEntity.ok(delete) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(delete);
    }
}
