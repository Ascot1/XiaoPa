package com.base.xiaopa.activitys;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.MediaStore;

import android.support.v4.content.FileProvider;
import android.support.v4.view.ScrollingView;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;


import com.xiaopa.android.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;


public class RegisterActivity extends Activity implements View.OnClickListener {

    /**
     * 多媒体
     */
    //相机与相册
    public static final int TAKE_PHOTO = 1;
    public static final int OPEN_PHOTO = 2;
    public static final int PHOTO_REQUEST_CUT = 3;
    private Uri imageUri;
    //短信
    private Button register;
    private Button getCode;
    String mPhone;

    /**
     * 其他控件
     **/
    private Button Male;
    private Button Female;
    private TextView Birthday;
    private ImageView Hp;
    private TextView Tel;
    private EditText code;
    int mYear, mMonth, mDay;
    private AlertDialog alertDialog;
    int e = 0, i = 0;

    private ScrollView mScrollView;
    private int Width;

    EventHandler eventHandler;

    private static final String[] COUNTRIES = new String[]{
            "五邑大学", "武夷大学", "华南理工大学", "华南师范大学", "中山大学", "上海复旦大学"
    };


//    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Hp = (ImageView) findViewById(R.id.HeadPortrait);
        register = (Button) findViewById(R.id.Register);
        getCode = (Button) findViewById(R.id.Verification);
        Tel = (TextView) findViewById(R.id.Text_TEL);
        code = (EditText) findViewById(R.id.Text_Verification);


        Calendar ca = Calendar.getInstance();   //获得默认时区的时间
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);

        Birthday = (TextView) findViewById(R.id.Text_Date);
        Male = (Button) findViewById(R.id.Male);
        Female = (Button) findViewById(R.id.Female);
        final AutoCompleteTextView mSelectSchool = (AutoCompleteTextView) findViewById(R.id.select_school);


        /**
         * 点击事件
         */

        Birthday.setOnClickListener(this);
        Male.setOnClickListener(this);
        Female.setOnClickListener(this);
        Hp.setOnClickListener(this);
        getCode.setOnClickListener(this);
        register.setOnClickListener(this);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, COUNTRIES);
        //适配器与自动完成文本框相关联
        mSelectSchool.setAdapter(adapter);
        //信息事务处理器
        MSG();
    }

    /**
     * 点击事件
     *
     * @param v
     */

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Text_Date:
                new DatePickerDialog(RegisterActivity.this, onDateSetListener,
                        mYear, mMonth, mDay).show();
                break;
            case R.id.Male:
                Female.setTextColor(R.color.grey);
                Male.setTextColor(R.color.Blue);
                if (e != 1) {
                    Hp.setImageResource(R.drawable.male);
                }
                break;
            case R.id.Female:
                if (e != 1) {
                    Hp.setImageResource(R.drawable.female);
                }
                Female.setTextColor(R.color.Pink);
                Male.setTextColor(R.color.grey);
                break;
            case R.id.HeadPortrait:
                showListAlertDialog();
                break;
            case R.id.Verification:
                getCode();
                break;
            case R.id.Register:
                submitCode();
                break;
            default:
                break;


        }

    }

    /**
     * 选择插入图片的方式单选对话框
     */
    public void showListAlertDialog() {
        i = 0;
        final String[] items = {"相机", "相册"};
        final AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle("请选择插入图片方式");
        alertBuilder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                i = which;
            }
        });
        alertBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                if (i == 0) {
                    Camera();
                } else if (i == 1) {
                    MyPhoto();
                }
                alertDialog.dismiss();
                e = 1;
            }
        });
        alertBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                alertDialog.dismiss();
            }
        });
        alertDialog = alertBuilder.create();
        alertDialog.show();
    }

    /**
     * 调用相册
     */
    private void MyPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, OPEN_PHOTO);
    }

    /**
     * 调用相机
     */
