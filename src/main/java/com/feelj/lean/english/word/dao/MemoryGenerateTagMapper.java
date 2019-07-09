package com.feelj.lean.english.word.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Auther: feelj
 * @Date: 2019/7/7 21:43
 * @Description:
 */
@Mapper
public interface MemoryGenerateTagMapper {

    Integer isGenerateTag(@Param("todayTime") Integer todayTime);

    void saveTodayTag(@Param("todayTime") Integer todayTime);

}
