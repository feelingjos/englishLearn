package com.feelj.lean.english.word.entity;

/**
 * @Auther: feelj
 * @Date: 2019/7/7 21:41
 * @Description:  标识是否生成进入单词
 */
public class MemoryGenerateTag {

    private Integer id;

    //生成日期
    private Integer todayGanerate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTodayGanerate() {
        return todayGanerate;
    }

    public void setTodayGanerate(Integer todayGanerate) {
        this.todayGanerate = todayGanerate;
    }
}
