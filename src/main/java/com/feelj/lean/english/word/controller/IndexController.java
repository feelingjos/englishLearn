package com.feelj.lean.english.word.controller;

import com.alibaba.fastjson.JSON;
import com.feelj.lean.english.word.entity.Test;
import com.feelj.lean.english.word.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Auther: feelj
 * @Date: 2019/5/9 09:28
 * @Description:
 */
@RestController
public class IndexController {

    @Autowired
    TestService testService;

    @GetMapping("/index")
    public ModelAndView index(HttpServletRequest request,HttpServletResponse response){

        List<Test> all = testService.findAll();

        String s = JSON.toJSONString(all);

        request.setAttribute("test",all.get(0));
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
