package com.akhmadali.qalampiruz.service;

import com.akhmadali.qalampiruz.dto.UserDto;
import com.akhmadali.qalampiruz.entity.Role;
import com.akhmadali.qalampiruz.entity.User;
import com.akhmadali.qalampiruz.enums.ERole;
import com.akhmadali.qalampiruz.enums.Status;
import com.akhmadali.qalampiruz.exceptions.NotFoundException;
import com.akhmadali.qalampiruz.repository.RoleRepository;
import com.akhmadali.qalampiruz.repository.UserRepository;
import com.akhmadali.qalampiruz.service.impl.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(UserDto userDto) {

        User user = new User();
        if (userRepository.existsByUsername(userDto.getUsername())) {
            return null;
        }
        if (userRepository.existsByEmail(userDto.getEmail())) {
            return null;
        }

        Role roleUser = roleRepository.findByName(ERole.ROLE_USER).get();
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(roleUser);


        Set<String> roleNamesString = userDto.getRoleNames();
        Set<ERole> roleNames = new HashSet<>();
        roleNames.add(ERole.ROLE_USER);
        for (String s : roleNamesString) {
            switch (s) {
                case "ROLE_MODERATOR":
                    roleNames.add(ERole.ROLE_MODERATOR);
                    break;
                case "ROLE_ADMIN":
                    roleNames.add(ERole.ROLE_ADMIN);
                    break;

            }
        }
        if (!roleNames.isEmpty()) {
            for (ERole roleName : roleNames) {
                Role role = roleRepository.findByName(roleName).get();
                if (role != null) {
                    if (role.getName() != ERole.ROLE_USER) {
                        userRoles.add(role);
                    }
                }
            }

        }
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setRoles(userRoles);
        user.setStatus(Status.ACTIVE);

        User registeredUser = userRepository.save(user);

        log.info("IN register - user: {} successfully registered", registeredUser);

        return registeredUser;
    }

    @Override
    public List<User> getAll() {
        List<User> result = userRepository.findAll();
        log.info("IN getAll - {} users found", result.size());
        return result;
    }

    @Override
    public User findByUsername(String username) {
        Optional<User> result = userRepository.findByUsername(username);
        log.info("IN findByUsername - user: {} found by username: {}", result.get(), username);
        return result.get();
    }

    @Override
    public User update(Integer id, UserDto userDto) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            throw new NotFoundException("User not found");
        }

        User updatingUser = optionalUser.get();

        if (userDto.getFirstName() != null) {
            updatingUser.setFirstName(userDto.getFirstName());
        }

        if (userDto.getLastName() != null) {
            updatingUser.setLastName(userDto.getLastName());
        }

        if (userDto.getUsername() != null) {
            updatingUser.setUsername(userDto.getUsername());
        }

        if (userDto.getPassword() != null) {
            updatingUser.setPassword(userDto.getPassword());
        }

        if (userDto.getStatus() != null) {
            updatingUser.setStatus(updatingUser.getStatus());
        }

        if (userDto.getRoleNames() != null) {
            Role roleUser = roleRepository.findByName(ERole.ROLE_USER).get();
            Set<Role> userRoles = new HashSet<>();
            userRoles.add(roleUser);
            Set<String> roleNamesString = userDto.getRoleNames();
            Set<ERole> roleNames = new HashSet<>();
            roleNames.add(ERole.ROLE_USER);
            for (String s : roleNamesString) {
                switch (s) {
                    case "ROLE_MODERATOR":
                        roleNames.add(ERole.ROLE_MODERATOR);
                        break;
                    case "ROLE_ADMIN":
                        roleNames.add(ERole.ROLE_ADMIN);
                        break;
                    default:
                        break;

                }
            }
            if (!roleNames.isEmpty()) {
                for (ERole roleName : roleNames) {
                    Role role = roleRepository.findByName(roleName).get();
                    if (role != null) {
                        if (role.getName() != ERole.ROLE_USER) {
                            userRoles.add(role);
                        }
                    }
                }

            }
            updatingUser.setRoles(userRoles);
        }

        userRepository.save(updatingUser);

        return updatingUser;
    }

    @Override
    public User findById(Integer id) {
        User result = userRepository.findById(id).orElse(null);

        if (result == null) {
            log.warn("IN findById - no user found by id: {}", id);
            throw new NotFoundException("User not found");
        }

//        log.info("IN findById - user: {} found by id: {}", result);
        return result;
    }

    @Override
    public User delete(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) return null;
        User user = optionalUser.get();
        userRepository.deleteById(id);
        return user;
    }


}
