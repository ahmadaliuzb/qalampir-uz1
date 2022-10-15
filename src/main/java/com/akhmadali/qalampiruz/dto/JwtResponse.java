package com.akhmadali.qalampiruz.dto;

import com.akhmadali.qalampiruz.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
    private String jwt;
    private Integer id;
    private String username;
    private String email;
    private List<String> roles;
}
