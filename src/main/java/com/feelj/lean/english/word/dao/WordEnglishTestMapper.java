package com.feelj.lean.english.word.dao;

import com.feelj.lean.english.word.entity.WordEnglishTest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Auther: feelj
 * @Date: 2019/6/10 18:03
 * @Description:
 */
@Mapper
public interface WordEnglishTestMapper {

    /**
     * 查询单词源
     * @param memoryTime 记忆时间
     * @return
     */
    List<WordEnglishTest> findMemorySource(Integer memoryTime);

    /**
     * 查询新记忆的单词
     * @return
     */
    List<WordEnglishTest> findSourceWord();

    /**
     * 批量更新
     * @param data
     */
    void updateBatch(List<WordEnglishTest> data);

}
