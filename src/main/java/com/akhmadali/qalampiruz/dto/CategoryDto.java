package com.akhmadali.qalampiruz.dto;


import lombok.Data;
import org.springframework.boot.convert.DataSizeUnit;

import java.util.List;

@Data
public class CategoryDto {
    private String nameKiril;

    private String nameLatin;

    private String nameEn;

    private Integer categoryId;

}
