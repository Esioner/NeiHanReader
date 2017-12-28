package com.esioner.neihanreader;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.esioner.neihanreader.utils.LogUtil;
import com.esioner.neihanreader.utils.SPUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static android.content.Context.WINDOW_SERVICE;

public class _URL {
    /**
     * 获取 content_type
     */
    public static String URL_CONTENT_TYPE = "http://lf.snssdk.com/neihan/service/tabs/?";
    /**
     * 获取推荐
     * 获取视频也是此 URL
     * 获取图片
     * 获取段子
     */
    public static String URL_RECOMMEND = "http://iu.snssdk.com/neihan/stream/mix/v1/?";
    /**
     * 获取段友秀
     */
    public static String URL_FRIEND_SHOW = "http://iu.snssdk.com/neihan/stream/mix/v1/";
    /**
     * 获取直播
     */
    public static String URL_PLAY_LIVE = "http://hotsoon.snssdk.com/hotsoon/feed/";

    //获取 contentType json 需要拼接的内容
    public static String getJointContentTypeParameter() {
        String suffixString =
                "&essence=1" +      //固定值 1
                        "&iid=" + SPUtils.getString("iid") +//一个长度为10的纯数字字符串，用于标识用户唯一性
                        "&device_id=" + getDeviceId() +//设备 id，一个长度为11的纯数字字符串
                        "&ac=wifi" +//网络环境，可取值 wifi
                        "&channel = 360" +//下载渠道，可360、tencent等
                        "&aid = 7" +//固定值7
                        "&app_name=joke_essay" +//固定值joke_essay
                        "&versionCode=" + removePoint(getVersionCode
                        ()) +//版本号去除小数点，例如612
                        "&version_name=" + getVersionCode() +//版本号，例如6.1.2
                        "&device_platform=android" +//设备平台，android 或 ios
                        "&ssmix=a" +//固定值 a
                        "&device_type" + getDeviceModel() +//设备型号，例如 hongmi
                        "&device_brand=" + getManufacturer() +//设备品牌，例如 xiaomi
                        "&os_api=" + getOSCode() +//设备品牌，例如 xiaomi
                        "&os_version=" + getVersionCode() +//操作系统版本号，例如7.1.0
                        "&uuid=" + SPUtils.getString("uuid") +//用户 id，一个长度为15的纯数字字符串
                        "&openudid=" + SPUtils.getString("openudid") +//一个长度为16的数字和小写字母混合字符串
                        "&manifest_version_code=" + removePoint(getVersionCode()) +//版本号去除小数点，例如612
                        "&resolution=" + getScreenHeight() + "*" + getScreenWidth() +//屏幕宽高，例如 1920*1080
                        "&dpi=" + getScreenDPI() +//手机 dpi
                        "&update_version_code=" + removePoint(getVersionCode()) + "0";//版本号去除小数点后乘10，例如6120
        return suffixString;
    }


    public static Map<String, String> getJokeParameter() {
        Map<String, String> map = new HashMap<>();
        map.put("mpic", "1");
        map.put("webp", "1");
        map.put("essence", "1");
        //新增
        map.put("video_cdn_first", "1");

        map.put("content_type", "-102");
        map.put("message_cursor", "-1");
        //新增
        map.put("longitude", "103.999987");
        map.put("latitude", "30.000014");

        map.put("am_longitude", "");
        map.put("am_latitude", "");
        map.put("am_city", "上海");
        map.put("am_loc_time", getUnixTime() + "");
        map.put("count", "20");
        map.put("min_time", (getUnixTime() - 1000) + "");
        map.put("screen_width", getScreenWidth() + "");
        map.put("double_col_mode", "0");

        //新增
        map.put("enable_image_comment", "1");
        map.put("local_request_tag", "1514441532602");

        map.put("iid", SPUtils.getString("iid"));
        map.put("device_id", getDeviceId());
        map.put("ac", "wifi");
        map.put("channel", "360");
        map.put("aid", "7");
        map.put("app_name", "joke_essay");
        //软件版本去小数点670
        map.put("versionCode", removePoint(getVersionCode()));
//        软件版本不去小数点
        map.put("version_name", getVersionCode());

        map.put("device_platform", "android");

        map.put("ssmix", "a");
        //ONEPLUS+A3000
        map.put("device_type", "getDeviceModel()");
        //OnePlus
        map.put("device_brand", "getManufacturer()");
//26
        map.put("os_api", "getOSCode()");
//      8.0.0
        map.put("os_version", getVersionCode());

        map.put("uuid", SPUtils.getString("uuid"));
        map.put("openudid", SPUtils.getString("openudid"));
        //软件版本670
        map.put("manifest_version_code", removePoint(getVersionCode()));
//1080*1920
        map.put("resolution", getScreenHeight() + "*" + getScreenWidth());

        map.put("dpi", getScreenDPI() + "");
//        6703
        map.put("update_version_code", removePoint(getVersionCode()) + "0");
//        13位随机数字
//_rticket=1514441532601
        map.put("_rticket", "1514441532601");
// ts=1514441534
        map.put("ts", "1514441534");
        //   as=a23558243e734a1b44
        map.put("as", "a23558243e734a1b44");
//        cp=833da75de9424cb5e2
        map.put("cp", "833da75de9424cb5e2");
        return map;
    }

