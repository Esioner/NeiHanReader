package com.esioner.neihanreader.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.esioner.neihanreader.Service;
import com.esioner.neihanreader.bean.neiHanBean.NeiHanBean;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * @author Esioner
 * @date 2017/12/28
 */

public abstract class BaseFragment extends Fragment {
    protected NeiHanBean neiHanBean;
    private Retrofit retrofit;
    private Service service;
    private ConnectStatus status;
    protected SmartRefreshLayout smartRefreshLayout;
    private boolean isVisible = false;
    protected static final int IS_REFRESH = 0;
    protected static final int IS_LOADING_MORE = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    protected void getDate(Map<String, String> parameterMaps, final int i) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                //打印retrofit日志
                Log.i("RetrofitLog", "retrofitBack = " + message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://lf.snssdk.com/neihan/stream/mix/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        service = retrofit.create(Service.class);
        Observable<NeiHanBean> observable = service.get(parameterMaps);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NeiHanBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        status.error(e);
                    }

                    @Override
                    public void onNext(NeiHanBean neiHanBean) {
                        Toast.makeText(getContext(), neiHanBean.getNeiHanRootData().getRefreshTip(), Toast.LENGTH_SHORT).show();
                        loadingComplete(i);
                        Log.d("TAG", "onNext: " + neiHanBean);
                        //回调
                        status.success(neiHanBean);
                    }
                });
    }

    /**
     * 加载完成
     */
    protected void loadingComplete(int i) {
        if (smartRefreshLayout != null) {
            if (i == IS_REFRESH) {
                smartRefreshLayout.finishRefresh();
            } else {
                smartRefreshLayout.finishLoadmore();
            }
        }

    }
    public void setConnectStatus(ConnectStatus status) {
        this.status = status;
    }

    public void setNeiHanBean(NeiHanBean neiHanBean) {
        this.neiHanBean = neiHanBean;
    }

    public NeiHanBean getNeiHanBean() {
        return neiHanBean;
    }

    public interface ConnectStatus {
        /**
         * 加载成功
         *
         * @param bean
         */
        void success(NeiHanBean bean);

        /**
         * 加载失败
         *
         * @param error
         */
        void error(Throwable error);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
        }
    }

    protected void onVisible() {
        lazyLoad();
    }

    protected abstract void lazyLoad();
}
