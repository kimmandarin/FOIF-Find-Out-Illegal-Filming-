package com.example.foif.service;

import com.example.foif.domain.File;
import com.example.foif.domain.FileDTO;
import com.example.foif.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@Transactional
public class FileService {

    FileRepository fileRepository;

    @Autowired
    public FileService(FileRepository fileRepository){
        this.fileRepository = fileRepository;
    }

    @PersistenceContext
    EntityManager em;

    public void joinFIle(FileDTO fileDTO){
        fileRepository.save(fileDTO);
        em.persist(fileDTO);
    }

    public String fileInfo(Long id){
        FileDTO fileDTO = fileRepository.findById(id).get();
        return fileDTO.getFullPath();
    }
}
