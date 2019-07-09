package com.feelj.lean.english.word.controller;

import com.feelj.lean.english.word.common.PageListData;
import com.feelj.lean.english.word.dto.MemoryRuleView;
import com.feelj.lean.english.word.service.MemoryRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Auther: feelj
 * @Date: 2019/6/12 22:42
 * @Description:
 */
@RestController
public class MemoryRuleController {

    @Autowired
    private MemoryRuleService memoryRuleService;

    @GetMapping("/memoryRuleDasboard")
    public ModelAndView wordDasboard(){
        return new ModelAndView("/memoryRule/memoryRuleList");
    }

    @GetMapping("/memoryRuleList")
    public PageListData<MemoryRuleView> listPage(String keyword,Integer pageNum, Integer pageSize){
        return  memoryRuleService.findList(keyword,pageNum,pageSize);
    }

    @PostMapping("/generateMemoryWord")
    public String generateMemoryWord(@RequestParam(name = "newLimit",defaultValue = "20") Integer newLimit){
        memoryRuleService.generateMemoryWord(newLimit);
        return "0";
    }

    @PostMapping("/isgenerateTodayMemoryWord")
    public Integer isgenerateTodayMemoryWord(){
        return memoryRuleService.isgenerateTodayMemoryWord();
    }


    /*@RequestMapping("/isgenerateTodayMemoryWord")
    public String isgenerateTodayMemoryWord(){
        Map<String, String[]> parameterMap = RequestUtils.getRequest().getParameterMap();
        String s = JSON.toJSONString(parameterMap);
        return s;
    }*/


}
