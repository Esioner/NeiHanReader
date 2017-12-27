package com.esioner.neihanreader.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.esioner.neihanreader.R;
import com.esioner.neihanreader._Api;
import com.esioner.neihanreader._URL;
import com.esioner.neihanreader.adapter.RecyclerViewAdapter;
import com.esioner.neihanreader.bean.neiHanBean.NeiHanBean;
import com.esioner.neihanreader.bean.neiHanBean.NeiHanDataBean;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * @author Esioner
 * @date 2017/12/27
 */

public class JokeFragment extends Fragment {

    private RecyclerView rvJokeFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.neihan_joke_fragment_layout, null);
        rvJokeFragment = view.findViewById(R.id.rv_joke_fragment);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://iu.snssdk.com/neihan/stream/mix/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        _Api service = retrofit.create(_Api.class);
        Observable<NeiHanBean> observable = service.get(_URL.getJokeParameter());
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<NeiHanBean>() {
                    @Override
                    public void call(NeiHanBean neiHanBean) {
                        List<NeiHanDataBean> neiHanDatas = neiHanBean.getData().getData();
                        RecyclerViewAdapter adapter = new RecyclerViewAdapter(neiHanDatas);
                        LinearLayoutManager manager = new LinearLayoutManager(getContext());
                        rvJokeFragment.setLayoutManager(manager);
                        rvJokeFragment.setAdapter(adapter);
                    }
                });
    }
}
