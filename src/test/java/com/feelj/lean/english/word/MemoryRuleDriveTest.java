package com.feelj.lean.english.word;

import com.alibaba.fastjson.util.TypeUtils;
import com.feelj.lean.english.word.core.DateOptionsUtils;
import com.feelj.lean.english.word.dao.MemoryRuleMapper;
import com.feelj.lean.english.word.dao.TestMapper;
import com.feelj.lean.english.word.dao.WordEnglishMapper;
import com.feelj.lean.english.word.entity.MemoryRule;
import com.feelj.lean.english.word.entity.WordEnglish;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: feelj
 * @Date: 2019/6/9 21:41
 * @Description:  记忆生成规则启动
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EnglishwordApplication.class)
@EnableAutoConfiguration
public class MemoryRuleDriveTest {

    @Resource
    private WordEnglishMapper wordEnglishMapper;

    @Resource
    private MemoryRuleMapper memoryRuleMapper;

    @Test
    public void initMemoryRule(){

        Integer day = 10;

        for (int i = 0 ; i < day; i++){

            //日期
            Integer date = DateOptionsUtils.getDate(null,i);

            //查询有记忆时间的word
            List<WordEnglish> source = wordEnglishMapper.findMemorySource(date);

            //新记忆的单词
            List<WordEnglish> sourceWord = wordEnglishMapper.findSourceWord(10);

            source.addAll(sourceWord);

            List<MemoryRule> memoryRules=new ArrayList<>();//新加的

            List<WordEnglish> updateWord=new ArrayList<>();

            for (WordEnglish wordEnglish : source) {
                MemoryRule memoryRule =new MemoryRule();

                memoryRule.setWordId(wordEnglish.getId());

                String[] memory = wordEnglish.getMemoryExpression().split("\\s+");//通过任意空格分割

                //新出现 非当天
                if(null == wordEnglish.getMemoryTime()){

                    //下一次出现
                    Integer days= TypeUtils.castToInt(memory[wordEnglish.getExpressionIndex()]);
                    Integer date1 = DateOptionsUtils.getDate(null, i);//当前时间

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
        }
    }

    @Resource
    private TestMapper testMapper;

    @Test
    public void test1(){
        Integer date = DateOptionsUtils.getDate(null,0);

        System.out.println(date);
    }

}
