package com.feelj.lean.english.word.service.impl;

import com.feelj.lean.english.word.dao.TestMapper;
import com.feelj.lean.english.word.entity.Test;
import com.feelj.lean.english.word.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: feelj
 * @Date: 2019/5/9 23:49
 * @Description:
 */
@Service
public class TestServiceImpl implements TestService{

    @Autowired
    TestMapper testMapper;

    @Override
    public List<Test> findAll(){
        return testMapper.findAll();
    }

}
