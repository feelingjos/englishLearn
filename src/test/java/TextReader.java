import com.feelj.lean.english.word.core.DateOptionsUtils;
import com.feelj.lean.english.word.entity.WordEnglish;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.junit.Test;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @Auther: feelj
 * @Date: 2019/5/11 15:51
 * @Description:
 */
public class TextReader {

    /**
     * 第一册
     */
    @Test
    public void test1(){
        try {
            FileInputStream fis = new FileInputStream("E:\\第一册.docx");
            XWPFDocument xdoc = new XWPFDocument(OPCPackage.open(fis));
            XWPFWordExtractor extractor = new XWPFWordExtractor(xdoc);

            //List<String> strings = JSON.parseArray(extractor.getText(), String.class);

            String[] split = extractor.getText().split("\n");

            List<String> collect = Arrays.stream(split).filter(x -> {
                return x.contains("[") && x.contains("]") || x.contains("Lesson");
            }).map(vul -> {
                if(vul.contains("Lesson")){
                    return vul.trim();
                }
                return vul.subSequence(2,vul.length()).toString().trim();
            }).collect(Collectors.toList());

            final String lesson="";

            List<String> collect2 = Arrays.stream(split).filter(x -> {
                return x.contains("[") && x.contains("]") || x.contains("Lesson");
            }).map(vul -> {
                if (vul.contains("Lesson")) {
                    return vul.trim();
                }
                return vul.subSequence(2, vul.length()).toString().trim();
            }).collect(Collectors.toList());

            System.out.println(collect2);

            List<WordEnglish> collect1 = Arrays.stream(split).filter(x -> {
                return x.contains("[") && x.contains("]") || x.contains("Lesson");
            }).map(vul -> {
                if(vul.contains("Lesson")){
                    return vul.trim();
                }
                return vul.subSequence(2,vul.length()).toString().trim();
            }).map(vlobj -> {
                //String[] sps = vlobj.split("\\\\s+");
                /*String[] sps = vlobj.split(" ");*/

                try{
                    WordEnglish wordenglish = new WordEnglish();

                    Matcher matcher = Pattern.compile("\\[").matcher(vlobj);

                    matcher.find();
                    //word
                    String s = vlobj.subSequence(0, matcher.start()).toString();
                    //System.out.println(s.trim());//word

                    wordenglish.setWord(s.trim());

                    Matcher ma = Pattern.compile("]").matcher(vlobj);

                    ma.find();
                    //System.out.println(ma.start());
                    String trim = vlobj.subSequence(matcher.start(), ma.start() + 1).toString().trim();
                    //System.out.println(trim);//phoneticSymbol

                    wordenglish.setPhoneticSymbol(trim);

                    String s1 = vlobj.subSequence(ma.start() + 1, vlobj.length()).toString().trim();

                    if(s1.contains(".")){
                        String[] vs = s1.split("\\.");

                        //System.out.println(vs[0].trim() + ".");//词性
                        //System.out.println(vs[1].trim());//translate;

                        wordenglish.setType(vs[0].trim() + ".");
                        wordenglish.setTranslate(vs[1].trim());
                    }else{
                        wordenglish.setTranslate(s1);
                    }

                    return wordenglish;
                }catch (Exception ex){
                    WordEnglish wordenglish = new WordEnglish();
                    System.out.println(vlobj);
                    return wordenglish;
                }

            }).collect(Collectors.toList());

            collect1.forEach(x ->{

            });

            collect.forEach(str ->{
                //System.out.println(str);
            });

            //System.out.println(extractor.getText());
        } catch(Exception ex) {
            ex.printStackTrace();
        }

    }

    @Test
    public void test2(){

        String wo="fail [feil] v.未及格，失败";


        Matcher matcher= Pattern.compile("\\[").matcher(wo);

        if(matcher.find()){

            System.out.println(matcher.group());

        }

        /*String[] word = wo.split(" ");

        *//*for (String value: word) {
            System.out.println(value);
        }*//*
        
        String wod="v.原谅";

        String[] split = wod.split("\\.");

        System.out.println(split[0]);*/

    }

