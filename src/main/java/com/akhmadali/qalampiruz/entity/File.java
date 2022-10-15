package com.akhmadali.qalampiruz.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "files")
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String contentType;

    private Long size;

    @Lob
    private byte[] data;

    @ManyToOne(fetch = FetchType.LAZY)
    private News news;

}