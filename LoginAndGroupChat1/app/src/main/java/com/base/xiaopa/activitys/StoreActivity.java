package com.base.xiaopa.activitys;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.base.xiaopa.base.TJ;
import com.base.xiaopa.base.XSQG;
import com.xiaopa.android.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.base.xiaopa.Adapter.*;
import cn.bingoogolapple.bgabanner.BGABanner;

public class StoreActivity extends Fragment {

    private RecyclerView mRecyclerView;
    private List<TJ> mDatas;
    private HomeAdapter_tuijian mAdapter;
    private Button trolley;

    private RecyclerView recy;
    private List<XSQG> mDatas_xianshi;
    private HomeAdapter_xianshi mAdapter_xianhsi;

    static private long mHour=12;
    static private long mMin=50;
    static private long mSecond=39;
    private boolean isRun=true;
    TextView xs_hour;
    TextView xs_minutes;
    TextView xs_second;
    View v;
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        v=inflater.inflate(R.layout.activity_store,container,false);

        BGABanner banner = (BGABanner)v.findViewById(R.id.banner);
        List<View> views = new ArrayList<>();
        views.add(getPageView(R.drawable.guanggao));
        //  views.add(getPageView(R.drawable.t6));
        // views.add(getPageView(R.drawable.t4));
        banner.setData(views);
        banner.setDelegate(new BGABanner.Delegate<ImageView, String>() {
            @Override
            public void onBannerItemClick(BGABanner banner, ImageView itemView, String model, int position) {
              //  Toast.makeText(banner.getContext(), "点击了" + position, Toast.LENGTH_SHORT).show();
                Intent i=new Intent(getContext(),GoodsActivity.class);
                startActivity(i);

                if (position==2){
                    banner.stopAutoPlay();
                }
            }
        });

        /***End***/


        /**
         /*限时抢购
         **/
        initData_xianshi();
        recy= (RecyclerView) v.findViewById(R.id.xianshi);
        recy.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));
        recy.setAdapter(mAdapter_xianhsi=new HomeAdapter_xianshi(mDatas_xianshi));
        //  recy.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL_LIST)); //设置纵向分割线
        mAdapter_xianhsi.setmOnItemClickListener(new HomeAdapter_xianshi.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent i=new Intent(getContext(),GoodsActivity.class);
                startActivity(i);
            }
        });
        /**抢购倒计时**/
         xs_hour= (TextView) v.findViewById(R.id.clock_hour);
         xs_minutes= (TextView) v.findViewById(R.id.clock_minutes);
         xs_second= (TextView) v.findViewById(R.id.clock_second);

         startRun();
        /****End*****/

        /**
         * 推荐
         * ***/
        initData();
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler);
        //mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(mAdapter=new HomeAdapter_tuijian(mDatas));

        mAdapter.setmOnItemClickListener(new HomeAdapter_tuijian.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent i=new Intent(getContext(),GoodsActivity.class);
                startActivity(i);
                Toast.makeText(getContext(), "点击了" + position, Toast.LENGTH_SHORT).show();
            }
        });
        /**解决nestScrollView嵌套RecyclerView时滑动不流畅问题**/
        LinearLayoutManager layoutManager =new LinearLayoutManager(getContext());
        layoutManager.setSmoothScrollbarEnabled(true);
        layoutManager.setAutoMeasureEnabled(true);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        //mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL_LIST));
        // mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));  //设置横向分割线

        /******End******/

        trolley= (Button) v.findViewById(R.id.trolley);

        trolley.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent i = new Intent(getActivity(),TrolleyActivity.class);
                startActivity(i);
            }
        });

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



    protected void initData_xianshi()
    {
        String yprice="原价 : ￥";
        String xprice="现价 : ￥";
        mDatas_xianshi=new ArrayList<XSQG>();
        {
            XSQG data=new XSQG();
            data.setXshead(R.drawable.xianshiqianggou1);
            data.setXsyprice(yprice+"4.8");
            data.setXsxprice(xprice+"2");
        mDatas_xianshi.add(data);
        }

        {
            XSQG data=new XSQG();
            data.setXshead(R.drawable.xianshiqianggou1);
            data.setXsyprice(yprice+"4.8");
            data.setXsxprice(xprice+"2");
            mDatas_xianshi.add(data);
        }
        {
            XSQG data=new XSQG();
            data.setXshead(R.drawable.xianshiqianggou1);
            data.setXsyprice(yprice+"4.8");
            data.setXsxprice(xprice+"2");
            mDatas_xianshi.add(data);
        }


    }



    /******推荐******/
    protected void initData()
    {
        String price="￥";
        String number="人已购买";

        {
            mDatas = new ArrayList<TJ>();
            TJ data = new TJ();
            data.setTjhead(R.drawable.caidai);
            data.setTjname("雪纱彩带");
            data.setTjprice(price + "3.29");
            data.setTjnumber("3590" + number);
            mDatas.add(data);
        }

        {
            TJ data = new TJ();
            data.setTjhead(R.drawable.uno);
            data.setTjname("经典UNO牌");
            data.setTjprice(price + "10.00");
            data.setTjnumber("5996" + number);
            mDatas.add(data);

        }

    }







     //xs_hour
   //  xs_minutes
   //  xs_second

    final Handler timeHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==1)
            {
                computerTime();
                if(mHour<10) {
                    xs_hour.setText("0"+mHour+"");
                }
                else {
                    xs_hour.setText(mHour+"");
                }

            if(mMin<10) {
                      xs_minutes.setText("0"+mMin+"");
            }
            else{
                     xs_minutes.setText(mMin+"");
                }
               }
            if(mSecond<10){
                     xs_second.setText("0"+mSecond+"");
            }
            else
            {
                xs_second.setText(mSecond+"");
            }


        }
    };




    /**
     * *开启倒计时
     * */
     ExecutorService pool= Executors.newFixedThreadPool(1);
    private void startRun()
    {

        pool.submit(new Runnable() {
            public void run() {
                while (isRun){
                   synchronized (""){
                    try {
                        Thread.sleep(1000);
                        Message message =Message.obtain();
                        message.what=1;
                        timeHandler.sendMessage(message);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            }
        });

    }

    /**
     * 倒计时开始
     */
private void computerTime(){

    mSecond--;
    if(mSecond<0) {
        mMin--;
        mSecond = 59;
        if (mMin < 0) {
            mHour--;
            mMin = 59;
        }

    }
}

}


