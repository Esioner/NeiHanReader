package com.esioner.neihanreader.utils;

import java.util.Map;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * @author Esioner
 * @date 2018/1/3
 */

public class HttpManager {
    private final HttpUtils mHttp;
    private CommonUtils commonUtils = CommonUtils.utils;
    private long _rticket = commonUtils.getUnixTime();

    private HttpManager() {
        mHttp = HttpUtils.getInstance();
    }

    public static HttpManager getInstance() {
        return new HttpManager();
    }

    public void action(String action, String itemId, long groupId, Callback callback) {
        String url = "http://lf.snssdk.com/2/data/item_action/?" +
                "manifest_version_code=670&" +
                "_rticket=" + _rticket + "&" +
                "ac=wifi&" +
//                43075228107
                "device_id=" + "43075228107" + "&" +
                "iid=" + SPUtils.getString("iid") + "&" +
                "os_version=" + commonUtils.getOSCode() + "&" +
                "channel=oppo-cpa&" +
                "version_code=670&" +
                "device_type=" + commonUtils.getDeviceModel() + "&" +
                "language=zh&" +
                "uuid=" + SPUtils.getString("uuid") + "&" +
                "resolution=" + commonUtils.getScreenWidth() + "*" + commonUtils.getScreenHeight() + "&" +
                "openudid=" + SPUtils.getString("openudid") + "&" +
                "update_version_code=6703&" +
                "app_name=joke_essay&" +
                "version_name=6.7.0&" +
                "os_api=" + commonUtils.getOSCode() + "&" +
                "device_brand=" + commonUtils.getManufacturer() + "&" +
                "ssmix=a&" +
                "device_platform=android&" +
                "dpi=" + commonUtils.getScreenDPI() + "&" +
                "aid=7&" +
                "ts=" + _rticket / 1000 + "&" +
                "as=&" +
                "cp=";
        RequestBody requestBody = new FormBody.Builder()
                .add("action", action)
                .add("group_id", groupId + "")
                .add("item_id", itemId + "")
                .add("refer", "10201")
                .add("manifest_version_code", "670")
                .add("_rticket", _rticket + "")
                .add("ac", "wifi")
                .add("device_id", "43075228107")
                .add("iid", SPUtils.getString("iid"))
                .add("os_version", commonUtils.getVersionCode())
                .add("channel", "oppo-cpa")
                .add("version_code", "670")
                .add("device_type", commonUtils.getDeviceModel())
                .add("language", "zh")
                .add("uuid", SPUtils.getString("uuid"))
                .add("resolution", commonUtils.getScreenWidth() + "*" + commonUtils.getScreenHeight())
                .add("openudid", SPUtils.getString("openudid"))
                .add("app_name", "joke_essay")
                .add("version_name", "6.7.0")
                .add("os_api", commonUtils.getOSCode() + "")
                .add("device_brand", commonUtils.getManufacturer())
                .add("ssmix", "a")
                .add("device_platform", "android")
                .add("dpi", commonUtils.getScreenDPI() + "")
                .add("aid", "7")
                .build();
        mHttp.post(url, requestBody, callback);
    }


    public interface HttpManagerCallBack {
        void onSuccess();

        void onError();
    }
}
