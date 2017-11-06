package com.base.xiaopa.activitys;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.base.xiaopa.view.NoScrollViewPager;
import com.xiaopa.android.R;

import java.util.ArrayList;

/**
 * Created by Satellite Wu on 2017/10/29.
 */
public class GoodsActivity extends FragmentActivity {

    NoScrollViewPager vp_pager;
    TextView mEvaluate;
    TextView mInformation;
    TextView mRecommendation;
    ImageView mshare;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_information);
        fragmentpage();
        mshare= (ImageView) findViewById(R.id.iv_share);
        mshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mshare.setBackgroundResource(R.drawable.collection_click);
                Toast.makeText(v.getContext(),"收藏成功！",Toast.LENGTH_LONG).show();
            }
        });
    }
    public void fragmentpage()
    {
       final ArrayList<Fragment> list =new ArrayList<>();
        list.add(new Goods_evaluate());
        list.add(new Goods_information());
        list.add(new Goods_recommendation());
        mEvaluate= (TextView) findViewById(R.id.tv_evaluate);
        mInformation= (TextView) findViewById(R.id.tv_information);
        mRecommendation= (TextView) findViewById(R.id.tv_recommendation);

        FragmentStatePagerAdapter adapter=new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public void finishUpdate(ViewGroup container) {
                super.finishUpdate(container);


                if(vp_pager.getCurrentItem()==0)
                {
                    mEvaluate.setTextColor(Color.parseColor("#FFA127"));
                    mRecommendation.setTextColor(Color.parseColor("#000000"));
                    mInformation.setTextColor(Color.parseColor("#000000"));
                }else if(vp_pager.getCurrentItem()==1)
                {
                    mEvaluate.setTextColor(Color.parseColor("#000000"));
                    mRecommendation.setTextColor(Color.parseColor("#000000"));
                    mInformation.setTextColor(Color.parseColor("#FFA127"));
                }else if(vp_pager.getCurrentItem()==2)
                {
                    mEvaluate.setTextColor(Color.parseColor("#000000"));
                    mRecommendation.setTextColor(Color.parseColor("#FFA127"));
                    mInformation.setTextColor(Color.parseColor("#000000"));
                }

            }
        };
        vp_pager= (NoScrollViewPager) findViewById(R.id.goods_viewpager);
        vp_pager.setAdapter(adapter);

    }

public void evaluate(View v) {
 vp_pager.setCurrentItem(0,true);
}
public void information(View v) {
    vp_pager.setCurrentItem(1,true);
}
    public void recommendation(View v){

        vp_pager.setCurrentItem(2,true);
    }



}
