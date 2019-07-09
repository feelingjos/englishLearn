package com.feelj.lean.english.word.dto;

import org.apache.ibatis.type.Alias;

/**
 * @Auther: feelj
 * @Date: 2019/6/5 21:49
 * @Description:  新概念英语列表展示
 */
@Alias("wordView")
public class WordView {

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
