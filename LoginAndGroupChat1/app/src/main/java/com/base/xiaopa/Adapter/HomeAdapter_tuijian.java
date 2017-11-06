package com.base.xiaopa.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.xiaopa.base.TJ;
import com.xiaopa.android.R;

import java.util.List;

/**
 * Created by Satellite Wu on 2017/11/5.
 */
public class HomeAdapter_tuijian extends RecyclerView.Adapter<HomeAdapter_tuijian.MyViewHolder> implements  View.OnClickListener
{

    private List<TJ> mDatas;
    private OnItemClickListener mOnItemClickListener=null;

   public  HomeAdapter_tuijian(List<TJ> mDatas){
    this.mDatas=mDatas;
}
    public void setmOnItemClickListener(OnItemClickListener listener){
    this.mOnItemClickListener= listener;
}
   public static interface OnItemClickListener{
       void onItemClick(View view,int position);
   }
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_store_tuijian, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        view.setOnClickListener(this);
        return holder;
    }



    public void onBindViewHolder(MyViewHolder holder, int position)
    {
        holder.iv_tjhead.setBackgroundResource(mDatas.get(position).getTjhead());
        holder.tv_tjname.setText(mDatas.get(position).getTjname());
        holder.tv_tjnumber.setText(mDatas.get(position).getTjnumber());
        holder.tv_tjprcie.setText(mDatas.get(position).getTjprice());
        holder.itemView.setTag(position);//将position保存在itemview的Tag中
    }

    @Override
    public int getItemCount()
    {
        return mDatas.size();
    }

    @Override
    public void onClick(View v) {
       if(mOnItemClickListener!=null)
           mOnItemClickListener.onItemClick(v, (int) v.getTag());
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {

        ImageView iv_tjhead;
        TextView tv_tjname;
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