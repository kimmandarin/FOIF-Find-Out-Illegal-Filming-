package com.example.foif.controller;

import com.example.foif.domain.File;
import com.example.foif.domain.FileDTO;
import com.example.foif.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String uploadFile(@RequestParam(value = "org_v", required = false) MultipartFile file) throws IllegalStateException, IOException{

        if( !file.isEmpty() ) {
            System.out.println("OriginalFilename : " + file.getOriginalFilename());
            System.out.println("ContentType : " + file.getContentType());
            String fullPath = "C:\\Users\\PC\\Desktop\\foif\\src\\main\\resources\\static\\video" + file.getOriginalFilename();
            file.transferTo(new java.io.File(fullPath));

            FileDTO fileDTO = new FileDTO();
            fileDTO.setOriginFileName(file.getOriginalFilename());
            fileDTO.setFullPath(fullPath);

            fileService.joinFIle(fileDTO);
        }

        return "redirect:/homePage";
    }
}
