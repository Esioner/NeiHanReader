package com.esioner.neihanreader;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.esioner.neihanreader.adapter.MyFragmentPagerAdapter;
import com.esioner.neihanreader.fragment.ImageFragment;
import com.esioner.neihanreader.fragment.JokeFragment;
import com.esioner.neihanreader.fragment.RecommendFragment;
import com.esioner.neihanreader.fragment.VideoFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private TabLayout.Tab mTabRecommend;
    private TabLayout.Tab mTabJoke;
    private TabLayout.Tab mTabImage;
    private TabLayout.Tab mTabMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //实现透明状态栏
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);

        }

        initView();
    }

    /**
     * 初始化 控件
     */
    private void initView() {
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.vp);

        List<Fragment> fragments = new ArrayList<>();
        RecommendFragment recommendFragment = new RecommendFragment();
        JokeFragment jokeFragment = new JokeFragment();
        ImageFragment imageFragment = new ImageFragment();
        VideoFragment videoFragment = new VideoFragment();
        fragments.add(recommendFragment);
        fragments.add(jokeFragment);
        fragments.add(imageFragment);
        fragments.add(videoFragment);

        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);

        mTabRecommend = tabLayout.getTabAt(0);
        mTabJoke = tabLayout.getTabAt(1);
        mTabImage = tabLayout.getTabAt(2);
        mTabMovie = tabLayout.getTabAt(3);

        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(4);
    }

}
