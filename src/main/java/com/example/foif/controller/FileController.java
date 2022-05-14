package com.example.foif.controller;

import com.example.foif.domain.File;
import com.example.foif.domain.FileDTO;
import com.example.foif.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Controller
public class FileController {

    FileService fileService;

    @Autowired
    public FileController(FileService fileService){
        this.fileService = fileService;
    }

    @GetMapping(value = "/home")
    public String uploadFileModel(Model model){
        System.out.println("Model Test");
        model.addAttribute("file", new File());
        return "home";
    }

    @PostMapping(value = "/home")
    public String uploadFile(@ModelAttribute("file") File file) throws IOException{

        System.out.println("Test");
        System.out.println("파일 이름 : "+file.getFile().getOriginalFilename());
        System.out.println("Test");

        if(file.getFile() != null){
            MultipartFile files = file.getFile();
            String fullPath = "C:\\Users\\PC\\Desktop\\foif\\src\\main\\resources\\static\\video" + files.getOriginalFilename();
            files.transferTo(new java.io.File(fullPath));

            FileDTO fileDTO = new FileDTO();
            fileDTO.setOriginFileName(files.getOriginalFilename());
            fileDTO.setFullPath(fullPath);

            fileService.joinFIle(fileDTO);
        }

        return "redirect:/homePage";
    }
}
