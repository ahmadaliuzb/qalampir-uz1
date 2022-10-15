package com.akhmadali.qalampiruz.dto;

import com.akhmadali.qalampiruz.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.convert.DataSizeUnit;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsDto {
    private String nameLatin;

    private String nameKiril;

    private String nameEn;

    private Integer categoryId;

    private String textLatin;

    private String textKiril;

    private String textEn;

    private Integer createdById;

    private List<Integer> filesId;



}
