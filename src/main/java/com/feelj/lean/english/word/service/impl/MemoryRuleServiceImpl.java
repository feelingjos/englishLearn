package com.feelj.lean.english.word.service.impl;

import com.alibaba.fastjson.util.TypeUtils;
import com.feelj.lean.english.word.common.PageListData;
import com.feelj.lean.english.word.core.DateOptionsUtils;
import com.feelj.lean.english.word.dao.MemoryGenerateTagMapper;
import com.feelj.lean.english.word.dao.MemoryRuleMapper;
import com.feelj.lean.english.word.dao.WordEnglishMapper;
import com.feelj.lean.english.word.dto.MemoryRuleView;
import com.feelj.lean.english.word.entity.MemoryRule;
import com.feelj.lean.english.word.entity.WordEnglish;
import com.feelj.lean.english.word.service.MemoryRuleService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: feelj
 * @Date: 2019/6/12 22:43
 * @Description:
 */
@Service
public class MemoryRuleServiceImpl implements MemoryRuleService{

    @Autowired
    private MemoryRuleMapper memoryRuleMapper;

    @Autowired
    private WordEnglishMapper wordEnglishMapper;

    @Autowired
    private MemoryGenerateTagMapper memoryGenerateTagMapper;

    @Override
    public PageListData<MemoryRuleView> findList(String keyword, Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum,pageSize);
        if(null != keyword && !"".equals(keyword)){
            keyword = keyword.replaceAll("", "%");
        }

        Page<MemoryRuleView> todayList =(Page<MemoryRuleView>) memoryRuleMapper.findTodayList(DateOptionsUtils.getDate(null, 0),keyword);

        PageListData<MemoryRuleView> result=new PageListData<>();
        result.setData(todayList);
        result.setPages(todayList.getPages());

        return result;
    }

    @Override
    @Transactional
    public void generateMemoryWord(Integer newLimit) {

        //日期
        Integer date = DateOptionsUtils.getDate(null,0);

        Integer isGenerateTag = memoryGenerateTagMapper.isGenerateTag(date);

        if(null != isGenerateTag){
            return;
        }

        //查询有记忆时间的word
        List<WordEnglish> source = wordEnglishMapper.findMemorySource(date);

        //新记忆的单词
        List<WordEnglish> sourceWord = wordEnglishMapper.findSourceWord(newLimit);

        source.addAll(sourceWord);

        List<MemoryRule> memoryRules=new ArrayList<>();//新加的

        List<WordEnglish> updateWord=new ArrayList<>();//源数据中的记录和规则

        for (WordEnglish wordEnglish : source) {

            MemoryRule memoryRule =new MemoryRule();

            memoryRule.setWordId(wordEnglish.getId());

            String[] memory = wordEnglish.getMemoryExpression().split("\\s+");//通过任意空格分割

            //新出现 非当天
            if(null == wordEnglish.getMemoryTime()){

                //下一次出现
                Integer days= TypeUtils.castToInt(memory[wordEnglish.getExpressionIndex()]);
                Integer date1 = DateOptionsUtils.getDate(null, 0);//当前时间

                memoryRule.setApperTime(date1);//新加入的记忆
                memoryRule.setNextReview(DateOptionsUtils.getDate(TypeUtils.castToString(date1),days));//下一次出现

            }else if(null != wordEnglish.getMemoryTime()){

                //下一次出息天数
                Integer days= TypeUtils.castToInt(memory[wordEnglish.getExpressionIndex()]);

                //下一次出现的天数
                Integer date1 = DateOptionsUtils.getDate(TypeUtils.castToString(wordEnglish.getMemoryTime()), days);

                memoryRule.setApperTime(wordEnglish.getMemoryTime());
                memoryRule.setNextReview(date1);
            }else {
                throw new RuntimeException("MemoryTime 记忆时间异常 " + wordEnglish.getId());
            }

            List<MemoryRule> memoryWord = memoryRuleMapper.isMemoryWord(memoryRule.getApperTime(), memoryRule.getWordId());

            if(!memoryWord.isEmpty()){//单词不可重复
                throw new RuntimeException("重复");
            }

            memoryRules.add(memoryRule);

            Integer cout= wordEnglish.getExpressionIndex() + 1;

            if(cout  >= memory.length){
                cout = 0;
            }
            wordEnglish.setExpressionIndex(cout);
            wordEnglish.setMemoryTime(memoryRule.getNextReview());
            updateWord.add(wordEnglish);
        }
        memoryRuleMapper.insertByBatch(memoryRules);
        wordEnglishMapper.updateBatch(updateWord);

        //保存记忆标识
        memoryGenerateTagMapper.saveTodayTag(date);

    }

    @Override
    public Integer isgenerateTodayMemoryWord() {
        Integer date = DateOptionsUtils.getDate(null,0);
        return memoryGenerateTagMapper.isGenerateTag(date);

    }
}
