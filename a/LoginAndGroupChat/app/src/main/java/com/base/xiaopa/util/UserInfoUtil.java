package com.base.xiaopa.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cldn1 on 2017/9/14.
 */
public class UserInfoUtil {

    /*获取用户名及密码*/
    public static Map<String,String> getUserInfo(Context context ){
        try {
            HashMap<String, String> map = new HashMap<String,String>();
            SharedPreferences sharedPreferences=context.getSharedPreferences("userInfo.txt",
                    context.MODE_APPEND);
            String username = sharedPreferences.getString("username", "");
            String password = sharedPreferences.getString("password", "");
            map.put("username", username);
            map.put("password", password);
            return map;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /*存储用户名及密码*/
    public static boolean saveUserInfo(Context context ,String username,String password){
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences("userInfo.txt",
                    context.MODE_APPEND);
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putString("username", username);
            edit.putString("password", password);
            edit.commit();
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;

    }
}

