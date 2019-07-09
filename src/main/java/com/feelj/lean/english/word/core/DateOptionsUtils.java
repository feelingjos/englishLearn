package com.feelj.lean.english.word.core;

import com.alibaba.fastjson.util.TypeUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Auther: feelj
 * @Date: 2019/6/10 13:19
 * @Description:
 */
public class DateOptionsUtils {


    /**
     * 获取日期
     * @param range 往后天数
     * @return
     */
    public static Integer getDate(String date,Integer range){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(new Date());

        if(null != date && !"".equals(date)){
            try {
                Date parse = dateFormat.parse(date);
                calendar.setTime(parse);
            }catch (Exception ex){
                ex.getStackTrace();
            }
        }
        calendar.add(calendar.DATE,range);

        return TypeUtils.castToInt(dateFormat.format(calendar.getTime()));
    }

}
