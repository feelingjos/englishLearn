package com.feelj.lean.english.word.controller;

import com.alibaba.fastjson.JSON;
import com.feelj.lean.english.word.dto.MemoryWordForAndroid;
import com.feelj.lean.english.word.entity.WordEnglish;
import com.feelj.lean.english.word.service.ApiMemoryWordService;
import com.feelj.lean.english.word.service.WordEnglishService;
import com.feelj.lean.english.word.util.ApkUtil;
import net.dongliu.apk.parser.bean.ApkMeta;
import org.apache.ibatis.annotations.Param;
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

    @Autowired
    private WordEnglishService wordEnglishService;

    @PostMapping("/getMemoryWord")
    public List<MemoryWordForAndroid> getTodayMemoryWord(){
        return apiMemoryWordService.getTodayMemoryWord();
    }

    @PostMapping("/saveNewWordForApp")
    public void saveNewWord(@Param("data") String data){
        if(null != data && !"".equals(data)){
            List<WordEnglish> wordEnglishes = JSON.parseArray(data, WordEnglish.class);
            wordEnglishService.insertBatch(wordEnglishes);
        }
    }

    @PostMapping("/getApkVersion")
    public Long getApkVersion(){
        ApkMeta verison = ApkUtil.getVerison();
        return verison.getVersionCode();
    }

}
