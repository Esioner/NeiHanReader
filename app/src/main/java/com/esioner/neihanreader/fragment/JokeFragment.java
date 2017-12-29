package com.esioner.neihanreader.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.esioner.neihanreader.R;
import com.esioner.neihanreader._URL;
import com.esioner.neihanreader.adapter.RecyclerViewAdapter;
import com.esioner.neihanreader.bean.neiHanBean.NeiHanBean;
import com.esioner.neihanreader.bean.neiHanBean.NeiHanDataBean;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Esioner
 * @date 2017/12/27
 */

public class JokeFragment extends BaseFragment implements BaseFragment.ConnectStatus {
    private static final String TAG = "S";
    private RecyclerView rvJokeFragment;
    private ConnectStatus status;
    private RecyclerViewAdapter adapter;
    private List<NeiHanDataBean> neiHanDatas = new ArrayList<>();
    private List<NeiHanDataBean> allNeiHanDatas = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.neihan_joke_fragment_layout, null);
        rvJokeFragment = view.findViewById(R.id.rv_joke_fragment);
        smartRefreshLayout = view.findViewById(R.id.smart_refresh_layout);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                allNeiHanDatas.clear();
                getDate(_URL.getJokeParameter(), IS_REFRESH);
            }
        });
        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                getDate(_URL.getJokeParameter(), IS_LOADING_MORE);
            }
        });
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        /**
         * 开始加载数据
         */
        getDate(_URL.getJokeParameter(), IS_REFRESH);
        setConnectStatus(this);
    }

    /**
     * 展示数据
     */
    public void showData() {
        if (adapter == null) {
            adapter = new RecyclerViewAdapter(allNeiHanDatas);
            LinearLayoutManager manager = new LinearLayoutManager(getContext());
            rvJokeFragment.setLayoutManager(manager);
            rvJokeFragment.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
        Log.d(TAG, "showData: " + allNeiHanDatas.size());
    }


    /**
     * 加载成功回调返回数据
     *
     * @param bean
     */
    @Override
    public void success(NeiHanBean bean) {
        neiHanDatas = bean.getNeiHanRootData().getDetailData();
        allNeiHanDatas.addAll(neiHanDatas);
        /**
         * 展示数据
         */
        showData();
    }

    /**
     * 加载失败，回调返回数据
     *
     * @param error
     */
    @Override
    public void error(Throwable error) {
        Log.e(TAG, "error: " + error.toString());
    }
}
