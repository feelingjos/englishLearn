package com.feelj.lean.english.word.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Auther: feelj
 * @Date: 2019/5/15 16:08
 * @Description:
 */
@RestController
public class DashboardController {

    @GetMapping("/dashboard")
    public ModelAndView IndexDashBoard(){
        return new ModelAndView("/dashboard/dashboard");
    }

}
