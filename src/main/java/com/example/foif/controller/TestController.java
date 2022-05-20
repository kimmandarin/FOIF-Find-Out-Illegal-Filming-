package com.example.foif.controller;

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
}
