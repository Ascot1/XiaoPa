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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.base.xiaopa.base.TJ;
import com.base.xiaopa.base.XSQG;
import com.xiaopa.android.R;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;

public class StoreActivity extends Fragment {

    private RecyclerView mRecyclerView;
    private List<TJ> mDatas;
    private HomeAdapter mAdapter;
    private Button trolley;

    private RecyclerView recy;
    private List<XSQG> mDatas_xianshi;
    private HomeAdapter_xianshi mAdapter_xianhsi;

    private long mHour=12;
    private long mMin=50;
    private long mSecond=39;
    private boolean isRun=true;

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
                Toast.makeText(banner.getContext(), "点击了" + position, Toast.LENGTH_SHORT).show();
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
        recy.setAdapter(mAdapter_xianhsi=new HomeAdapter_xianshi());
        //  recy.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL_LIST)); //设置纵向分割线

        /**抢购倒计时**/


        // startRun();
        /****End*****/

        /**
         * 推荐
         * ***/
        initData();
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler);
        //mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(mAdapter=new HomeAdapter(getContext()));

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

    class HomeAdapter_xianshi extends RecyclerView.Adapter<HomeAdapter_xianshi.MyViewHolder>
    {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    getActivity()).inflate(R.layout.item_store_xianshi, parent,
                    false));
            return holder;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position)
        {
            holder.ivxs_head.setBackgroundResource(mDatas_xianshi.get(position).getXshead());
            holder.tvxs_yprice.setText(mDatas_xianshi.get(position).getXsyprice());
            holder.tvxs_yprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //设置划线
            holder.tvxs_xprice.setText(mDatas_xianshi.get(position).getXsxprice());


        }

        @Override
        public int getItemCount()
        {
            return mDatas_xianshi.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder
        {

            ImageView ivxs_head;
            TextView tvxs_yprice;
            TextView tvxs_xprice;
            TextView xs_hour;
            TextView xs_minutes;
            TextView xs_second;
            public MyViewHolder(View view)
            {
                super(view);
                ivxs_head= (ImageView) view.findViewById(R.id.xshead);
                tvxs_xprice= (TextView) view.findViewById(R.id.xsxprice);
                tvxs_yprice= (TextView) view.findViewById(R.id.xsyprice);
                xs_hour= (TextView) view.findViewById(R.id.clock_hour);
                xs_minutes= (TextView) view.findViewById(R.id.clock_minutes);
                xs_second= (TextView) view.findViewById(R.id.clock_second);

            }
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
    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder>
    {


          Context mContext;

        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    getActivity()).inflate(R.layout.item_store_tuijian, parent,
                    false));
            return holder;
        }

       public HomeAdapter(Context mContext)
       {
           this.mContext=mContext;

       }

        public void onBindViewHolder(MyViewHolder holder, int position)
        {
            holder.iv_tjhead.setBackgroundResource(mDatas.get(position).getTjhead());
            holder.tv_tjname.setText(mDatas.get(position).getTjname());
            holder.tv_tjnumber.setText(mDatas.get(position).getTjnumber());
            holder.tv_tjprcie.setText(mDatas.get(position).getTjprice());

        }

        @Override
        public int getItemCount()
        {
            return mDatas.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder
        {

            ImageView iv_tjhead;
            TextView  tv_tjname;
            TextView  tv_tjprcie;
            TextView  tv_tjnumber;

            public MyViewHolder(View view)
            {

                super(view);
                iv_tjhead= (ImageView) view.findViewById(R.id.tjhead);
                tv_tjname= (TextView) view.findViewById(R.id.tjname);
                tv_tjprcie= (TextView) view.findViewById(R.id.tjprice);
                tv_tjnumber= (TextView) view.findViewById(R.id.tjnumber);

            }
        }
    }
         /***end****/

         /***购物车跳转***/



   /* final Handler timeHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==1)
            {
                computerTime();
                if(mHour<10) {
                    mDatas_xianshi.get(position).setXsHour("0"+mHour);
                    holder.xs_hour.setText(mDatas_xianshi.get(position).getXsHour());
                }
                else {
                    mDatas_xianshi.get(position).setXsHour(""+mHour);
                    holder.xs_hour.setText(mDatas_xianshi.get(position).getXsHour());
                }
            }
            if(mMin<10){
                mDatas_xianshi.get(position).setXsMin("0"+mMin);
                holder.xs_minutes.setText(mDatas_xianshi.get(position).getXsMin());}
            else{
                mDatas_xianshi.get(position).setXsMin(""+mMin);
                holder.xs_minutes.setText(mDatas_xianshi.get(position).getXsMin());}
            if(mSecond<10){
                mDatas_xianshi.get(position).setXsSecond("0"+mSecond);
                holder.xs_second.setText(mDatas_xianshi.get(position).getXsSecond());}
            else
            {
                mDatas_xianshi.get(position).setXsSecond(""+mSecond);
                holder.xs_second.setText(mDatas_xianshi.get(position).getXsSecond());}


        }
    };*/




    /**
     * *开启倒计时
     * */
   /* private void startRun()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isRun){

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
        }).start();

    }*/

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


