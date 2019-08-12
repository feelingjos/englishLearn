package com.feelj.lean.english.word.util;

import net.dongliu.apk.parser.ApkFile;
import net.dongliu.apk.parser.bean.ApkMeta;

import java.io.File;

/**
 * @Auther: feelj
 * @Date: 2019/8/7 22:01
 * @Description:
 */
public class ApkUtil {

    /**
     * 获取版本号
     */
    public static  void getVerison(){
        try{
            ApkFile apkParser = new ApkFile(new File("E:\\elesh\\static\\app-release.apk"));
            String xml = apkParser.getManifestXml();
            System.out.println(xml);
            ApkMeta apkMeta = apkParser.getApkMeta();
            System.out.println(apkMeta);
            apkParser.close();
        }catch (Exception ex){
            ex.getMessage();
        }

    }

}
