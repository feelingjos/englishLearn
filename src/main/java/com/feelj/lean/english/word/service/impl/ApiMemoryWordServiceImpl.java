package com.feelj.lean.english.word.service.impl;

import com.feelj.lean.english.word.core.DateOptionsUtils;
import com.feelj.lean.english.word.dao.MemoryRuleMapper;
import com.feelj.lean.english.word.dto.MemoryRuleView;
import com.feelj.lean.english.word.dto.MemoryWordForAndroid;
import com.feelj.lean.english.word.service.ApiMemoryWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: wuhuahao
 * @Date: 2019/7/9 23:20
 * @Description:
 */
@Service
public class ApiMemoryWordServiceImpl implements ApiMemoryWordService {

    @Autowired
    MemoryRuleMapper memoryRuleMapper;

    @Override
    public List<MemoryWordForAndroid> getTodayMemoryWord() {

        //当天日期
        Integer memoryTime = DateOptionsUtils.getDate(null,0);

        List<MemoryRuleView> todayList = memoryRuleMapper.findTodayList(memoryTime, null);

        List<MemoryWordForAndroid> result = todayList.stream().map(memoryRuleView -> {
            MemoryWordForAndroid memoryWordForAndroid = new MemoryWordForAndroid();

            memoryWordForAndroid.setWord(memoryRuleView.getWord());
            memoryWordForAndroid.setType(memoryRuleView.getType());
            memoryWordForAndroid.setPhoneticSymbol(memoryRuleView.getPhoneticSymbol());
            memoryWordForAndroid.setTranslate(memoryRuleView.getTranslate());

            return memoryWordForAndroid;
        }).collect(Collectors.toList());

        return result;
    }
}