    /**
     * 获取段子拼接参数
     *
     * @param count   请求数量
     * @param minTime 上次请求的时间
     * @return
     */
    public static String getJokeJointUrlParameter(String count, String
            minTime) {
        String suffixString = "mpic=1" +
                "webp=1" +          //固定值 1
                "&essence=1" +      //固定值 1
                "&message_cursor=-1" + //固定值-1
                "&am_longitude=110" +//经度。可为空
                "&am_latitude=110" +//纬度。可为空
                "&am_city=%E5%8C%97%E4%BA%AC%E5%B8%82" +//城市名，例如：北京市。可为空
                "&am_loc_time=" + getUnixTime() +//当前时间 Unix 时间戳，毫秒为单位
                "&count=" + count +//返回数量
                "&min_time=" + minTime +//上次更新时间的 Unix 时间戳，秒为单位
                "&screen_width=" + getScreenWidth() +//屏幕宽度，px为单位
                "&double_col_mode=0" +//固定值0
                "&iid=" + SPUtils.getString("iid") +//一个长度为10的纯数字字符串，用于标识用户唯一性
                "&device_id=" + getDeviceId() +//设备 id，一个长度为11的纯数字字符串
                "&ac=wifi" +//网络环境，可取值 wifi
                "&channel=360" +//下载渠道，可360、tencent等
                "&aid=7" +//固定值7
                "&app_name=joke_essay" +//固定值joke_essay
                "&versionCode=" + removePoint(getVersionCode
                ()) +//版本号去除小数点，例如612
                "&version_name=" + getVersionCode() +//版本号，例如6.1.2
                "&device_platform=android" +//设备平台，android 或 ios
                "&ssmix=a" +//固定值 a
                "&device_type=" + getDeviceModel().replace(" ", "") +//设备型号，例如 hongmi
                "&device_brand=" + getManufacturer() +//设备品牌，例如 xiaomi
                "&os_api=" + getOSCode() +//设备品牌，例如 xiaomi
                "&os_version=" + getVersionCode() +//操作系统版本号，例如7.1.0
                "&uuid=" + SPUtils.getString("uuid") +//用户 id，一个长度为15的纯数字字符串
                "&openudid=" + SPUtils.getString("openudid") +//一个长度为16的数字和小写字母混合字符串
                "&manifest_version_code=" + removePoint(getVersionCode()) +//版本号去除小数点，例如612
                "&resolution=" + getScreenHeight() + "*" + getScreenWidth() +//屏幕宽高，例如 1920*1080
                "&dpi=" + getScreenDPI() +//手机 dpi
                "&update_version_code=" + removePoint(getVersionCode()) + "0";//版本号去除小数点后乘10，例如6120
        return suffixString;
    }

