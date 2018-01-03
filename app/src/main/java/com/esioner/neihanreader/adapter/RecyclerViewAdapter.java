package com.esioner.neihanreader.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.esioner.neihanreader.MainActivity;
import com.esioner.neihanreader.MyApplication;
import com.esioner.neihanreader.R;
import com.esioner.neihanreader.Service;
import com.esioner.neihanreader._URL;
import com.esioner.neihanreader.bean.ActionBean.ActionBean;
import com.esioner.neihanreader.bean.neiHanBean.NeiHanCommentsBean;
import com.esioner.neihanreader.bean.neiHanBean.NeiHanDataBean;
import com.esioner.neihanreader.utils.HttpManager;
import com.esioner.neihanreader.view.CommentView;
import com.esioner.neihanreader.view.LikeShareView;
import com.google.gson.Gson;

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

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<NeiHanDataBean> neiHanBeans;
    private static final String TAG = "RecyclerViewAdapter";
    private Activity activity;

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
            final JokeViewHolder jokeHolder = (JokeViewHolder) holder;
            //设置用户头像
            Glide.with(MyApplication.getContext()).load(dataBean.getGroupData().getUserInfo().getAvatarUrl()).into(jokeHolder.ivAuthorHeader);
            //设置用户名
            jokeHolder.tvAuthorName.setText(dataBean.getGroupData().getUserInfo().getName());
//            设置用户内容
            jokeHolder.tvAuthorContent.setText(dataBean.getGroupData().getText());
//           设置踩次数
            jokeHolder.likeShareBar.setBuryCount(dataBean.getGroupData().getBuryCount());
//            设置顶数
            jokeHolder.likeShareBar.setDiggCount(dataBean.getGroupData().getDiggCount());
//            设置分享次数
            jokeHolder.likeShareBar.setShareCount(dataBean.getGroupData().getShareCount());
//            设置评论次数
            jokeHolder.likeShareBar.setCommentCount(dataBean.getGroupData().getCommentCount());
            jokeHolder.likeShareBar.setViewListener(new LikeShareView.ViewOnClickListener() {
                @Override
                public void onBuryClick(View view, int amount) {
                    Toast.makeText(MyApplication.getContext(), "点踩", Toast.LENGTH_SHORT).show();
                    HttpManager manager = HttpManager.getInstance();
                    manager.action("bury", dataBean.getGroupData().getIdStr(), dataBean.getGroupData().getGroupId(), new okhttp3.Callback() {
                        @Override
                        public void onFailure(okhttp3.Call call, IOException e) {
                            Log.e(TAG, "onFailure: " + e);
                        }

                        @Override
                        public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {

//                          Log.d(TAG, "onResponse: " + response.body().string());

                            String jsonBody = response.body().string();
                            final ActionBean actionBean = new Gson().fromJson(jsonBody, ActionBean.class);
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    //           设置踩次数
                                    jokeHolder.likeShareBar.setBuryCount(actionBean.getBuryCount());
//            设置顶数
                                    jokeHolder.likeShareBar.setDiggCount(actionBean.getDiggCount());
//            设置评论次数
                                    jokeHolder.likeShareBar.setCommentCount(actionBean.getCommentCount());
                                }
                            });
                        }
                    });
                }

                @Override
                public void onDiggClick(View view, int amount) {
                    Toast.makeText(MyApplication.getContext(), "点赞", Toast.LENGTH_SHORT).show();
                    HttpManager manager = HttpManager.getInstance();
                    manager.action("digg", dataBean.getGroupData().getIdStr(), dataBean.getGroupData().getGroupId(), new okhttp3.Callback() {
                        @Override
                        public void onFailure(okhttp3.Call call, IOException e) {
                            Log.e(TAG, "onFailure: " + e);
                        }

                        @Override
                        public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {

//                          Log.d(TAG, "onResponse: " + response.body().string());

                            String jsonBody = response.body().string();
                            final ActionBean actionBean = new Gson().fromJson(jsonBody, ActionBean.class);
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    //           设置踩次数
                                    jokeHolder.likeShareBar.setBuryCount(actionBean.getBuryCount());
//            设置顶数
                                    jokeHolder.likeShareBar.setDiggCount(actionBean.getDiggCount());
//            设置评论次数
                                    jokeHolder.likeShareBar.setCommentCount(actionBean.getCommentCount());
                                }
                            });
                        }
                    });
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

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

}
