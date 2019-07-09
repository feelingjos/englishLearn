package com.feelj.lean.english.word.dto;

/**
 * @Auther: feelj
 * @Date: 2019/7/9 23:14
 * @Description:   但会到手机端的数据
 */
public class MemoryWordForAndroid {

    //单词
    private String word;

    //词性
    private String type;

    //音标
    private String phoneticSymbol;

    //中文翻译
    private String translate;

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
