package com.esioner.neihanreader.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.esioner.neihanreader.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Esioner on 2017/12/30.
 */

public class CommentView extends LinearLayout {

    private CircleImageView ivHeaderImage;
    private TextView tvUserName;
    private TextView tvUserComment;
    private TextView tvDiggCount;
    private LinearLayout rootView;
    private boolean isGodComment = false;
    private TextView tvGodMark;


    public CommentView(Context context) {
        this(context, null);
    }

    public CommentView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CommentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.neihan_comment_bar_layout, this);
        ivHeaderImage = findViewById(R.id.iv_comment_user_header_image);
        tvUserName = findViewById(R.id.tv_comment_user_name);
        tvUserComment = findViewById(R.id.tv_comment_user_comment);
        tvDiggCount = findViewById(R.id.tv_comment_user_digg_count);
        rootView = findViewById(R.id.ll_comment_layout);
        tvGodMark = findViewById(R.id.tv_god_mark);
//        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.CommentView);
//        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        ivHeaderImage.setLayoutParams(params);
//        tvUserName.setLayoutParams(params);
//        tvUserComment.setLayoutParams(params);
//        tvDiggCount.setLayoutParams(params);
//        attributes.recycle();

        judgeCommentType();
    }

    public CircleImageView getImageView() {
        return ivHeaderImage;
    }

    public void setUserName(String name) {
        tvUserName.setText(name);
    }

    public void setUserComment(String comment) {
        tvUserComment.setText(comment);
    }

    public void setUserDiggCount(String count) {
        tvDiggCount.setText(count);
    }

    @Override
    public void setVisibility(int visibility) {
        switch (visibility) {
            case VISIBLE:
                rootView.setVisibility(VISIBLE);
                break;
            case GONE:
                rootView.setVisibility(GONE);
                break;
        }
    }

    public boolean isGodComment() {
        return isGodComment;
    }

    public void setGodComment(boolean godComment) {
        isGodComment = godComment;
        judgeCommentType();
    }
    private void judgeCommentType(){
        if (isGodComment() == false) {
            tvGodMark.setVisibility(GONE);
        } else {
            tvGodMark.setVisibility(VISIBLE);
        }
    }
}
