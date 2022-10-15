package com.akhmadali.qalampiruz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {

    @NotNull
    private String username;

    @NotNull
    private String fullName;

    @NotNull
    private String password;
}
