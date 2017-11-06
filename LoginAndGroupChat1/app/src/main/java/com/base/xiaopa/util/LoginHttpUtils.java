package com.base.xiaopa.util;

import android.os.Handler;
import android.os.Message;


import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by cldn1 on 2017/9/16.
 * 不同网络请求方式
 */
public class LoginHttpUtils {

    //以get方式请求数据
    public static void requestNetForGetLogin(final Handler handler, final String username,
                                             final String password){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //使用UrlConnection请求服务器将用户名及密码发送到服务器进行认证
                    String string_url = "http://192.168.43.7:8080/itheima74/servlet/LoginServlet?username="
                            +URLEncoder.encode(username,"utf-8")+"&pwd="+
                            URLEncoder.encode(password,"utf-8");
                    URL url = new URL(string_url);
                    HttpURLConnection openConnection = (HttpURLConnection)url.openConnection();
                    //设置HttpURLConnection对象的相关参数
                    openConnection.setConnectTimeout(10*1000);
                    openConnection.setRequestMethod("GET");
                    int code = openConnection.getResponseCode();
                    //获取相应码，判断响应吗是够为200
                    if(code == 200){
                        InputStream in = openConnection.getInputStream();
                        //获取网络链接的读取流信息，将流转化为字符串
                        String result = StreamUtils.streamToString(in);
                        boolean issuccess = false;
                        System.out.print(result);
                        if(result.contains("success")){
                            issuccess = true;
                        }
                        Message msg = Message.obtain();
                        msg.obj = issuccess;
                        handler.sendMessage(msg);

                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //以post方式请求数据
    public static void requestNetForPostLogin(final Handler handler,final String username,
                                              final String password){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    //使用urlConnection请求服务器将用户名及密码发送到服务器进行认证
                    String string_url ="http://192.168.43.7:8080/itheima74/servlet/LoginServlet";
                    URL url = new URL(string_url);
                    HttpURLConnection openConnection = (HttpURLConnection)url.openConnection();
                    openConnection.setRequestMethod("POST");
                    //设置一些请求头
                    String body = "username="+URLEncoder.encode(username,"utf-8")+"&pwd="
                            +URLEncoder.encode(password,"utf-8");
                    //设置URLConnection可以请求内容
                    openConnection.setDoOutput(true);
                    //获取一个outputstream，并将内容写入该流
                    openConnection.getOutputStream().write(body.getBytes());
                    openConnection.setConnectTimeout(10*1000);
                    int code = openConnection.getResponseCode();
                    if(code ==200){
                        InputStream in = openConnection.getInputStream();
                        //获取网络链接的读取流信息，将流转化为字符串
                        String result = StreamUtils.streamToString(in);
                        boolean issuccess = false;
                        System.out.print(result);
                        if(result.contains("success")){
                            issuccess = true;
                        }
                        Message msg = Message.obtain();
                        msg.obj = issuccess;
                        handler.sendMessage(msg);
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

