package com.esioner.neihanreader.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
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
    private RadioButton rbBury;
    private TextView tvBuryCount;
    private RadioButton rbDigg;
    private TextView tvDiggCount;
    private LinearLayout llShare;
    private TextView tvShareCount;
    private ImageButton ibShare;

    private boolean isFirst = true;
    /**
     * 赞个数
     */
    private int buryCount = 0;
    /**
     * 踩个数
     */
    private int diggCount = 0;
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
        rbBury = findViewById(R.id.rb_bury);
        tvBuryCount = findViewById(R.id.tv_like_count);
        rbDigg = findViewById(R.id.rb_dislike);
        tvDiggCount = findViewById(R.id.tv_dislike_count);
        llShare = findViewById(R.id.ll_share);
        ibShare = findViewById(R.id.ib_share);
        tvShareCount = findViewById(R.id.tv_share_count);

        rbBury.setOnClickListener(this);
        rbDigg.setOnClickListener(this);
        llShare.setOnClickListener(this);

        TypedArray obtainAttrs = getContext().obtainStyledAttributes(attrs, R.styleable.LikeShareView);
        int btnSize = obtainAttrs.getDimensionPixelSize(R.styleable.LikeShareView_btnSize, 0);
        int tvSize = obtainAttrs.getDimensionPixelSize(R.styleable.LikeShareView_textSize, 0);
        obtainAttrs.recycle();

//        LayoutParams btnParms = new LayoutParams(btnSize, btnSize);
//        rbDigg.setLayoutParams(btnParms);
//        rbBury.setLayoutParams(btnParms);
//        ibShare.setLayoutParams(btnParms);
//
//        LayoutParams textParams = new LayoutParams(tvSize, tvSize);
//        tvDiggCount.setLayoutParams(textParams);
//        tvBuryCount.setLayoutParams(textParams);
//        tvShareCount.setLayoutParams(textParams);
//        if (tvSize != 0) {
//            tvBuryCount.setTextSize(TypedValue.COMPLEX_UNIT_PX, tvSize);
//            tvDiggCount.setTextSize(TypedValue.COMPLEX_UNIT_PX, tvSize);
//            tvShareCount.setTextSize(TypedValue.COMPLEX_UNIT_PX, tvSize);
//            tvBuryCount.setText(buryCount + "");
//            tvDiggCount.setText(diggCount + "");
//            tvShareCount.setText(shareCount + "");
//        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rb_bury:
                //判断是否是第一次点击，如果是执行回调函数，即是否已赞过或者已踩过
                if (isFirst) {
                    isFirst = false;
                    viewListener.onBuryClick(tvBuryCount, buryCount);
                } else {
                    rbBury.setEnabled(false);
                }
                break;
            case R.id.rb_dislike:
                //判断是否是第一次点击，如果是执行回调函数，即是否已赞过或者已踩过
                if (isFirst) {
                    isFirst = false;
                    viewListener.onDiggClick(tvDiggCount, diggCount);
                } else {
                    rbBury.setEnabled(false);
                }
                break;
            case R.id.ll_share:
                viewListener.onShareClick(tvShareCount, shareCount);
                break;
            default:
        }
    }

    public int getBuryCount() {
        return buryCount;
    }

    public void setBuryCount(int buryCount) {
        tvBuryCount.setText(buryCount + "");
    }

    public int getDiggCount() {
        return diggCount;
    }

    public void setDiggCount(int diggCount) {
        tvDiggCount.setText(diggCount + "");
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
        void onBuryClick(View view, int amount);

        void onDiggClick(View view, int amount);

        void onShareClick(View view, int amount);
    }
}
