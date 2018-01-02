package com.esioner.neihanreader.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.esioner.neihanreader.MyApplication;
import com.esioner.neihanreader.R;
import com.esioner.neihanreader.Service;
import com.esioner.neihanreader._URL;
import com.esioner.neihanreader.bean.neiHanBean.NeiHanCommentsBean;
import com.esioner.neihanreader.bean.neiHanBean.NeiHanDataBean;
import com.esioner.neihanreader.view.CommentView;
import com.esioner.neihanreader.view.LikeShareView;

import java.io.IOException;
import java.util.List;
import java.util.Observable;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * @author Esioner
 * @date 2017/12/27
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements LikeShareView.ViewOnClickListener {
    private List<NeiHanDataBean> neiHanBeans;

    public RecyclerViewAdapter(List<NeiHanDataBean> neiHanBeans) {
        this.neiHanBeans = neiHanBeans;
    }


    /**
     * 段子 viewHolder
     */
    public class JokeViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView ivAuthorHeader;
        private TextView tvAuthorName;
        private TextView tvAuthorContent;
        private final LikeShareView likeShareBar;
        private CommentView commentView;

        public JokeViewHolder(View itemView) {
            super(itemView);
            ivAuthorHeader = itemView.findViewById(R.id.iv_joke_author_header_image);
            tvAuthorName = itemView.findViewById(R.id.tv_joke_author_name);
            tvAuthorContent = itemView.findViewById(R.id.tv_joke_author_content);
            likeShareBar = itemView.findViewById(R.id.like_share_bar);
            commentView = itemView.findViewById(R.id.comment_view_joke_item);
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
        final NeiHanDataBean dataBean = neiHanBeans.get(position);
        if (holder instanceof JokeViewHolder) {
            JokeViewHolder jokeHolder = (JokeViewHolder) holder;
            //设置用户头像
            Glide.with(MyApplication.getContext()).load(dataBean.getGroupData().getUserInfo().getAvatarUrl()).into(jokeHolder.ivAuthorHeader);
            //设置用户名
            jokeHolder.tvAuthorName.setText(dataBean.getGroupData().getUserInfo().getName());
//            设置用户内容
            jokeHolder.tvAuthorContent.setText(dataBean.getGroupData().getText());
//            设置顶数
            jokeHolder.likeShareBar.setBuryCount(dataBean.getGroupData().getBuryCount());
//            设置踩次数
            jokeHolder.likeShareBar.setDiggCount(dataBean.getGroupData().getDiggCount());
//            设置分享次数
            jokeHolder.likeShareBar.setShareCount(dataBean.getGroupData().getShareCount());
            jokeHolder.likeShareBar.setViewListener(new LikeShareView.ViewOnClickListener() {
                @Override
                public void onBuryClick(View view, int amount) {

                }

                @Override
                public void onDiggClick(View view, int amount, NeiHanDataBean neiHanDataBean) {
                    Toast.makeText(MyApplication.getContext(), "点赞", Toast.LENGTH_SHORT).show();
                    Retrofit retrofit = new Retrofit.Builder().baseUrl("http://iu.snssdk.com/").build();
                    Service service = retrofit.create(Service.class);
                    Call<ResponseBody> call = service.post(_URL.diggMap(), _URL.diggHeaderMap(0, dataBean.getGroupData().getGroupId(), dataBean.getGroupData().getGroupId(), "digg"));
                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            try {
                                Log.d("DIGGGGG", "onResponse: " + response.body().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }


                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Log.e("DIGGGGG", "onFailure: " + t.toString());
                        }
                    });
                }

                @Override
                public void onShareClick(View view, int amount) {

                }

                @Override
                public void onCommentClick(View view) {

                }

                @Override
                public void onCheckChange(View view, boolean isChecked) {

                }
            });

            List<NeiHanCommentsBean> comments = dataBean.getComments();
            if (comments.size() > 0) {
                jokeHolder.commentView.setVisibility(View.VISIBLE);
                jokeHolder.commentView.setGodComment(true);
                jokeHolder.commentView.setUserComment(comments.get(0).getText());
                jokeHolder.commentView.setUserName(comments.get(0).getUserName());
                jokeHolder.commentView.setUserDiggCount(comments.get(0).getDiggCount() + "");
                Glide.with(MyApplication.getContext()).load(comments.get(0).getAvatarUrl()).into(jokeHolder.commentView.getImageView());
            } else {
                jokeHolder.commentView.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return neiHanBeans.size();
    }


    @Override
    public void onBuryClick(View view, int amount) {
        Toast.makeText(MyApplication.getContext(), "点踩", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onDiggClick(View view, int amount, NeiHanDataBean neiHanDataBean) {
        Toast.makeText(MyApplication.getContext(), "点赞", Toast.LENGTH_SHORT).show();
//        service.post();
    }

    @Override
    public void onShareClick(View view, int amount) {
        Toast.makeText(MyApplication.getContext(), "分享", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCommentClick(View view) {
        Toast.makeText(MyApplication.getContext(), "评论", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCheckChange(View view, boolean isChecked) {
        if (isChecked) {
            Toast.makeText(MyApplication.getContext(), "喜欢", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MyApplication.getContext(), "不喜欢", Toast.LENGTH_SHORT).show();
        }
    }
}
