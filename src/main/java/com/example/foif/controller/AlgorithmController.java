package com.example.foif.controller;

import com.example.foif.algorithm.CompareVideo;
import com.example.foif.algorithm.VideoToImage;
import org.python.util.PythonInterpreter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AlgorithmController {

    @GetMapping("/pytest")
    public String pytest(){
        CompareVideo compareVideo = new CompareVideo();
        compareVideo.compareVideo(183, 183);
        return "redirect:/homePage";
    }

    @GetMapping("/pythontest")
    public String pythonTest(){
        String str = "C:\\Users\\PC\\Desktop\\foif\\src\\main\\resources\\static\\video\\comparetest.mp4";
        VideoToImage videoToImage = new VideoToImage();
        videoToImage.videoToImage(str);
        return "redirect:/homePage";
    }
}
