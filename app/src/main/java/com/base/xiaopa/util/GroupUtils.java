package com.base.xiaopa.util;

import android.content.Context;

import com.base.xiaopa.constant.GroupBean;
import com.xiaopa.android.R;

import java.util.ArrayList;

/**
 * Created by cldn1 on 2017/10/5.
 */
public class GroupUtils {
    //封装新闻的假数据到list中返回
    public static ArrayList<GroupBean> getAllGroup(Context context) {
        ArrayList<GroupBean> arrayList = new ArrayList<GroupBean> ();

           GroupBean groupBean = new GroupBean();
           groupBean.icon = context.getResources().getDrawable(R.drawable.one);
           groupBean.groupName = "阳光party";
           groupBean.msgNub = "10条未读信息";
           arrayList.add(groupBean);

           GroupBean groupBean1 = new GroupBean();
           groupBean1.icon = context.getResources().getDrawable(R.drawable.two);
           groupBean1.groupName = "群聊2";
           groupBean1.msgNub = "12条未读信息";
           arrayList.add(groupBean1);

           GroupBean groupBean2 = new GroupBean();
           groupBean2.icon = context.getResources().getDrawable(R.drawable.three);
           groupBean2.groupName = "群聊3";
           groupBean2.msgNub = "10条未读信息";
           arrayList.add(groupBean2);

           GroupBean groupBean3 = new GroupBean();
           groupBean3.icon = context.getResources().getDrawable(R.drawable.four);
           groupBean3.groupName = "群聊4";
           groupBean3.msgNub = "10条未读信息";
           arrayList.add(groupBean3);

           GroupBean groupBean4 = new GroupBean();
           groupBean4.icon = context.getResources().getDrawable(R.drawable.five);
           groupBean4.groupName = "群聊5";
           groupBean4.msgNub = "10条未读信息";
           arrayList.add(groupBean4);

           GroupBean groupBean5 = new GroupBean();
           groupBean5.icon = context.getResources().getDrawable(R.drawable.six);
           groupBean5.groupName = "群聊6";
           groupBean5.msgNub = "10条未读信息";
           arrayList.add(groupBean5);


           GroupBean groupBean6 = new GroupBean();
           groupBean6.icon = context.getResources().getDrawable(R.drawable.six);
           groupBean6.groupName = "群聊7";
           groupBean6.msgNub = "10条未读信息";
           arrayList.add(groupBean6);

        return arrayList;
    }
}

