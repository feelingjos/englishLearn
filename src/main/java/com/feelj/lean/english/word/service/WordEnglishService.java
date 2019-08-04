package com.feelj.lean.english.word.service;

import com.feelj.lean.english.word.common.PageListData;
import com.feelj.lean.english.word.dto.WordView;
import com.feelj.lean.english.word.entity.WordEnglish;

import java.util.List;

/**
 * @Auther: feelj
 * @Date: 2019/5/12 14:48
 * @Description:
 */
public interface WordEnglishService {

    /**
     * 插入数据
     * @param wordEnglish
     * @return
     */
    Integer insert(WordEnglish wordEnglish);

    /**
     * 分页查询 测试
     * @param page
     * @param rows
     * @return
     */
    List<WordEnglish> find(Integer page,Integer rows);


    /**
     * 分页查找列表
     * @param pageNum
     * @param pageSize
     * @param keyword
     * @return
     */
    PageListData<WordView> findList(Integer pageNum, Integer pageSize,String keyword);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    WordView getById(Integer id);

    /**
     * 保存
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
    void deleteById(Integer id);

    /**
     * 批量保存
     * @param data
     */
    void insertBatch(List<WordEnglish> data);

}