    @Test
    public void test3(){
        String wo="fail [feil] v.未及格，失败";

        Matcher matcher= Pattern.compile("\\[").matcher(wo);

        matcher.find();
        //word
        String s = wo.subSequence(0, matcher.start()).toString();
        System.out.println(s.trim());//word

        Matcher ma=Pattern.compile("]").matcher(wo);

        ma.find();
        String trim = wo.subSequence(matcher.start(), ma.start()+1).toString().trim();
        System.out.println(trim);

        String s1 = wo.subSequence(ma.start() + 1, wo.length()).toString();
        String[] split = s1.split("\\.");

        System.out.println(split[0].trim()+".");
        System.out.println(split[1].trim());

    }

    @Test
    public void test4(){

        String str="asdfasf.asdfas";

        boolean contains = str.contains(".");
        System.out.println(contains);

    }

    @Test
    public void test5(){
        try {
            FileInputStream fis = new FileInputStream("E:\\new第二册.docx");
            XWPFDocument xdoc = new XWPFDocument(OPCPackage.open(fis));
            XWPFWordExtractor extractor = new XWPFWordExtractor(xdoc);

            //List<String> strings = JSON.parseArray(extractor.getText(), String.class);

            String[] split = extractor.getText().split("\n");

            List<String> collect = Arrays.stream(split).filter(x -> {
                return x.contains("[") && x.contains("]");
            }).map(vul -> vul.subSequence(2,vul.length()).toString()).collect(Collectors.toList());

            List<WordEnglish> collect1 = Arrays.stream(split).filter(x -> {
                return x.contains("[") && x.contains("]");
            }).map(vul -> vul.subSequence(2, vul.length()).toString())
                    .map(vlobj -> {
                        //String[] sps = vlobj.split("\\\\s+");
                        /*String[] sps = vlobj.split(" ");*/
                        try{
                            WordEnglish wordenglish = new WordEnglish();

                            Matcher matcher = Pattern.compile("\\[").matcher(vlobj);

                            matcher.find();
                            //word
                            String s = vlobj.subSequence(0, matcher.start()).toString();
                            //System.out.println(s.trim());//word

                            wordenglish.setWord(s.trim());

                            Matcher ma = Pattern.compile("]").matcher(vlobj);

                            ma.find();
                            //System.out.println(ma.start());
                            String trim = vlobj.subSequence(matcher.start(), ma.start() + 1).toString().trim();
                            //System.out.println(trim);//phoneticSymbol

                            wordenglish.setPhoneticSymbol(trim);

                            String s1 = vlobj.subSequence(ma.start() + 1, vlobj.length()).toString().trim();

                            if(s1.contains(".")){
                                String[] vs = s1.split("\\.");

                                //System.out.println(vs[0].trim() + ".");//词性
                                //System.out.println(vs[1].trim());//translate;

                                wordenglish.setType(vs[0].trim() + ".");
                                wordenglish.setTranslate(vs[1].trim());
                            }else{
                                wordenglish.setTranslate(s1);
                            }

                            return wordenglish;
                        }catch (Exception ex){
                            WordEnglish wordenglish = new WordEnglish();
                            System.out.println(vlobj);
                            return wordenglish;
                        }

                    }).collect(Collectors.toList());

            collect1.forEach(x ->{

            });

            //System.out.println(extractor.getText());
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void test6(){
        try {
            FileInputStream fis = new FileInputStream("E:\\new第三册.docx");
            XWPFDocument xdoc = new XWPFDocument(OPCPackage.open(fis));
            XWPFWordExtractor extractor = new XWPFWordExtractor(xdoc);

            //List<String> strings = JSON.parseArray(extractor.getText(), String.class);

            String[] split = extractor.getText().split("\n");

            List<String> collect = Arrays.stream(split).filter(x -> {
                return x.contains("[") && x.contains("]");
            }).collect(Collectors.toList());

            List<WordEnglish> collect1 = Arrays.stream(split).filter(x -> {
                return x.contains("[") && x.contains("]");
            }).map(vlobj -> {
                //String[] sps = vlobj.split("\\\\s+");
                /*String[] sps = vlobj.split(" ");*/
                try{
                    WordEnglish wordenglish = new WordEnglish();

                    Matcher matcher = Pattern.compile("\\[").matcher(vlobj);

                    matcher.find();
                    //word
                    String s = vlobj.subSequence(0, matcher.start()).toString();
                    //System.out.println(s.trim());//word

                    wordenglish.setWord(s.trim());

                    Matcher ma = Pattern.compile("]").matcher(vlobj);

                    ma.find();
                    //System.out.println(ma.start());
                    String trim = vlobj.subSequence(matcher.start(), ma.start() + 1).toString().trim();
                    //System.out.println(trim);//phoneticSymbol

                    wordenglish.setPhoneticSymbol(trim);

                    String s1 = vlobj.subSequence(ma.start() + 1, vlobj.length()).toString().trim();

                    if(s1.contains(".")){
                        String[] vs = s1.split("\\.");

                        //System.out.println(vs[0].trim() + ".");//词性
                        //System.out.println(vs[1].trim());//translate;

                        wordenglish.setType(vs[0].trim() + ".");
                        wordenglish.setTranslate(vs[1].trim());
                    }else{
                        wordenglish.setTranslate(s1);
                    }

                    return wordenglish;
                }catch (Exception ex){
                    WordEnglish wordenglish = new WordEnglish();
                    System.out.println(vlobj);
                    return wordenglish;
                }

            }).collect(Collectors.toList());

            collect1.forEach(x ->{

            });

            //System.out.println(extractor.getText());
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void test7(){
        try {
            FileInputStream fis = new FileInputStream("E:\\new第四册.docx");
            XWPFDocument xdoc = new XWPFDocument(OPCPackage.open(fis));
            XWPFWordExtractor extractor = new XWPFWordExtractor(xdoc);

            //List<String> strings = JSON.parseArray(extractor.getText(), String.class);

            String[] split = extractor.getText().split("\n");

            List<String> collect = Arrays.stream(split).filter(x -> {
                return x.contains("[") && x.contains("]");
            }).collect(Collectors.toList());

            List<WordEnglish> collect1 = Arrays.stream(split).filter(x -> {
                return x.contains("[") && x.contains("]");
            }).map(vlobj -> {
                //String[] sps = vlobj.split("\\\\s+");
                /*String[] sps = vlobj.split(" ");*/
                try{
                    WordEnglish wordenglish = new WordEnglish();

                    Matcher matcher = Pattern.compile("\\[").matcher(vlobj);

                    matcher.find();
                    //word
                    String s = vlobj.subSequence(0, matcher.start()).toString();
                    //System.out.println(s.trim());//word

                    wordenglish.setWord(s.trim());

                    Matcher ma = Pattern.compile("]").matcher(vlobj);

                    ma.find();
                    //System.out.println(ma.start());
                    String trim = vlobj.subSequence(matcher.start(), ma.start() + 1).toString().trim();
                    //System.out.println(trim);//phoneticSymbol

                    wordenglish.setPhoneticSymbol(trim);

                    String s1 = vlobj.subSequence(ma.start() + 1, vlobj.length()).toString().trim();

                    if(s1.contains(".")){
                        String[] vs = s1.split("\\.");

                        //System.out.println(vs[0].trim() + ".");//词性
                        //System.out.println(vs[1].trim());//translate;

                        wordenglish.setType(vs[0].trim() + ".");
                        wordenglish.setTranslate(vs[1].trim());
                    }else{
                        wordenglish.setTranslate(s1);
                    }

                    return wordenglish;
                }catch (Exception ex){
                    WordEnglish wordenglish = new WordEnglish();
                    System.out.println(vlobj);
                    return wordenglish;
                }

            }).collect(Collectors.toList());

            collect1.forEach(x ->{

            });

            //System.out.println(extractor.getText());
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void test8(){
        Integer date = DateOptionsUtils.getDate(null, 0);

        System.out.println(date);

    }


}
