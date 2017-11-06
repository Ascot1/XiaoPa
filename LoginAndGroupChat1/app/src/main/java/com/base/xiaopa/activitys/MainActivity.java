package com.base.xiaopa.activitys;

import android.os.Bundle;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;

import android.support.v4.view.ViewPager;


import android.view.View;
import android.view.ViewGroup;


import android.widget.ImageButton;


import com.base.xiaopa.view.NoScrollViewPager;
import com.base.xiaopa.view.SlidingMenu;
import com.xiaopa.android.R;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private ViewPager mViewPager; //用于放置界面切换
    private List<View> mViews = new ArrayList<View>();//用来存放每个功能展示不同的页面

    /**
     * Tab 以及按钮
     */

    private ImageButton mHomePageImg;
    private ImageButton mGroupChatImg;
    private ImageButton mTrendsImg;
    private ImageButton mMallImg;
    private ImageButton mMeImg;
    private SlidingMenu mMenu;

    NoScrollViewPager vp_pager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        mMenu =(SlidingMenu)findViewById(R.id.id_menu);
        fragmentpage();

    }
    public void fragmentpage()
    {
        final ArrayList<Fragment> list =new ArrayList<Fragment>();
        list.add(new GroupActivity());
        list.add(new TrendsActivity());
        list.add(new HomeActivity());
        list.add(new StoreActivity());
        mHomePageImg= (ImageButton) findViewById(R.id.id_home_img);
        mTrendsImg= (ImageButton) findViewById(R.id.id_trends_img);
        mMallImg= (ImageButton) findViewById(R.id.id_mall_img);
        mMeImg= (ImageButton) findViewById(R.id.id_me_img);
        mGroupChatImg= (ImageButton) findViewById(R.id.id_chat_img);

        FragmentStatePagerAdapter adapter_f=new FragmentStatePagerAdapter(getSupportFragmentManager()) {
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
                   resetImg();
                    mGroupChatImg.setBackgroundResource(R.drawable.chat_orange);
                }else if(vp_pager.getCurrentItem()==1)
                {
                    resetImg();
                    mTrendsImg.setBackgroundResource(R.drawable.trends_orange);

                }else if(vp_pager.getCurrentItem()==2)
                {
                    resetImg();
                    mHomePageImg.setBackgroundResource(R.drawable.home_orange);

                }else  if(vp_pager.getCurrentItem()==3)
                {
                    resetImg();
                    mMallImg.setBackgroundResource(R.drawable.mall_orange);

                }else  if(vp_pager.getCurrentItem()==4){
                    resetImg();

                    mMeImg.setBackgroundResource(R.drawable.me_orange);
                }


            }
        };
        vp_pager= (NoScrollViewPager) findViewById(R.id.vp_mf);
        vp_pager.setAdapter(adapter_f);
    }


    @Override
    public void onClick(View v) {

    }
    public void groupChat(View v)
    {
        vp_pager.setCurrentItem(0,true);

    }
    public  void trends(View v){
        vp_pager.setCurrentItem(1,true);
    }
    public void home(View v){
        vp_pager.setCurrentItem(2,true);
    }
    public void shop(View v){
        vp_pager.setCurrentItem(3,true);
    }
    public void me(View v){
        vp_pager.setCurrentItem(4,true);
    }


    public void resetImg()
    {

        mHomePageImg.setBackgroundResource(R.drawable.home_grey);
        mTrendsImg.setBackgroundResource(R.drawable.trends_grey);
        mMallImg.setBackgroundResource(R.drawable.mall_grey);
        mMeImg.setBackgroundResource(R.drawable.me_grey);
        mGroupChatImg.setBackgroundResource(R.drawable.chat_grey);
    }
}
