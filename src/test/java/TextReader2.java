import com.feelj.lean.english.word.entity.WordEnglish;
import com.feelj.lean.english.word.util.ReadWordUtils;
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
 * @Date: 2019/5/13 16:17
 * @Description:
 */
public class TextReader2 {

    @Test
    public void test1(){
        try {

            FileInputStream fis = new FileInputStream("E:\\new第三册.docx");
            XWPFDocument xdoc = new XWPFDocument(OPCPackage.open(fis));
            XWPFWordExtractor extractor = new XWPFWordExtractor(xdoc);

            //List<String> strings = JSON.parseArray(extractor.getText(), String.class);

            //String[] split1 = extractor.getText().split("Lesson [1-9]\\d*");

            String[] split1 = extractor.getText().replaceAll("Lesson", "##############Lesson").split("##############");

            List<List<WordEnglish>> lesson1 = Arrays.stream(split1).map(xefa -> {

                //System.out.println(xefa);
                if (!xefa.contains("Lesson")) {
                    return null;
                }

                Pattern ptn = Pattern.compile("Lesson [1-9]\\d*");
                Matcher m = ptn.matcher(xefa);
                m.find();
                final String lesson = m.group();

                String[] split = xefa.split("\n");

                List<WordEnglish> collect = Arrays.stream(split).filter(asdfas -> {
                    return asdfas.contains("[") && asdfas.contains("]");
                }).map(asdfwer -> {
                    String s = asdfwer.subSequence(0, 2).toString();
                    Matcher matcher = Pattern.compile("[1-9]\\d* ").matcher(s);
                    if (matcher.find()) {
                        return asdfwer.subSequence(2, asdfwer.length()).toString().trim();
                    }
                    return asdfwer;
                }).map(aswe -> {
                    try {
                        WordEnglish wordenglish = new WordEnglish();

                        Matcher matcher = Pattern.compile("\\[").matcher(aswe);

                        matcher.find();
                        //word
                        String s = aswe.subSequence(0, matcher.start()).toString();
                        //System.out.println(s.trim());//word

                        wordenglish.setWord(s.trim());

                        Matcher ma = Pattern.compile("]").matcher(aswe);

                        ma.find();
                        //System.out.println(ma.start());
                        String trim = aswe.subSequence(matcher.start(), ma.start() + 1).toString().trim();
                        //System.out.println(trim);//phoneticSymbol

                        wordenglish.setPhoneticSymbol(trim);

                        String s1 = aswe.subSequence(ma.start() + 1, aswe.length()).toString().trim();

                        if (s1.contains(".")) {
                            String[] vs = s1.split("\\.");

                            //System.out.println(vs[0].trim() + ".");//词性
                            //System.out.println(vs[1].trim());//translate;

                            wordenglish.setType(vs[0].trim() + ".");
                            wordenglish.setTranslate(vs[1].trim());
                        } else {
                            wordenglish.setTranslate(s1);
                        }
                        wordenglish.setLesson(lesson);
                        return wordenglish;
                    } catch (Exception ex) {
                        WordEnglish wordenglish = new WordEnglish();
                        System.out.println(aswe);
                        return wordenglish;
                    }
                }).collect(Collectors.toList());

                return collect;
            }).filter(xdaee -> null != xdaee).collect(Collectors.toList());



        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void test2(){

        List<WordEnglish> 第一册 = ReadWordUtils.readerWord("第一册");

        System.out.println(第一册);

    }



}
