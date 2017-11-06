package com.base.xiaopa.Adapter;

import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.xiaopa.base.XSQG;
import com.xiaopa.android.R;

import java.util.List;

/**
 * Created by Satellite Wu on 2017/11/5.
 */
public class HomeAdapter_xianshi extends RecyclerView.Adapter<HomeAdapter_xianshi.MyViewHolder> implements  View.OnClickListener
{
    private List<XSQG> mDatas_xianshi;
    private OnItemClickListener mOnItemClickListener=null;

    public HomeAdapter_xianshi(List<XSQG> mDatas_xianshi){
        this.mDatas_xianshi=mDatas_xianshi;
    }
    public void setmOnItemClickListener(OnItemClickListener listener){
        this.mOnItemClickListener=listener;
    }

    public static interface OnItemClickListener{
        void onItemClick(View view ,int position);
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_store_xianshi, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        view.setOnClickListener(this);

        return holder;
    }

    @Override
    public void onBindViewHolder( MyViewHolder holder,  int position)
    {
        holder.ivxs_head.setBackgroundResource(mDatas_xianshi.get(position).getXshead());
        holder.tvxs_yprice.setText(mDatas_xianshi.get(position).getXsyprice());
        holder.tvxs_yprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //设置划线
        holder.tvxs_xprice.setText(mDatas_xianshi.get(position).getXsxprice());

        holder.itemView.setTag(position); //将position保存在itemView的Tag中

    }

    @Override
    public int getItemCount()
    {
        return mDatas_xianshi.size();
    }

    @Override
    public void onClick(View v) {

        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(v,(int) v.getTag());
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {

        ImageView ivxs_head;
        TextView tvxs_yprice;
        TextView tvxs_xprice;

        public MyViewHolder(View view)
        {
            super(view);
            ivxs_head= (ImageView) view.findViewById(R.id.xshead);
            tvxs_xprice= (TextView) view.findViewById(R.id.xsxprice);
            tvxs_yprice= (TextView) view.findViewById(R.id.xsyprice);


        }
    }
}