package com.feelj.lean.english.word.dao;

import com.feelj.lean.english.word.dto.WordView;
import com.feelj.lean.english.word.entity.WordEnglish;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Auther: feelj
 * @Date: 2019/5/12 14:38
 * @Description:
 */
@Mapper
public interface WordEnglishMapper {

    /**
     * 插入基础数据
     * @param wordEnglish
     * @return
     */
    Integer insert(WordEnglish wordEnglish);


    /**
     * 查找
     * @return
     */
    List<WordEnglish> find();

    /**
     * 查询列表
     * @param keyword
     * @return
     */
    List<WordEnglish> findList(@Param(value="keyword")String keyword);


    /**
     * 根据id查询
     * @param id
     * @return
     */
    WordEnglish getById(Integer id);

    /**
     * 添加
     * @param wordView
     */
    void saveWord(WordView wordView);


    /**
     * 更新
     * @param wordView
     */
    void updateWord(WordView wordView);


    /**
     * 根据id删除
     * @param id
     */
    void deleteById(@Param(value="id")Integer id);

    //--------------------记忆记忆算法-------------------------------

    /**
     * 查询单词源
     * @param memoryTime 记忆时间
     * @return
     */
    List<WordEnglish> findMemorySource(Integer memoryTime);

    /**
     * 查询新记忆的单词
     * @return
     */
    List<WordEnglish> findSourceWord(@Param("newLimit") Integer newLimit);

    /**
     * 批量更新
     * @param data
     */
    void updateBatch(List<WordEnglish> data);

    /**
     * 批量保存
     * @param data
     */
    void insertBatch(@Param("data") List<WordEnglish> data);

}
