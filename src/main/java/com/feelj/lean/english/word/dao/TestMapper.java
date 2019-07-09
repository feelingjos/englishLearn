package com.feelj.lean.english.word.dao;

import com.feelj.lean.english.word.entity.Test;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Auther: feelj
 * @Date: 2019/5/9 23:18
 * @Description:
 */
@Mapper
public interface TestMapper {

    List<Test> findAll();

}
