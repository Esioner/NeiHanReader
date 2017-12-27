package com.esioner.neihanreader.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.esioner.neihanreader.MyApplication;
import com.esioner.neihanreader.R;
import com.esioner.neihanreader.bean.neiHanBean.NeiHanBean;
import com.esioner.neihanreader.bean.neiHanBean.NeiHanDataBean;
import com.esioner.neihanreader.bean.neiHanBean.NeiHanGroupBean;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author Esioner
 * @date 2017/12/27
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<NeiHanDataBean> neiHanBeans;

    public RecyclerViewAdapter(List<NeiHanDataBean> neiHanBeans) {
        this.neiHanBeans = neiHanBeans;
    }

    public class JokeViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView ivAuthorHeader;
        private TextView tvAuthorName;
        private TextView tvAuthorContent;

        public JokeViewHolder(View itemView) {
            super(itemView);
            ivAuthorHeader = itemView.findViewById(R.id.iv_joke_author_header_image);
            tvAuthorName = itemView.findViewById(R.id.tv_joke_author_name);
            tvAuthorContent = itemView.findViewById(R.id.tv_joke_author_content);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.neihan_joke_item_layout, null);
        JokeViewHolder jokeViewHolder = new JokeViewHolder(view);
        return jokeViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        NeiHanDataBean dataBean = neiHanBeans.get(position);
        if (holder instanceof JokeViewHolder) {
            JokeViewHolder jokeHolder = (JokeViewHolder) holder;
            Glide.with(MyApplication.getContext()).load(dataBean.getGroup().getUserInfo().getAvatarUrl()).into(jokeHolder.ivAuthorHeader);
            jokeHolder.tvAuthorName.setText(dataBean.getGroup().getUserInfo().getName());
            jokeHolder.tvAuthorContent.setText(dataBean.getGroup().getText());
        }
    }

    @Override
    public int getItemCount() {
        return neiHanBeans.size();
    }
}
