package com.feelj.lean.english.word.entity;

import org.apache.ibatis.type.Alias;
import sun.awt.image.IntegerInterleavedRaster;

/**
 * @Auther: feelj
 * @Date: 2019/6/9 21:24
 * @Description:  记忆时间表
 */
@Alias("memoryRule")
public class MemoryRule {

    //id
    private Integer id;

    //单词id
    private Integer wordId;

    //记忆时间
    private Integer apperTime;

    //下次出现时间
    private Integer nextReview;

    //下次出现周期
    private Integer nextReviewCount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWordId() {
        return wordId;
    }

    public void setWordId(Integer wordId) {
        this.wordId = wordId;
    }

    public Integer getApperTime() {
        return apperTime;
    }

    public void setApperTime(Integer apperTime) {
        this.apperTime = apperTime;
    }

    public Integer getNextReview() {
        return nextReview;
    }

    public void setNextReview(Integer nextReview) {
        this.nextReview = nextReview;
    }

    public Integer getNextReviewCount() {
        return nextReviewCount;
    }

    public void setNextReviewCount(Integer nextReviewCount) {
        this.nextReviewCount = nextReviewCount;
    }
}
