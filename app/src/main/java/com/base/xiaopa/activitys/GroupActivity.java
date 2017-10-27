package com.base.xiaopa.activitys;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.base.xiaopa.Adapter.GroupAdapter;
import com.base.xiaopa.constant.GroupBean;
import com.base.xiaopa.util.GroupUtils;
import com.base.xiaopa.view.SearchView;

import com.xiaopa.android.R;
import java.util.ArrayList;


public class GroupActivity extends Fragment implements View.OnClickListener{

    private ListView lv_chat;
    private SearchView search;
    Context mcontext;
    View v;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v=inflater.inflate(R.layout.activity_chat,container,false);
        mcontext=getContext();
        lv_chat = (ListView)v.findViewById(R.id.lv_group);
        search = (SearchView)v.findViewById(R.id.et_search);

        //获取群组数据用list封装
        ArrayList<GroupBean> allGroup = GroupUtils.getAllGroup(getContext());
        //创建Adapter设置给listView
        GroupAdapter groupAdapter = new GroupAdapter(getContext(),allGroup);
        lv_chat.setAdapter(groupAdapter);

        //设置点击事件
        lv_chat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //ListView的条目点击时会调用该方法
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(getActivity(),ChatActivity.class);
                startActivity(intent);
            }
        });
        return v;
    }





    //控件点击事件
    @Override
    public void onClick(View v) {
      switch(v.getId()){
          case R.id.et_search:

              break;
          case R.id.tv_add:

              break;
          default:
              break;
      }
    }
}
