package com.feelj.lean.english.word.util;

import net.dongliu.apk.parser.ApkFile;
import net.dongliu.apk.parser.bean.ApkMeta;

import java.io.File;
import java.io.IOException;

/**
 * @Auther: feelj
 * @Date: 2019/8/7 22:01
 * @Description:
 */
public class ApkUtil {

    /**
     * 获取版本号
     */
    public static  ApkMeta getVerison(){
        ApkFile apkParser = null;
        try{
            String path = System.getProperty("user.dir") + File.separator + "static\\app-release.apk";
            apkParser = new ApkFile(new File(path));
            ApkMeta apkMeta = apkParser.getApkMeta();
            return apkMeta;
        }catch (Exception ex){
            ex.getMessage();
        }finally {
            if(null != apkParser){
                try {
                    apkParser.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

}
