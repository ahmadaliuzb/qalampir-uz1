package com.akhmadali.qalampiruz.entity;

import com.akhmadali.qalampiruz.template.AbsIntegerEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class News extends AbsIntegerEntity {

    private String nameLatin;

    private String nameKiril;

    private String nameEn;

    @ManyToOne
    private Category category;

    private String textLatin;

    private String textKiril;

    private String textEn;

    private Integer views;

    private Integer likes;

    private Double rating;

    @ManyToOne
    private User createdBy;

    @OneToMany(mappedBy = "news", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<File> files;


    public void addFile(File file) {
        this.files.add(file);
        file.setNews(this);
    }

    public void removeFile(File file) {
        this.files.remove(file);
        file.setNews(null);
    }


}
