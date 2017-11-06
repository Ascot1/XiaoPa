package com.base.xiaopa.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * Created by cldn1 on 2017/9/16.
 * 将流转化为字符串
 */
public class StreamUtils {

    public static String streamToString(InputStream in ){

        String result = "";
        try{
            //创建一个字节数组写入流
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length = 0;
            while ((length = in.read(buffer))!= -1){
                out.write(buffer,0,length);
                out.flush();
            }
            //将字节流转化为string
            result = new String(out.toByteArray(),"utf-8");
            out.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
}

