package com.example.foif.controller;

import com.example.foif.domain.File;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Controller
public class FileController {
    @PostMapping(value = "/upload")
    public String uploadFile(@RequestPart MultipartFile files) throws IOException{
        File file = new File();
        String sourceFileName = files.getOriginalFilename();
//        String sourceFileNameExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase();
//        FilenameUtils.removeExtension(sourceFileName);

        java.io.File destinationFile;
        String destinationFileName;
        String fileUrl = "C:\\Users\\PC\\Desktop\\foif\\src\\main\\resources\\static\\video\\";

        return "redirect:/home";
    }
}
