package com.esioner.neihanreader.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.esioner.neihanreader.R;
import com.esioner.neihanreader._URL;
import com.esioner.neihanreader.adapter.RecyclerViewAdapter;
import com.esioner.neihanreader.bean.neiHanBean.NeiHanDataBean;

import java.util.List;

/**
 * @author Esioner
 * @date 2017/12/27
 */

public class JokeFragment extends BaseFragment {

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
        getDate(_URL.getJokeParameter());
        List<NeiHanDataBean> neiHanDatas = getNeiHanBean().getData().getData();
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(neiHanDatas);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rvJokeFragment.setLayoutManager(manager);
        rvJokeFragment.setAdapter(adapter);
    }

    @Override
    protected void showProgress() {

    }

    @Override
    protected void dismissProgress() {

    }
}
