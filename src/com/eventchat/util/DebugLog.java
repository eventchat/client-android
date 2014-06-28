package com.eventchat.util;

import android.util.Log;

public final class DebugLog {

    private static boolean mEnabled = true;

    private DebugLog() {

    }

    public static int v(String tag, String msg) {
        if (mEnabled) {
            return Log.v(tag, msg);
        }
        return -1;
    }

    public static int i(String tag, String msg) {
        if (mEnabled) {
            return Log.i(tag, msg);
        }
        return -1;
    }

    public static int d(String tag, String msg) {
        if (mEnabled) {
            return Log.d(tag, msg);
        }
        return -1;
    }

    public static int w(String tag, String msg) {
        if (mEnabled) {
            return Log.w(tag, msg);
        }
        return -1;
    }

    public static int e(String tag, String msg) {
        if (mEnabled) {
            return Log.e(tag, msg);
        }
        return -1;
    }

    public static int wtf(String tag, String msg) {
        if (mEnabled) {
            return Log.wtf(tag, msg);
        }
        return -1;
    }
}