    /**
     * 获取图片拼接参数
     *
     * @param count   请求数量
     * @param minTime 上次请求的时间
     * @return
     */
    public static String getImageJointUrlParameter(String count, String
            minTime) {
        String suffixString = "mpic=1" +
                "webp=1" +          //固定值 1
                "&essence=1" +      //固定值 1
                "&message_cursor=-1" + //固定值-1
                "&am_longitude=110" +//经度。可为空
                "&am_latitude=110" +//纬度。可为空
                "&am_city=%E5%8C%97%E4%BA%AC%E5%B8%82" +//城市名，例如：北京市。可为空
                "&am_loc_time=" + getUnixTime() +//当前时间 Unix 时间戳，毫秒为单位
                "&count=" + count +//返回数量
                "&min_time=" + minTime +//上次更新时间的 Unix 时间戳，秒为单位
                "&screen_width=" + getScreenWidth() +//屏幕宽度，px为单位
                "&double_col_mode=0" +//固定值0
                "&iid=" + SPUtils.getString("iid") +//一个长度为10的纯数字字符串，用于标识用户唯一性
                "&device_id=" + getDeviceId() +//设备 id，一个长度为11的纯数字字符串
                "&ac=wifi" +//网络环境，可取值 wifi
                "&channel=360" +//下载渠道，可360、tencent等
                "&aid=7" +//固定值7
                "&app_name=joke_essay" +//固定值joke_essay
                "&versionCode=" + removePoint(getVersionCode
                ()) +//版本号去除小数点，例如612
                "&version_name=" + getVersionCode() +//版本号，例如6.1.2
                "&device_platform=android" +//设备平台，android 或 ios
                "&ssmix=a" +//固定值 a
                "&device_type=" + getDeviceModel().replace(" ", "") +//设备型号，例如 hongmi
                "&device_brand=" + getManufacturer() +//设备品牌，例如 xiaomi
                "&os_api=" + getOSCode() +//设备品牌，例如 xiaomi
                "&os_version=" + getVersionCode() +//操作系统版本号，例如7.1.0
                "&uuid=" + SPUtils.getString("uuid") +//用户 id，一个长度为15的纯数字字符串
                "&openudid=" + SPUtils.getString("openudid") +//一个长度为16的数字和小写字母混合字符串
                "&manifest_version_code=" + removePoint(getVersionCode()) +//版本号去除小数点，例如612
                "&resolution=" + getScreenHeight() + "*" + getScreenWidth() +//屏幕宽高，例如 1920*1080
                "&dpi=" + getScreenDPI() +//手机 dpi
                "&update_version_code=" + removePoint(getVersionCode()) + "0";//版本号去除小数点后乘10，例如6120
        return suffixString;
    }

    /**
     * 获取视频拼接参数
     *
     * @param count   请求数量
     * @param minTime 上次请求的时间
     * @return
     */
    public static String getVideoJointUrlParameter(String count, String
            minTime) {
        String suffixString =
                "mpic=1" +
                        "webp=1" +          //固定值 1
                        "&essence=1" +      //固定值 1
                        "&message_cursor=-1" + //固定值-1
                        "&am_longitude=110" +//经度。可为空
                        "&am_latitude=110" +//纬度。可为空
                        "&am_city=%E5%8C%97%E4%BA%AC%E5%B8%82" +//城市名，例如：北京市。可为空
                        "&am_loc_time=" + getUnixTime() +//当前时间 Unix 时间戳，毫秒为单位
                        "&count=" + count +//返回数量
                        "&min_time=" + minTime +//上次更新时间的 Unix 时间戳，秒为单位
                        "&screen_width=" + getScreenWidth() +//屏幕宽度，px为单位
                        "&double_col_mode=0" +//固定值0
                        "&iid=" + SPUtils.getString("iid") +//一个长度为10的纯数字字符串，用于标识用户唯一性
                        "&device_id=" + getDeviceId() +//设备 id，一个长度为11的纯数字字符串
                        "&ac=wifi" +//网络环境，可取值 wifi
                        "&channel=360" +//下载渠道，可360、tencent等
                        "&aid=7" +//固定值7
                        "&app_name=joke_essay" +//固定值joke_essay
                        "&versionCode=" + removePoint(getVersionCode
                        ()) +//版本号去除小数点，例如612
                        "&version_name=" + getVersionCode() +//版本号，例如6.1.2
                        "&device_platform=android" +//设备平台，android 或 ios
                        "&ssmix=a" +//固定值 a
                        "&device_type=" + getDeviceModel().replace(" ", "") +//设备型号，例如 hongmi
                        "&device_brand=" + getManufacturer() +//设备品牌，例如 xiaomi
                        "&os_api=" + getOSCode() +//设备品牌，例如 xiaomi
                        "&os_version=" + getVersionCode() +//操作系统版本号，例如7.1.0
                        "&uuid=" + SPUtils.getString("uuid") +//用户 id，一个长度为15的纯数字字符串
                        "&openudid=" + SPUtils.getString("openudid") +//一个长度为16的数字和小写字母混合字符串
                        "&manifest_version_code=" + removePoint(getVersionCode()) +//版本号去除小数点，例如612
                        "&resolution=" + getScreenHeight() + "*" + getScreenWidth() +//屏幕宽高，例如 1920*1080
                        "&dpi=" + getScreenDPI() +//手机 dpi
                        "&update_version_code=" + removePoint(getVersionCode()) + "0";//版本号去除小数点后乘10，例如6120
        return suffixString;
    }

