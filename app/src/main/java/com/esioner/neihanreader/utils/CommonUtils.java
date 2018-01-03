package com.esioner.neihanreader.utils;

import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.esioner.neihanreader.MyApplication;

import java.text.SimpleDateFormat;
import java.util.Date;

import static android.content.Context.WINDOW_SERVICE;

/**
 * @author Esioner
 * @date 2017/12/27
 */

public class CommonUtils {
    public static final CommonUtils utils = new CommonUtils();

    //获取 yyyyMMdd 的日期
    public String getDate() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String s = sdf.format(date).trim();
        LogUtil.d("DATE", s);
        return s;
    }

    /**
     * 获取设备型号
     */
    public String getDeviceModel() {
        LogUtil.d("DEVICE_MODEL", Build.MODEL);
        return Build.MODEL;

    }

    /**
     * 获取手机制造商
     */
    public String getManufacturer() {
        LogUtil.d("Manufacturer", Build.MANUFACTURER);
        return Build.MANUFACTURER;
    }

    /**
     * 获取当前 Unix 时间戳
     */
    public long getUnixTime() {
        LogUtil.d("UnixTime", (System.currentTimeMillis()) + "");
        return (System.currentTimeMillis());
    }

    /**
     * 获取屏幕宽度
     */
    public long getScreenWidth() {
        WindowManager wm = (WindowManager) MyApplication.getContext().getSystemService(WINDOW_SERVICE);
        Point point = new Point();
        wm.getDefaultDisplay().getSize(point);
        int height = point.y;

        LogUtil.d("WIDTH*HEIGHT", point.x * point.y + "");

        return point.x;
    }

    /**
     * 获取屏幕高度
     *
     * @return
     */
    public long getScreenHeight() {
        WindowManager wm = (WindowManager) MyApplication.getContext().getSystemService(WINDOW_SERVICE);
        Point point = new Point();
        wm.getDefaultDisplay().getSize(point);
        return point.y;
    }

    //返回设备id
    public String getDeviceId() {
        LogUtil.d("getDeviceId", Build.SERIAL);
        return Build.SERIAL;
    }

    /**
     * 返回版本号
     */
    public String getVersionCode() {
        LogUtil.d("getVersionCode", Build.VERSION.RELEASE);
        return Build.VERSION.RELEASE;
    }

    public int getOSCode() {
        LogUtil.d("getOSCode", Build.VERSION.SDK_INT + "");
        return Build.VERSION.SDK_INT;
    }

    public int getScreenDPI() {
        WindowManager wm = (WindowManager) MyApplication.getContext().getSystemService(WINDOW_SERVICE);
        DisplayMetrics metric = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metric);
        LogUtil.d("getScreenDPI", metric.densityDpi + "");
        return metric.densityDpi;
    }

    /**
     * 去除小数点
     */
    public String removePoint(String str) {
        String s = str.replace(".", "");
        return s;
    }

}
