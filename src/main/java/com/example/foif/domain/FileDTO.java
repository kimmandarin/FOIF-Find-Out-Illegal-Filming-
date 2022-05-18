package com.example.foif.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
// @javax.persistence.Entity(name="MEMBER")
@Entity
@Table(name="file")
public class FileDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "origin_filename")
    private String originFileName;

    @Column(name = "full_path")
    private String fullPath;

    @Builder
    public FileDTO(Long id, String originFileName, String fullPath) {
        this.id = id;
        this.originFileName = originFileName;
        this.fullPath = fullPath;
    }
}
