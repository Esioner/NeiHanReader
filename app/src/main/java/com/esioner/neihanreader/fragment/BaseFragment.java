package com.esioner.neihanreader.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.esioner.neihanreader.Service;
import com.esioner.neihanreader.bean.neiHanBean.NeiHanBean;

import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    protected void getDate(Map<String, String> parameterMaps) {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://iu.snssdk.com/neihan/stream/mix/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(Service.class);
        Observable<NeiHanBean> observable = service.get(parameterMaps);
        observable
                .doOnNext(new Action1<NeiHanBean>() {
                    @Override
                    public void call(NeiHanBean neiHanBean) {
                        showProgress();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<NeiHanBean>() {
                    @Override
                    public void call(NeiHanBean neiHanBean) {
                        Toast.makeText(getContext(), neiHanBean.getData().getRefreshTip(), Toast.LENGTH_SHORT).show();
                        dismissProgress();
                        setNeiHanBean(neiHanBean);
                    }
                });
    }

    /**
     * 展示 progress
     */
    protected abstract void showProgress();

    /**
     * progress消失
     */
    protected abstract void dismissProgress();

    public void setNeiHanBean(NeiHanBean neiHanBean) {
        this.neiHanBean = neiHanBean;
    }

    public NeiHanBean getNeiHanBean() {
        return neiHanBean;
    }

}
