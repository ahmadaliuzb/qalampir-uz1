package com.akhmadali.qalampiruz.entity;

import com.akhmadali.qalampiruz.enums.ERole;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "roles")
@Data
public class Role extends BaseEntity{

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;


}