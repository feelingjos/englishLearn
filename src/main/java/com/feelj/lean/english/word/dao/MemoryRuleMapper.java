package com.feelj.lean.english.word.dao;

import com.feelj.lean.english.word.dto.MemoryRuleView;
import com.feelj.lean.english.word.entity.MemoryRule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Auther: feelj
 * @Date: 2019/6/10 12:41
 * @Description:
 */
@Mapper
public interface MemoryRuleMapper {

    /**
     * 批量保存
     * @param data
     */
    void insertByBatch(List<MemoryRule> data);

    /**
     * 指定时间是否存在
     * @param memoryTime  记忆时间
     * @param wordId   单词
     * @return
     */
    List<MemoryRule> isMemoryWord(@Param("memoryTime") Integer memoryTime,@Param("wordId")  Integer wordId);

    /**
     * 记忆单词表
     * @param memoryTime
     * @param keyword
     * @return
     */
    List<MemoryRuleView> findTodayList(@Param("memoryTime") Integer memoryTime,@Param("keyword") String keyword);

}
