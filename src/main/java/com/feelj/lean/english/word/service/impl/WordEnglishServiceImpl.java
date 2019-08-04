package com.feelj.lean.english.word.service.impl;

import com.feelj.lean.english.word.common.PageListData;
import com.feelj.lean.english.word.dao.WordEnglishMapper;
import com.feelj.lean.english.word.dto.WordView;
import com.feelj.lean.english.word.entity.WordEnglish;
import com.feelj.lean.english.word.service.WordEnglishService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: feelj
 * @Date: 2019/5/12 14:49
 * @Description:
 */
@Service
public class WordEnglishServiceImpl implements WordEnglishService {

    @Autowired
    WordEnglishMapper wordEnglishMapper;

    @Override
    public Integer insert(WordEnglish wordEnglish) {
        return wordEnglishMapper.insert(wordEnglish);
    }

    @Override
    public List<WordEnglish> find(Integer page, Integer rows) {

        PageHelper.startPage(page, rows);

         List<WordEnglish> wordEnglishes = wordEnglishMapper.find();

        PageInfo<WordEnglish> pageInfo = new PageInfo<>(wordEnglishes);

        return pageInfo.getList();
    }

    @Override
    public PageListData<WordView> findList(Integer pageNum, Integer pageSize,String keyword) {
        PageHelper.startPage(pageNum,pageSize);
        if(null != keyword && !"".equals(keyword)){
            keyword = keyword.replaceAll("", "%");
        }
        Page<WordEnglish> list= (Page<WordEnglish>) wordEnglishMapper.findList(keyword);

        PageListData<WordView> result=new PageListData<>();

        result.setPages(list.getPages());

        List<WordView> data = list.stream().map(des -> {
            WordView wordview = new WordView();
            BeanUtils.copyProperties(des, wordview);
            return wordview;
        }).collect(Collectors.toList());

        result.setData(data);

        return result;
    }

    @Override
    public WordView getById(Integer id) {
        WordEnglish wordEnglish = wordEnglishMapper.getById(id);
        WordView result=new WordView();
        BeanUtils.copyProperties(wordEnglish,result);
        return result;
    }

    @Override
    public void saveWord(WordView wordView) {
        wordEnglishMapper.saveWord(wordView);
    }

    @Override
    public void updateWord(WordView wordView) {
        wordEnglishMapper.updateWord(wordView);
    }

    @Override
    public void deleteById(Integer id) {
        wordEnglishMapper.deleteById(id);
    }

    @Override
    public void insertBatch(List<WordEnglish> data) {
        wordEnglishMapper.insertBatch(data);
    }
}
