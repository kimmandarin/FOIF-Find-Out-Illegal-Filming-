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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
        model.addAttribute("file", new File());
        return "home";
    }

    @PostMapping(value = "/home")
    public String uploadFile(@RequestParam(value = "org_v", required = false) MultipartFile file,
                             HttpServletRequest request) throws IllegalStateException, IOException{

        HttpSession session = request.getSession();

        if(!file.isEmpty()) {
            String fullPath = "C:\\Users\\PC\\Desktop\\foif\\src\\main\\resources\\static\\video\\original" + file.getOriginalFilename();
            file.transferTo(new java.io.File(fullPath));

            FileDTO fileDTO = new FileDTO();
            fileDTO.setOriginFileName(file.getOriginalFilename());
            fileDTO.setFullPath(fullPath);

            fileService.joinFIle(fileDTO);

            session.setAttribute("originalId", fileDTO.getId());
        }

        return "redirect:/homePage";
    }

    @PostMapping(value = "/query")
    public String queryUploadFile(@RequestParam(value = "que_v", required = false) MultipartFile file,
                                  HttpServletRequest request) throws IllegalStateException, IOException{

        HttpSession session = request.getSession();

        if(!file.isEmpty()) {
            String fullPath = "C:\\Users\\PC\\Desktop\\foif\\src\\main\\resources\\static\\video\\query" + file.getOriginalFilename();
            file.transferTo(new java.io.File(fullPath));

            FileDTO fileDTO = new FileDTO();
            fileDTO.setOriginFileName(file.getOriginalFilename());
            fileDTO.setFullPath(fullPath);

            fileService.joinFIle(fileDTO);

            session.setAttribute("queryId", fileDTO.getId());
        }

        return "redirect:/homePage";
    }

    @PostMapping(value = "/compare")
    public String compareUploadFile(@RequestParam(value = "com_v", required = false) MultipartFile file,
                                    HttpServletRequest request) throws IllegalStateException, IOException{
        HttpSession session = request.getSession();

        if(!file.isEmpty()) {
            String fullPath = "C:\\Users\\PC\\Desktop\\foif\\src\\main\\resources\\static\\video\\compare" + file.getOriginalFilename();
            file.transferTo(new java.io.File(fullPath));

            FileDTO fileDTO = new FileDTO();
            fileDTO.setOriginFileName(file.getOriginalFilename());
            fileDTO.setFullPath(fullPath);

            fileService.joinFIle(fileDTO);

            session.setAttribute("compareId", fileDTO.getId());

        }

        return "redirect:/homePage";
    }
}
