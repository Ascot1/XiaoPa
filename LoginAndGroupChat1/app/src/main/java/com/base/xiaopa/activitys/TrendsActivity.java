package com.base.xiaopa.activitys;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaopa.android.R;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;

public class TrendsActivity extends Fragment  {

    View v;
    TextView SM;
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.activity_trends, container, false);
        Button mSummary = (Button) v.findViewById(R.id.textView3);
        mSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SecondActivity.class);
                startActivity(intent);
            }
        });

        SM = (TextView) v.findViewById(R.id.summary);
        SM.setText("我是第四个活动");
        BGABanner banner = (BGABanner) v.findViewById(R.id.banner1);
        List<View> views1 = new ArrayList<>();
        List<String> views2= new ArrayList<>();
        views1.add(getPageView(R.drawable.trends1));
        views1.add(getPageView(R.drawable.trends2));
        views1.add(getPageView(R.drawable.trends3));
        views1.add(getPageView(R.drawable.trends4));
        views2.add("我是第一个活动");
        views2.add("我是第二个活动");
        views2.add("我是第三个活动");
        views2.add("我是第四个活动");


        banner.setData(views1, null,views2);
        banner.setAutoPlayAble(false);


        banner.setDelegate(new BGABanner.Delegate<ImageView, String>() {

            @Override
            public void onBannerItemClick(BGABanner banner, ImageView itemView,
                                          String model, int position) {
                change(position);
            }
        });
        return v;
    }

    public void change(int position) {
        if (position == 0) {
            SM.setText("我是第一个活动");
        } else if (position == 1) {
            SM.setText("我是第二个活动");
        } else if (position == 2) {
            SM.setText("我是第三个活动");
        } else if (position == 3) {
            SM.setText("我是第四个活动");
        }

    }
    /**
     * banner
     **/
    private View getPageView(@DrawableRes int resid) {
        ImageView imageView = new ImageView(getContext());
        imageView.setImageResource(resid);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return imageView;
    }


}
