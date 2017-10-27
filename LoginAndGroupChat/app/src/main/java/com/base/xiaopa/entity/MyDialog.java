package com.base.xiaopa.entity;

import android.content.Context;
import android.support.v7.app.AlertDialog;

/**
 * Created by Ivan on 2017/10/3.
 */

public class MyDialog {
    AlertDialog.Builder builder;

    public AlertDialog.Builder simpleDialog(
            final Context context, String title, String message) {
        builder = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("确定", null)
                .setNegativeButton("取消", null);
        return builder;
    }
}
