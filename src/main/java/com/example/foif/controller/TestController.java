package com.example.foif.controller;

import com.example.foif.algorithm.CompareVideo;
import com.example.foif.algorithm.VideoToImage;
import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    private static PythonInterpreter interpreter;

    @GetMapping("/pytest")
    public String pytest(){
        CompareVideo compareVideo = new CompareVideo();
        compareVideo.compareVideo(183, 183);
        return "redirect:/homePage";
    }

    @GetMapping("/pythontest")
    public String pythonTest(){
//        System.setProperty("python.import.site", "false");
//        interpreter = new PythonInterpreter();
//        interpreter.exec("import sys.path.append('C:\\Users\\PC\\AppData\\Local\\Programs\\Python\\Python39\\Lib\\site-packages\\cv2')");
//        interpreter.execfile("src/main/python/Video_To_Image-master/main.py");
//        PyFunction pyFunction = interpreter.get("madeVidocap", PyFunction.class);
//
        String str = "C:\\Users\\PC\\Desktop\\foif\\src\\main\\resources\\static\\video\\comparetest.mp4";
//
//        PyObject pyObject = pyFunction.__call__(new PyString(str));
//        System.out.println(pyObject.toString());
        VideoToImage videoToImage = new VideoToImage();
        videoToImage.videoToImage(str);

        return "redirect:/homePage";
    }
}
