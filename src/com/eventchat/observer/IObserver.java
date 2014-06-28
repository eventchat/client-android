package com.eventchat.observer;

import android.os.Message;

public interface IObserver {

    public abstract void update(Message msg);

}
