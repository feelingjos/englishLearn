package com.feelj.lean.english.word.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.util.TypeUtils;
import com.feelj.lean.english.word.common.PageListData;
import com.feelj.lean.english.word.common.RequestHolder;
import com.feelj.lean.english.word.common.ResponseViewBody;
import com.feelj.lean.english.word.dto.WordView;
import com.feelj.lean.english.word.entity.WordEnglish;
import com.feelj.lean.english.word.service.WordEnglishService;
import com.feelj.lean.english.word.util.ReadWordUtils;
import com.github.pagehelper.PageInfo;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @Auther: feelj
 * @Date: 2019/5/12 14:47
 * @Description:
 */
@RestController
public class WordEnglishController {

    @Autowired
    WordEnglishService wordEnglishService;

    @GetMapping("/IndexTest")
    public Integer IndexTest(){

        WordEnglish wordEnglish=new WordEnglish();

        wordEnglish.setWord("asdfasf");
        wordEnglish.setType("asdfa");
        wordEnglish.setTranslate("asfdafs");

        return wordEnglishService.insert(wordEnglish);
    }

    @GetMapping("/readerWordNewconceptEnglish")
    public String readerWordNewconceptEnglish(){

        List<WordEnglish> wordEnglishesone = ReadWordUtils.readerWord("第一册");
        List<WordEnglish> wordEnglishestwo = ReadWordUtils.readerWord("第二册");
        List<WordEnglish> wordEnglishesthree = ReadWordUtils.readerWord("第三册");
        List<WordEnglish> wordEnglishesfour = ReadWordUtils.readerWord("第四册");

        for (WordEnglish list:wordEnglishesone) {
            //wordEnglishService.insert(list);
        }
        for (WordEnglish list:wordEnglishestwo) {
            //wordEnglishService.insert(list);
        }
        for (WordEnglish list:wordEnglishesthree) {
            //wordEnglishService.insert(list);
        }
        for (WordEnglish list:wordEnglishesfour) {
           // wordEnglishService.insert(list);
        }

        return "success";
    }


    @GetMapping("/find")
    public String find(Integer page,Integer rows){

        List<WordEnglish> wordEnglishes = wordEnglishService.find(page, rows);


        String s = JSON.toJSONString(wordEnglishes);

        return s;
    }

    @GetMapping("/wordDasboard")
    public ModelAndView wordDasboard(){
        return new ModelAndView("/wordList/wordList");
    }

    @GetMapping("/wordList")
    public PageListData<WordView> listPage(String keyword,Integer pageNum, Integer pageSize){

        return wordEnglishService.findList(pageNum, pageSize,keyword);
    }

    @GetMapping("/editword")
    public ModelAndView editword(Integer id){
        if(null != id ){

            WordView wordView = wordEnglishService.getById(id);

            RequestHolder.getRequest().setAttribute("wordView",wordView);
        }
        return new ModelAndView("/edit/addWord");
    }

    @PostMapping("/saveWord")
    public ResponseViewBody saveWord(WordView wordView){
        ResponseViewBody result=new ResponseViewBody();
        if(null != wordView.getId()){
            wordEnglishService.updateWord(wordView);
            result.setMsg("编辑成功");
        }else{
            wordEnglishService.saveWord(wordView);
            result.setMsg("添加成功");
        }
        return result;
    }

    @PostMapping("/deleteWordById")
    public ResponseViewBody deleteWordById(Integer id){
        ResponseViewBody result=new ResponseViewBody();
        wordEnglishService.deleteById(id);
        result.setMsg("删除成功");
        return result;
    }



}
