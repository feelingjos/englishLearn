package com.feelj.lean.english.word.dto;

/**
 * @Auther: feelj
 * @Date: 2019/6/12 22:52
 * @Description:  每日背诵单词数据展示
 */
public class MemoryRuleView {

    //记忆id
    private Integer id;

    private Integer wordid;

    private String word;

    private String phoneticSymbol;

    private String type;

    private String translate;

    private Integer memoryTime;

    private Integer nextReview;

    private String memoryExpression;

    private String expressionIndex;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWordid() {
        return wordid;
    }

    public void setWordid(Integer wordid) {
        this.wordid = wordid;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getPhoneticSymbol() {
        return phoneticSymbol;
    }

    public void setPhoneticSymbol(String phoneticSymbol) {
        this.phoneticSymbol = phoneticSymbol;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }

    public Integer getMemoryTime() {
        return memoryTime;
    }

    public void setMemoryTime(Integer memoryTime) {
        this.memoryTime = memoryTime;
    }

    public Integer getNextReview() {
        return nextReview;
    }

    public void setNextReview(Integer nextReview) {
        this.nextReview = nextReview;
    }

    public String getMemoryExpression() {
        return memoryExpression;
    }

    public void setMemoryExpression(String memoryExpression) {
        this.memoryExpression = memoryExpression;
    }

    public String getExpressionIndex() {
        return expressionIndex;
    }

    public void setExpressionIndex(String expressionIndex) {
        this.expressionIndex = expressionIndex;
    }
}
