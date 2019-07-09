package com.feelj.lean.english.word.entity;

import org.apache.ibatis.type.Alias;

/**
 * @Auther: feelj
 * @Date: 2019/5/11 16:37
 * @Description:
 */
@Alias("wordEnglishTest")
public class WordEnglishTest {

    private Integer id;

    //来自那本书
    private String fromBook;

    //第几课时
    private String lesson;

    //单词
    private String word;

    //词性
    private String type;

    //音标
    private String phoneticSymbol;

    //中文翻译
    private String translate;

    //循环次数
    private Integer loopCount;

    //记忆时间
    private Integer memoryTime;

    //记忆记忆规则表达式
    private String memoryExpression;

    //记忆索引
    private Integer expressionIndex;

    public String getMemoryExpression() {
        return memoryExpression;
    }

    public void setMemoryExpression(String memoryExpression) {
        this.memoryExpression = memoryExpression;
    }

    public Integer getExpressionIndex() {
        return expressionIndex;
    }

    public void setExpressionIndex(Integer expressionIndex) {
        this.expressionIndex = expressionIndex;
    }

    public Integer getLoopCount() {
        return loopCount;
    }

    public void setLoopCount(Integer loopCount) {
        this.loopCount = loopCount;
    }

    public Integer getMemoryTime() {
        return memoryTime;
    }

    public void setMemoryTime(Integer memoryTime) {
        this.memoryTime = memoryTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFromBook() {
        return fromBook;
    }

    public void setFromBook(String fromBook) {
        this.fromBook = fromBook;
    }

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhoneticSymbol() {
        return phoneticSymbol;
    }

    public void setPhoneticSymbol(String phoneticSymbol) {
        this.phoneticSymbol = phoneticSymbol;
    }

    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }
}
