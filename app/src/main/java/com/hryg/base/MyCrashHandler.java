package com.hryg.base;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;

/**
 * 自定义的 异常处理类 , 实现了 UncaughtExceptionHandler接口
 */
public class MyCrashHandler implements Thread.UncaughtExceptionHandler {
    // 需求是 整个应用程序 只有一个 MyCrash-Handler
    private static MyCrashHandler myCrashHandler;
    private Context context;
    private SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

    //1.私有化构造方法
    private MyCrashHandler() {

    }

    public static synchronized MyCrashHandler getInstance() {
        if (myCrashHandler != null) {
            return myCrashHandler;
        } else {
            myCrashHandler = new MyCrashHandler();
            return myCrashHandler;
        }
    }


    public void uncaughtException(Thread arg0, Throwable arg1) {
        System.out.println("程序挂掉了 ");
        // 1.获取当前程序的版本号. 版本的id
        String versioninfo = getVersionInfo();


        // 3.把错误的堆栈信息 获取出来
        String errorinfo = getErrorInfo(arg1);

        // 4.把所有的信息 还有信息对应的时间 提交到服务器


        //干掉当前的程序
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    /**
     * 获取错误的信息
     *
     * @param arg1
     * @return
     */
    private String getErrorInfo(Throwable arg1) {
        Writer writer = new StringWriter();
        PrintWriter pw = new PrintWriter(writer);
        arg1.printStackTrace(pw);
        pw.close();
        String error = writer.toString();
        return error;
    }


    /**
     * 获取手机的版本信息
     *
     * @return
     */
    private String getVersionInfo() {
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo info = pm.getPackageInfo(context.getPackageName(), 0);
            return info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return "版本号未知";
        }
    }
}