    /**
     * 获取推荐拼接参数
     *
     * @param count   请求数量
     * @param minTime 上次请求的时间
     * @return
     */
    public static String getRecommendJointUrlParameter(String count, String
            minTime) {
        String suffixString = "mpic=1" +
                "webp=1" +          //固定值 1
                "&essence=1" +      //固定值 1
                "&message_cursor=-1" + //固定值-1
                "&am_longitude=110" +//经度。可为空
                "&am_latitude=110" +//纬度。可为空
                "&am_city=%E5%8C%97%E4%BA%AC%E5%B8%82" +//城市名，例如：北京市。可为空
                "&am_loc_time=" + getUnixTime() +//当前时间 Unix 时间戳，毫秒为单位
                "&count=" + count +//返回数量
                "&min_time=" + minTime +//上次更新时间的 Unix 时间戳，秒为单位
                "&screen_width=" + getScreenWidth() +//屏幕宽度，px为单位
                "&double_col_mode=0" +//固定值0
                "&iid=" + SPUtils.getString("iid") +//一个长度为10的纯数字字符串，用于标识用户唯一性
                "&device_id=" + getDeviceId() +//设备 id，一个长度为11的纯数字字符串
                "&ac=wifi" +//网络环境，可取值 wifi
                "&channel=360" +//下载渠道，可360、tencent等
                "&aid=7" +//固定值7
                "&app_name=joke_essay" +//固定值joke_essay
                "&versionCode=" + removePoint(getVersionCode
                ()) +//版本号去除小数点，例如612
                "&version_name=" + getVersionCode() +//版本号，例如6.1.2
                "&device_platform=android" +//设备平台，android 或 ios
                "&ssmix=a" +//固定值 a
                "&device_type=" + getDeviceModel().replace(" ", "") +//设备型号，例如 hongmi
                "&device_brand=" + getManufacturer() +//设备品牌，例如 xiaomi
                "&os_api=" + getOSCode() +//设备品牌，例如 xiaomi
                "&os_version=" + getVersionCode() +//操作系统版本号，例如7.1.0
                "&uuid=" + SPUtils.getString("uuid") +//用户 id，一个长度为15的纯数字字符串
                "&openudid=" + SPUtils.getString("openudid") +//一个长度为16的数字和小写字母混合字符串
                "&manifest_version_code=" + removePoint(getVersionCode()) +//版本号去除小数点，例如612
                "&resolution=" + getScreenHeight() + "*" + getScreenWidth() +//屏幕宽高，例如 1920*1080
                "&dpi=" + getScreenDPI() +//手机 dpi
                "&update_version_code=" + removePoint(getVersionCode()) + "0";//版本号去除小数点后乘10，例如6120
        return suffixString;
    }


