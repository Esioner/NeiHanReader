package com.esioner.neihanreader;

import android.app.Application;
import android.content.Context;

import com.esioner.neihanreader.utils.LogUtil;
import com.esioner.neihanreader.utils.SPUtils;

import java.util.Random;

/**
 * @author Esioner
 * @date 2017/12/27
 */

public class MyApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        if (SPUtils.getString("iid").equals("")) {
            /**
             * 生成 iid
             */
            String iid = createNum(11);
            SPUtils.putString("iid", iid);
            LogUtil.d("iid", iid);
        }
        if (SPUtils.getString("uuid").equals("")) {
            /**
             * 生成 uuid
             */
            String uuid = createNum(15);
            SPUtils.putString("uuid", uuid);
            LogUtil.d("uuid", uuid);
        }
        if (SPUtils.getString("openuuid").equals("")) {
            /**
             * 生成 openuuid
             */
            String openuuid = createRandomChar();
            SPUtils.putString("openuuid", openuuid);
            LogUtil.d("openuuid", openuuid);
        }
    }

    /**
     * 返回 Application 的上下文环境
     *
     * @return
     */
    public static Context getContext() {
        return mContext;
    }

    /**
     * 生成 iid ： 11 位纯数字
     */
    public String createNum(int b) {
        String s = "";
        for (int i = 0; i < b; i++) {
            int a = (int) (Math.random() * 10);
            s = s + a;
        }
        return s;
    }

    public String createRandomChar() {
        String[] strings = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
                "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "1", "2", "3", "4",
                "5", "6", "7", "8", "9", "0"};
        Random r = new Random();
        String s = "";
        String temp = "";
        for (int i = 0; i < 16; i++) {
            temp = strings[r.nextInt(36)];
            s = s + temp;
        }
        return s;
    }
}
