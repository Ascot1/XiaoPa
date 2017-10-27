package com.base.xiaopa.base;

import android.app.Application;

import cn.sharesdk.framework.ShareSDK;

/**
 * Created by cldn1 on 2017/9/21.
 * 初始化ShareSDK
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化ShareSDK
        ShareSDK.initSDK(this);
    }
}