    /**
     * 拼接字符串
     *
     * @param contentType 从获取 content_type 中获取得到的 list_id 字段值。
     * @param count       返回数量
     * @param minTime     上次更新时间的 Unix 时间戳，秒为单位
     *                    //     * @param iid         一个长度为10的纯数字字符串，用于标识用户唯一性
     *                    //     * @param uuid        用户 id，一个长度为15的纯数字字符串
     *                    //     * @param openudid    一个长度为16的数字和小写字母混合字符串
     * @return
     */
    public static String getJointUrlParameter(String contentType, String count, String minTime) {
        String suffixString = "webp=1" +          //固定值 1
                "&essence=1" +      //固定值 1
                "&content_type=" + contentType +    //从获取 content_type 中获取得到的 list_id 字段值。
                "&message_cursor=-1" + //固定值-1
                "&am_longitude=" +//经度。可为空
                "&am_latitude=" +//纬度。可为空
                "&am_city=" +//城市名，例如：北京市。可为空
                "&am_loc_time=" + getUnixTime() +//当前时间 Unix 时间戳，毫秒为单位
                "&count=" + count +//返回数量
                "&min_time=" + minTime +//上次更新时间的 Unix 时间戳，秒为单位
                "&screen_width=" + getScreenWidth() +//屏幕宽度，px为单位
                "&double_col_mode=0" +//固定值0
                "&iid=" + SPUtils.getString("iid") +//一个长度为10的纯数字字符串，用于标识用户唯一性
                "&device_id=" + getDeviceId() +//设备 id，一个长度为11的纯数字字符串
                "&ac=wifi" +//网络环境，可取值 wifi
                "&channel = 360" +//下载渠道，可360、tencent等
                "&aid = 7" +//固定值7
                "&app_name=joke_essay" +//固定值joke_essay
                "&versionCode=" + removePoint(getVersionCode
                ()) +//版本号去除小数点，例如612
                "&version_name=" + getVersionCode() +//版本号，例如6.1.2
                "&version_name=android" +//设备平台，android 或 ios
                "&ssmix=a" +//固定值 a
                "&device_type" + getDeviceModel() +//设备型号，例如 hongmi
                "&device_brand=" + getManufacturer() +//设备品牌，例如 xiaomi
                "&os_api=" + getOSCode() +//设备品牌，例如 xiaomi
                "&os_version=" + getVersionCode() +//操作系统版本号，例如7.1.0
                "&uuid=" + SPUtils.getString("uuid") +//用户 id，一个长度为15的纯数字字符串
                "&openudid=" + SPUtils.getString("openudid") +//一个长度为16的数字和小写字母混合字符串
                "&manifest_version_code=" + removePoint(getVersionCode()) +//版本号去除小数点，例如612
                "&resolution=" + getScreenHeight() + "*" + getScreenWidth() +//屏幕宽高，例如 1920*1080
                "&dpi=" + getScreenDPI() +//手机 dpi
                "&update_version_code=" + removePoint(getVersionCode()) + "0";//版本号去除小数点后乘10，例如6120
        return suffixString;
    }

    //获取 yyyyMMdd 的日期
    public static String getDate() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String s = sdf.format(date).trim();
        LogUtil.d("DATE", s);
        return s;
    }

    /**
     * 获取设备型号
     */
    public static String getDeviceModel() {
        LogUtil.d("DEVICE_MODEL", Build.MODEL);
        return Build.MODEL;

    }

    /**
     * 获取手机制造商
     */
    public static String getManufacturer() {
        LogUtil.d("Manufacturer", Build.MANUFACTURER);
        return Build.MANUFACTURER;
    }

    /**
     * 获取当前 Unix 时间戳
     */
    public static long getUnixTime() {
        LogUtil.d("UnixTime", (System.currentTimeMillis()) + "");
        return (System.currentTimeMillis());
    }

    /**
     * 获取屏幕宽度
     */
    public static long getScreenWidth() {
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
    public static long getScreenHeight() {
        WindowManager wm = (WindowManager) MyApplication.getContext().getSystemService(WINDOW_SERVICE);
        Point point = new Point();
        wm.getDefaultDisplay().getSize(point);
        return point.y;
    }

    //返回设备id
    public static String getDeviceId() {
        LogUtil.d("getDeviceId", Build.SERIAL);
        return Build.SERIAL;
    }

    /**
     * 返回版本号
     */
    public static String getVersionCode() {
        LogUtil.d("getVersionCode", Build.VERSION.RELEASE);
        return Build.VERSION.RELEASE;
    }

    public static int getOSCode() {
        LogUtil.d("getOSCode", Build.VERSION.SDK_INT + "");
        return Build.VERSION.SDK_INT;
    }

    public static int getScreenDPI() {
        WindowManager wm = (WindowManager) MyApplication.getContext().getSystemService(WINDOW_SERVICE);
        DisplayMetrics metric = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metric);
        LogUtil.d("getScreenDPI", metric.densityDpi + "");
        return metric.densityDpi;
    }

    /**
     * 去除小数点
     */
    public static String removePoint(String str) {
        String s = str.replace(".", "");
        return s;
    }


}
