package com.akhmadali.qalampiruz.entity;

import com.akhmadali.qalampiruz.template.AbsIntegerEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category extends BaseEntity {

    private String nameKiril;

    private String nameLatin;

    private String nameEn;

    @ManyToOne
    private Category category;

}
