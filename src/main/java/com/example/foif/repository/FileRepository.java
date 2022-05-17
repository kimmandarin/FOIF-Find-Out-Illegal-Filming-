package com.example.foif.repository;

import com.example.foif.domain.FileDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<FileDTO, Long> {
}
