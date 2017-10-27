package com.base.xiaopa.activitys;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.xiaopa.android.R;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * Created by cldn1 on 2017/10/8.
 */
public class HomeActivity extends Fragment {

    View v;
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.activity_homepage,container,false);
        BGABanner banner = (BGABanner)v.findViewById(R.id.banner);
        List<View> views = new ArrayList<>();
        List<String> views2= new ArrayList<>();
        views.add(getPageView(R.drawable.trends1));
        views.add(getPageView(R.drawable.trends2));
        views.add(getPageView(R.drawable.trends3));
        views.add(getPageView(R.drawable.trends4));
        views2.add("我是第一个活动");
        views2.add("我是第二个活动");
        views2.add("我是第三个活动");
        views2.add("我是第四个活动");
        banner.setData(views, null,views2);
        banner.setAutoPlayAble(true);
        return v;
    }

    /**banner**/
    private View getPageView(@DrawableRes int resid) {
        ImageView imageView = new ImageView(getContext());
        imageView.setImageResource(resid);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return imageView;
    }
    /***End***/
}
