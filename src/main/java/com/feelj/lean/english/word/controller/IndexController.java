package com.feelj.lean.english.word.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * @Auther: feelj
 * @Date: 2019/5/9 09:28
 * @Description:
 */
@RestController
public class IndexController {

    @GetMapping("/index")
    public ModelAndView index(HttpServletRequest request,HttpServletResponse response){

        return new ModelAndView("index");
    }

    @GetMapping("/uploadWord")
    public String testSoWord(){

        return "读取成功";
    }

    @GetMapping("/androidConnection")
    public String androidConnection(String name){

        return name+"连接成功";
    }

}
