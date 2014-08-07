package com.eventchat.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;

import com.eventchat.util.Constant.Tag;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public final class WebApiUtil {

    private static final String TAG = WebApiUtil.class.getSimpleName();

    private WebApiUtil() {

    }

    public static String resToString(HttpResponse response) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            response.getEntity().writeTo(bos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bos.toString();
    }

    public static boolean isSuccess(HttpResponse response) {
        if (response != null) {
            DebugLog.d(TAG, "isSuccess response status code = "
                    + response.getStatusLine().getStatusCode());
            return response.getStatusLine().getStatusCode() == HttpStatus.SC_OK;
        }
        return false;
    }

    public static boolean isInternetConnected(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager != null) {
            NetworkInfo info = manager.getActiveNetworkInfo();
            if (info == null) {
                return false;
            } else if (!info.isConnected()) {
                return false;
            } else if (!info.isAvailable()) {
                return false;
            }
            return true;
        }
        return false;
    }
}
