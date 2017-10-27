package com.base.xiaopa.activitys;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.base.xiaopa.util.LoginHttpUtils;
import com.base.xiaopa.util.UserInfoUtil;
import com.mob.tools.utils.UIHandler;
import com.xiaopa.android.R;


import java.util.HashMap;
import java.util.Map;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;

/**
 * Created by cldn1 on 2017/9/14.
 * 该类用于登录界面
 */
public class LoginActivity extends AppCompatActivity  implements View.OnClickListener
        ,PlatformActionListener,Handler.Callback {

    private EditText et_username;
    private EditText et_password;
    private CheckBox cd_rem;
    private Button bt_login;
    private Button forget_psd;
    private Button bt_register;
    private ImageView qq_login;
    private ImageView weichat_login;
    private TextView mGuest;
    private Context mContext;
    private boolean isrem;
    private String username;
    private String password;

    private static final int MSG_ACTION_CCALLBACK = 0;
    private ProgressDialog progressDialog;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mContext = this;
        //获取控件
        et_username = (EditText)findViewById(R.id.username);
        et_password = (EditText)findViewById(R.id.user_password);
        cd_rem = (CheckBox) findViewById(R.id.cb_rem);
        bt_login = (Button)findViewById(R.id.bt_login);
        forget_psd = (Button)findViewById(R.id.forget_psd);
        bt_register = (Button)findViewById(R.id.bt_register);
        qq_login = (ImageView)findViewById(R.id.qq_login);
        weichat_login = (ImageView)findViewById(R.id.weichat_login);
        mGuest= (TextView) findViewById(R.id.guest);
        //为控件设置点击事件
        bt_login.setOnClickListener(this);
        bt_register.setOnClickListener(this);
        qq_login.setOnClickListener(this);
        weichat_login.setOnClickListener(this);
        mGuest.setOnClickListener(this);
        //回显用户名及密码
        Map<String,String> map = UserInfoUtil.getUserInfo(mContext);
        if(map!=null){
            String username = map.get("username");
            String password = map.get("password");
            et_username.setText(username);
            et_password.setText(password);
            cd_rem.setChecked(true);
        }

    }

    //创建一个Handler
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            boolean issuccess = (Boolean) msg.obj;
            if(issuccess){
                //判断是否记住密码，如果记住，将密码缓存到本地
                if(isrem){
                    boolean result = UserInfoUtil.saveUserInfo(mContext,username,password);
                    if(result){
                        Toast.makeText(mContext,"用户名及密码保存成功",Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(mContext,"用户名及密码保存失败",Toast.LENGTH_SHORT).show();
                    }


                }else{
                    Toast.makeText(mContext,"不保存密码及用户名",Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this,MainActivity.class);
                startActivity(intent);

            }else{
                Toast.makeText(mContext,"用户名或密码错误，请重新输入",Toast.LENGTH_SHORT).show();
            }
        }
    };
     @Override
   public void onClick(View v) {
        switch(v.getId()){
            case R.id.bt_login://登陆
                login();

                break ;
            case R.id.bt_register://这里添加免费注册
//                Intent i =new Intent(LoginActivity.this,RegisterActivity.class);
//                startActivity(i);
                break;
            case R.id.qq_login://这里添加QQ登陆
                Platform qq = ShareSDK.getPlatform(QQ.NAME);
                qq.setPlatformActionListener(this);
                qq.SSOSetting(false);
                authorize(qq, 2);

                break;
            case R.id.weichat_login://这里添加微信登陆
                Platform wechat = ShareSDK.getPlatform(Wechat.NAME);
                wechat.setPlatformActionListener(this);
                wechat.SSOSetting(false);
                authorize(wechat, 1);
                break;
            case R.id.forget_psd://这里添加忘记密码

                break;
            case R.id.guest:
                Intent intent =new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                break;
            default :
                break;
        }
    }


    /*登陆*/
    private void login() {
        /*在onclick方法中，获取用户输入的用户名及密码以及是否记住密码*/
        username = et_username.getText().toString().trim();
        password = et_password.getText().toString().trim();
        isrem = cd_rem.isChecked();
        //判断用户名及密码是否为空，不为空则请求服务器
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            Toast.makeText(mContext, "用户名或者密码不能为空，请重新输入", Toast.LENGTH_SHORT).show();
            return;
        }
        //以POST方式请求服务器
        LoginHttpUtils.requestNetForPostLogin(handler, username, password);
    }



    //授权
    private void authorize(Platform plat, int type) {
        switch (type) {
            case 1:
                showProgressDialog(getString(R.string.opening_wechat));
                break;
            case 2:
                showProgressDialog(getString(R.string.opening_qq));
                break;
            case 3:
                showProgressDialog(getString(R.string.opening_blog));
                break;
        }
        if (plat.isValid()) { //如果授权就删除授权资料
            plat.removeAccount();
        }
        plat.showUser(null);//授权并获取用户信息
    }



    //登陆授权成功的回调
    @Override
    public void onComplete(Platform platform, int action, HashMap<String, Object> res) {
        Message msg = new Message();
        msg.what = MSG_ACTION_CCALLBACK;
        msg.arg1 = 1;
        msg.arg2 = action;
        msg.obj = platform;
        UIHandler.sendMessage(msg, this);   //发送消息

    }

    //登陆授权错误的回调
    @Override
    public void onError(Platform platform, int action, Throwable t) {
        Message msg = new Message();
        msg.what = MSG_ACTION_CCALLBACK;
        msg.arg1 = 2;
        msg.arg2 = action;
        msg.obj = t;
        UIHandler.sendMessage(msg, this);
    }

    //登陆授权取消的回调
    @Override
    public void onCancel(Platform platform, int action) {
        Message msg = new Message();
        msg.what = MSG_ACTION_CCALLBACK;
        msg.arg1 = 3;
        msg.arg2 = action;
        msg.obj = platform;
        UIHandler.sendMessage(msg, this);
    }

    //登陆发送的handle消息在这里处理
    @Override
    public boolean handleMessage(Message message) {
        hideProgressDialog();
        switch (message.arg1) {
            case 1: { // 成功
                Toast.makeText(LoginActivity.this, "授权登陆成功", Toast.LENGTH_SHORT).show();

                //获取用户资料
                Platform platform = (Platform) message.obj;
                String userId = platform.getDb().getUserId();//获取用户账号
                String userName = platform.getDb().getUserName();//获取用户名字
                String userIcon = platform.getDb().getUserIcon();//获取用户头像
                //获取用户性别，m = 男, f = 女，如果微信没有设置性别,默认返回null
                String userGender = platform.getDb().getUserGender();
                //跳转到主界面
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this,GroupActivity.class);
                startActivity(intent);
                Toast.makeText(LoginActivity.this, "用户信息为--用户名：" + userName +
                        "性别：" + userGender, Toast.LENGTH_SHORT).show();

                //下面就可以利用获取的用户信息登录自己的服务器或者做自己想做的事啦!
                //。。。

            }
            break;
            case 2: { // 失败
                Toast.makeText(LoginActivity.this, "授权登陆失败", Toast.LENGTH_SHORT).show();
            }
            break;
            case 3: { // 取消
                Toast.makeText(LoginActivity.this, "授权登陆取消", Toast.LENGTH_SHORT).show();
            }
            break;
        }
        return false;
    }

    //显示dialog
    public void showProgressDialog(String message) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(message);
        progressDialog.setCancelable(true);
        progressDialog.show();
    }

    //隐藏dialog
    public void hideProgressDialog() {
        if (progressDialog != null)
            progressDialog.dismiss();
    }
}

