package com.esioner.neihanreader.utils;

import android.util.Log;

import com.esioner.neihanreader.MyApplication;


public class LogUtil {
    public static int VERBOSE = 0;
    public static int DEBUG = 1;
    public static int INFO = 2;
    public static int WARM = 3;
    public static int ERROR = 4;
    private static int LOGTYPE = 0;

    public static void v(String Tag, String msg) {
        if (LOGTYPE <= VERBOSE) {
            if (Tag.equals("")) {
                Tag = CommonUtils.utils.getDate();
            }
            Log.v(Tag, msg);
        }
    }

    public static void d(String Tag, String msg) {
        if (LOGTYPE <= DEBUG) {
            if (Tag.equals("")) {
                Tag = CommonUtils.utils.getDate();
            }
            Log.d(Tag, msg);
        }
    }

    public static void i(String Tag, String msg) {
        if (LOGTYPE <= INFO) {
            if (Tag.equals("")) {
                Tag = CommonUtils.utils.getDate();
            }
            Log.i(Tag, msg);
        }
    }

    public static void w(String Tag, String msg) {
        if (LOGTYPE <= WARM) {
            if (Tag.equals("")) {
                Tag = CommonUtils.utils.getDate();
            }
            Log.w(Tag, msg);
        }
    }

    public static void e(String Tag, String msg) {
        if (LOGTYPE <= ERROR) {
            if (Tag.equals("")) {
                Tag = CommonUtils.utils.getDate();
            }
            Log.e(Tag, msg);
        }
    }

}
