package com.eventchat.webapi;

import org.apache.http.HttpResponse;

public interface OnReceiveCallback {

    public abstract void onReceive(HttpResponse response);

}
