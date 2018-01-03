package com.esioner.neihanreader.utils;


import android.util.Log;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @author Esioner
 * @date 2017/12/28
 */

public class HttpUtils {
    private OkHttpClient mClient;

    private HttpUtils() {
        if (mClient == null) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    //打印retrofit日志
                    Log.i("OkHttp日志", "Okhttp = " + message);
                }
            });
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            mClient = new OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .build();
        }
    }

    /**
     * 单例获取实例
     *
     * @return
     */
    public static HttpUtils getInstance() {
        return new HttpUtils();
    }

    /**
     * GET 请求
     */
    public void get() {

    }

    /**
     * POST 请求
     */
    public void post(String url, RequestBody formBody, Callback callback) {

        RequestBody requestBody = formBody;
        Request request = new Request.Builder()
                .addHeader("Cookie", "qh[360]=1;uuid=" + SPUtils.getString("uuid") + ";alert_coverage=13;install_id=" + SPUtils.getString("iid") + ";ttreq=1$374b440cc0981ec4b26ba8427300fa2f33e5dbba")
                .url(url)
                .post(requestBody)
                .build();
        mClient.newCall(request).enqueue(callback);
    }

}