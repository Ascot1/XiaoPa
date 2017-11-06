package com.base.xiaopa.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.xiaopa.activitys.TrolleyActivity;
import com.base.xiaopa.base.ShopCar;
import com.xiaopa.android.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Satellite Wu on 2017/11/5.
 */
public class TrolleyAdapter extends RecyclerView.Adapter<TrolleyAdapter.MyViewHolder> implements View.OnClickListener,View.OnLongClickListener
{
    private List<ShopCar> mList; //数据源
    private Context context;
    private OnItemClickListener mOnItemClickListener=null;
    private boolean isshowBox=false;  //是否显示单选框
    private Map<Integer,Boolean> map=new HashMap<>();


    public TrolleyAdapter(List<ShopCar> mList,Context context){
        this.mList=mList;
        this.context=context;
        initMap();
    }
    //初始化Map 默认为不选中
    public void initMap(){

        for(int i=0;i<mList.size();i++)
            map.put(i,false);

    }
    public Map<Integer,Boolean> getMap(){

        return map;
    }

    @Override
    public boolean onLongClick(View v) {
        initMap();
        return mOnItemClickListener!=null&&mOnItemClickListener.onItemLongClickListener(v,(int)v.getTag());
    }

    public static  interface OnItemClickListener{
        //单击事件
       void onItemClick(View view,int position);
        //长按事件
        Boolean onItemLongClickListener(View view,int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {

        this.mOnItemClickListener=listener;
    }
    public void setShowBox(){
        isshowBox=!isshowBox;
    }
    public void setSelectItem(int position){
        if(map.get(position)){
            map.put(position,false);
        }else{
            map.put(position,true);
        }
        notifyItemChanged(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trolley_recyclerview,parent,false);
        MyViewHolder holder =new MyViewHolder(view);
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.ivSChead.setBackgroundResource(mList.get(position).getSChead());
        holder.tvSCname.setText(mList.get(position).getSCname());
        holder.tvSCinformation.setText(mList.get(position).getSCinformation());
        holder.tvSCprice.setText(mList.get(position).getSCprice());
        holder.tvSCnumber.setText(mList.get(position).getSCnumber());
        //长按显示/隐藏
      /*  if(isshowBox)
            holder.checkBox.setVisibility(View.VISIBLE);
        else
            holder.checkBox.setVisibility(View.INVISIBLE);*/

        holder.itemView.setTag(position);
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                map.put(position,isChecked);
            }
        });
    //设置CheckBox的状态
        if(map.get(position)==null){
            map.put(position,false);}

     holder.checkBox.setChecked(map.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onClick(View v) {
        if(mOnItemClickListener!=null)
            mOnItemClickListener.onItemClick(v,(int)v.getTag());
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView ivSChead;
        TextView tvSCname;
        TextView  tvSCinformation;
        TextView  tvSCprice;
        TextView  tvSCnumber;
        CheckBox checkBox;
        public MyViewHolder(View view)
        {
            super(view);
            ivSChead= (ImageView) view.findViewById(R.id.shead);
            tvSCname= (TextView) view.findViewById(R.id.sname);
            tvSCinformation= (TextView) view.findViewById(R.id.sinformation);
            tvSCprice= (TextView) view.findViewById(R.id.sprice);
            tvSCnumber= (TextView) view.findViewById(R.id.snumber);
            checkBox= (CheckBox) view.findViewById(R.id.scheckbox);


        }
    }
}