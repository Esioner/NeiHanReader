package com.esioner.neihanreader.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.esioner.neihanreader.R;

/**
 * @author Esioner
 * @date 2017/12/28
 */

public class LikeShareView extends LinearLayout implements View.OnClickListener {

    private RadioGroup radioGroup;
    private RadioButton rbLike;
    private TextView tvLikeCount;
    private RadioButton rbDislike;
    private TextView tvDislikeCount;
    private LinearLayout llShare;
    private TextView tvShareCount;
    private ImageButton ibShare;

    private int likeCount = 0;
    private int dislikeCount = 0;
    private int shareCount = 0;

    private ViewOnClickListener viewListener;


    public LikeShareView(Context context) {
        this(context, null);
    }

    public LikeShareView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LikeShareView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(getContext()).inflate(R.layout.neihan_common_bar, this);
        radioGroup = findViewById(R.id.rg);
        rbLike = findViewById(R.id.rb_like);
        tvLikeCount = findViewById(R.id.tv_like_count);
        rbDislike = findViewById(R.id.rb_dislike);
        tvDislikeCount = findViewById(R.id.tv_dislike_count);
        llShare = findViewById(R.id.ll_share);
        ibShare = findViewById(R.id.ib_share);
        tvShareCount = findViewById(R.id.tv_share_count);

        rbLike.setOnClickListener(this);
        rbDislike.setOnClickListener(this);
        llShare.setOnClickListener(this);

        TypedArray obtainAttrs = getContext().obtainStyledAttributes(attrs, R.styleable.LikeShareView);
        int btnSize = obtainAttrs.getDimensionPixelSize(R.styleable.LikeShareView_btnSize, 0);
        int tvSize = obtainAttrs.getDimensionPixelSize(R.styleable.LikeShareView_textSize, 0);
        obtainAttrs.recycle();

//        LayoutParams btnParms = new LayoutParams(btnSize, btnSize);
//        rbDislike.setLayoutParams(btnParms);
//        rbLike.setLayoutParams(btnParms);
//        ibShare.setLayoutParams(btnParms);
//
//        LayoutParams textParams = new LayoutParams(tvSize, tvSize);
//        tvDislikeCount.setLayoutParams(textParams);
//        tvLikeCount.setLayoutParams(textParams);
//        tvShareCount.setLayoutParams(textParams);
//        if (tvSize != 0) {
//            tvLikeCount.setTextSize(TypedValue.COMPLEX_UNIT_PX, tvSize);
//            tvDislikeCount.setTextSize(TypedValue.COMPLEX_UNIT_PX, tvSize);
//            tvShareCount.setTextSize(TypedValue.COMPLEX_UNIT_PX, tvSize);
//            tvLikeCount.setText(likeCount + "");
//            tvDislikeCount.setText(dislikeCount + "");
//            tvShareCount.setText(shareCount + "");
//        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rb_like:
                viewListener.onClick(tvLikeCount, likeCount);
                break;
            case R.id.rb_dislike:
                viewListener.onClick(tvDislikeCount, dislikeCount);
                break;
            case R.id.ll_share:
                viewListener.onClick(tvShareCount, shareCount);
                break;
            default:
        }
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        tvLikeCount.setText(likeCount + "");
    }

    public int getDislikeCount() {
        return dislikeCount;
    }

    public void setDislikeCount(int dislikeCount) {
        tvDislikeCount.setText(dislikeCount + "");
    }

    public int getShareCount() {
        return shareCount;
    }

    public void setShareCount(int shareCount) {
        tvShareCount.setText(shareCount + "");
    }

    public void setViewListener(ViewOnClickListener viewListener) {
        this.viewListener = viewListener;
    }

    public interface ViewOnClickListener {
        /**
         * 改变 text 的内容
         *
         * @param view
         * @param amount
         */
        void onClick(View view, int amount);
    }
}
