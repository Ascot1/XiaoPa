package com.base.xiaopa.activitys;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;

import com.base.xiaopa.entity.MyDialog;
import com.xiaopa.android.R;

/**
 * Created by Ivan on 2017/10/3.
 */

public class SecondActivity extends MainActivity implements View.OnClickListener {

    Button JoinIn;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        JoinIn = (Button) findViewById(R.id.JoinIn);
        JoinIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.JoinIn:
                onClickSimple();
                break;
            default:
                break;
        }
    }

    public void onClickSimple() {
        builder = new MyDialog().simpleDialog(this,null,"确定参加该活动");
        builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        })
                .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        builder.create().show();
    }
}
