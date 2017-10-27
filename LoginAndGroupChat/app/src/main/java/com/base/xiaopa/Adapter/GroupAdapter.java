package com.base.xiaopa.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.xiaopa.constant.GroupBean;
import com.xiaopa.android.R;

import java.util.ArrayList;

/**
 * Created by cldn1 on 2017/10/5.
 * listView适配器
 */
public class GroupAdapter extends BaseAdapter {
    private ArrayList<GroupBean> list;
    private Context context;

    public GroupAdapter(Context context,ArrayList<GroupBean> list){
        this.list = list;
        this.context = context;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        //复用convertView优化listView,创建一个view作为getview的返回值用来显示一个item
        if(convertView != null){
            view  = convertView;
        }else{
            view = View.inflate(context,R.layout.item_group_layout,null);
        }
        //获取View上的子控件
        ImageView item_img_icon =(ImageView)view.findViewById(R.id.item_img_icon);
        TextView item_tv_name = (TextView)view.findViewById(R.id.item_tv_name);
        TextView item_tv_msg = (TextView)view.findViewById(R.id.item_tv_msg);
        //获取position位置条目对应的list集合的群组数据
        GroupBean groupBean = list.get(position);
        item_img_icon.setImageDrawable(groupBean.icon);
        item_tv_name.setText(groupBean.groupName);
        item_tv_msg.setText(groupBean.msgNub);

        return view;
    }
}
