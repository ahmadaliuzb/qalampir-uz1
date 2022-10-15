package com.akhmadali.qalampiruz.dto;

import com.akhmadali.qalampiruz.entity.User;
import com.akhmadali.qalampiruz.enums.ERole;
import com.akhmadali.qalampiruz.enums.Status;
import com.akhmadali.qalampiruz.enums.SystemRoleName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {

    private String username;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private Status status;

    private Set<String> roleNames;

    public static UserDto fromUser(User user) {
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());

        return userDto;
    }


}
