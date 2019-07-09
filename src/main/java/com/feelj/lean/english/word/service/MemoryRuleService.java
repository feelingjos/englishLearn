package com.feelj.lean.english.word.service;

import com.feelj.lean.english.word.common.PageListData;
import com.feelj.lean.english.word.dto.MemoryRuleView;

/**
 * @Auther: feelj
 * @Date: 2019/6/12 22:43
 * @Description:
 */
public interface MemoryRuleService {

    /**
     * 每日单词列表
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageListData<MemoryRuleView> findList(String keyword,Integer pageNum, Integer pageSize);

    /**
     * 生成当天日期背诵词
     * @param newLimit  新单词数量
     */
    void generateMemoryWord(Integer newLimit);

    /**
     * 是否生成了今天的单词
     * @return
     */
    Integer isgenerateTodayMemoryWord();

}
