package com.example.foif.controller;

import com.example.foif.algorithm.OpenCVTest;
import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    private static PythonInterpreter interpreter;

    @GetMapping("/pytest")
    public String pytest(){
        System.setProperty("python.import.site", "false");
        interpreter = new PythonInterpreter();
        interpreter.execfile("src/main/python/test.py");
        interpreter.exec("print(testFunc(5,10))");

        PyFunction pyFunction = interpreter.get("testFunc", PyFunction.class);

        int a = 10;
        int b = 20;

        PyObject pyObject =  pyFunction.__call__(new PyInteger(a), new PyInteger(b));
        System.out.println(pyObject.toString());

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
        String str = "C:\\\\Users\\\\PC\\\\Desktop\\\\foif\\\\src\\\\main\\\\resources\\\\static\\\\video\\\\originaltest.mp4";
//
//        PyObject pyObject = pyFunction.__call__(new PyString(str));
//        System.out.println(pyObject.toString());
        OpenCVTest openCVTest = new OpenCVTest();
        openCVTest.doTes(str);

        return "redirect:/homePage";
    }
}
