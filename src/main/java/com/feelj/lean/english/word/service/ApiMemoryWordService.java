package com.feelj.lean.english.word.service;

import com.feelj.lean.english.word.dto.MemoryWordForAndroid;

import java.util.List;

/**
 * @Auther: wuhuahao
 * @Date: 2019/7/9 23:18
 * @Description:  service
 */
public interface ApiMemoryWordService {

    List<MemoryWordForAndroid> getTodayMemoryWord();

}
