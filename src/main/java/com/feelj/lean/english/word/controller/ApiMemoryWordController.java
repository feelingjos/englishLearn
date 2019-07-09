package com.feelj.lean.english.word.controller;

import com.feelj.lean.english.word.dto.MemoryWordForAndroid;
import com.feelj.lean.english.word.service.ApiMemoryWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: feelj
 * @Date: 2019/7/9 23:03
 * @Description:  手机端数据提供接口
 */
@RestController
public class ApiMemoryWordController {

    @Autowired
    private ApiMemoryWordService apiMemoryWordService;

    @PostMapping("/getMemoryWord")
    public List<MemoryWordForAndroid> getTodayMemoryWord(){
        return apiMemoryWordService.getTodayMemoryWord();
    }


}
