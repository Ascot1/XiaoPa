package com.base.xiaopa.activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiaopa.android.R;

/**
 * Created by Satellite Wu on 2017/10/29.
 */
public class Goods_recommendation extends Fragment {

    View v;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.activity_goods_vp_recommendation,container,false);
        return v;
    }
}
