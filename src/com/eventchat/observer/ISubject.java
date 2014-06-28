package com.eventchat.observer;

import android.os.Message;

public interface ISubject {

    public static final int FLAG_RUN_SYNC = 0;

    public static final int FLAG_RUN_ASYNC = 1;

    public abstract void notfiy(ISubject subject, Message msg);
}
