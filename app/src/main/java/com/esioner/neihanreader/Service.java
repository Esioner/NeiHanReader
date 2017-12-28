package com.esioner.neihanreader;

import com.esioner.neihanreader.bean.neiHanBean.NeiHanBean;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * @author Esioner
 * @date 2017/12/27
 */

public interface Service {
    @GET("v1/")
    Observable<NeiHanBean> get(@QueryMap Map<String,String> map);
}