//    @RequiresApi(api = Build.VERSION_CODES.FROYO)
    private void Camera() {
        File outputImage = new File(getExternalCacheDir(), "output_image.jpg");
        try {
            if (outputImage.exists()) {
                outputImage.delete();
            }
            outputImage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Build.VERSION.SDK_INT >= 24) {
            imageUri = FileProvider.getUriForFile(RegisterActivity.this,
                    "com.quincy.www.schooldating.fileprovider", outputImage);
        } else {
            imageUri = Uri.fromFile(outputImage);
        }
        //启动相机
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, TAKE_PHOTO);
    }

    /**
     * 相机和相册活动结果
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver()
                                .openInputStream(imageUri));
                        Hp.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case OPEN_PHOTO:
                if (requestCode == OPEN_PHOTO) {
                    if (data != null) {
                        Uri uri = data.getData();
                        crop(uri);
                    }
                }
                break;
            case PHOTO_REQUEST_CUT:
                if (requestCode == PHOTO_REQUEST_CUT) {
                    Bitmap bitmap = data.getParcelableExtra("data");
                    Hp.setImageBitmap(bitmap);
                }
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 裁剪照片
     *
     * @param uri
     */
    //Todo 不是特别理解intent的用意
    private void crop(Uri uri) {
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);

        intent.putExtra("outputFormat", "JPEG");  // 图片格式
        intent.putExtra("noFaceDetection", true);  // 取消人脸识别
        intent.putExtra("return-data", true);

        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CUT
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

    /**
     * 日历监听器
     */
    private DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            String days;
            //统一展现格式：yyyy年mm月dd日
            if (mMonth + 1 < 10) {
                if (mDay < 10) {
                    days = new StringBuffer().append(mYear).append("年").append("0").
                            append(mMonth + 1).append("月").append("0").append(mDay).append("日").toString();
                } else {
                    days = new StringBuffer().append(mYear).append("年").append("0").
                            append(mMonth + 1).append("月").append(mDay).append("日").toString();
                }
            } else {
                if (mDay < 10) {
                    days = new StringBuffer().append(mYear).append("年").
                            append(mMonth + 1).append("月").append("0").append(mDay).append("日").toString();
                } else {
                    days = new StringBuffer().append(mYear).append("年").
                            append(mMonth + 1).append("月").append(mDay).append("日").toString();
                }
            }
            Birthday.setText(days);
        }
    };

    /**
     * 获取验证码
     */
    private boolean getCode() {
        mPhone = Tel.getText().toString().trim();
        SMSSDK.getVerificationCode("86", mPhone);
        return true;
    }

    /**
     * 提交验证码
     */
    private void submitCode() {
        String mCode = code.getText().toString().trim();
        SMSSDK.submitVerificationCode("86", mPhone, mCode);
    }

    /**
     * 事务处理
     */
    private void MSG() {
        eventHandler = new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                if (data instanceof Throwable) {
                    Throwable throwable = (Throwable) data;
                    final String msg = throwable.getMessage();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //Todo 这里有bug
                            Toast.makeText(RegisterActivity.this, msg, Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        //获取验证码的回调
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(RegisterActivity.this, "获取成功,请60秒后再获取", Toast.LENGTH_SHORT).show();
                                MyCountDownTimer myCountDownTimer = new MyCountDownTimer(60000, 1000);
                                myCountDownTimer.start();
                            }
                        });

                    } else if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        //提交代码操作的回调
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }
        };
        SMSSDK.registerEventHandler(eventHandler);
    }

    /**
     * 倒计时
     */
    private class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        //计时过程
        @Override
        public void onTick(long l) {
            //防止计时过程中重复点击
            getCode.setClickable(false);
            getCode.setText((l / 1000) + "s");
        }

        //计时完毕的方法
        @Override
        public void onFinish() {
            //重新给Button设置文字
            getCode.setText("重新获取");
            //设置可点击
            getCode.setClickable(true);
        }
    }


    /**
     * 销毁事务处理
     */
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eventHandler);
    }


}