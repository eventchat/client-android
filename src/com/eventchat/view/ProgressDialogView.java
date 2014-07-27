package com.eventchat.view;

import android.app.ProgressDialog;
import android.content.Context;

import com.eventchat.util.DebugLog;

public final class ProgressDialogView {

    private static final String TAG = ProgressDialogView.class.getSimpleName();

    private ProgressDialog mProgressDialog = null;

    public ProgressDialogView() {

    }

    public void show(Context context, String title, String message) {
        DebugLog.d(TAG, "show");
        mProgressDialog = ProgressDialog.show(context, title, message);
    }

    public void show(Context context) {
        mProgressDialog = ProgressDialog.show(context, null, null);
    }

    public void dismiss() {
        DebugLog.d(TAG, "dismiss");
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }
}
