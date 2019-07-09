package com.feelj.lean.english.word.common;

import java.util.List;

/**
 * @Auther: feelj
 * @Date: 2019/6/7 22:45
 * @Description:   分页列表数据返回
 */
public class PageListData<T> {

    //总页数
    private Integer pages;

    //数据
    private List<T> data;

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
