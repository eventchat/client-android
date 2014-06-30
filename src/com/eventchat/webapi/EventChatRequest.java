package com.eventchat.webapi;

import org.apache.http.HttpRequest;

public final class EventChatRequest {

    private HttpRequest mHttpRequest = null;

    private OnReceiveCallback mCallback = null;

    public EventChatRequest() {

    }

    EventChatRequest with(HttpRequest request) {
        mHttpRequest = request;
        return this;
    }

    EventChatRequest with(OnReceiveCallback callback) {
        mCallback = callback;
        return this;
    }

    HttpRequest getHttpRequest() {
        return mHttpRequest;
    }

    OnReceiveCallback getCallback() {
        return mCallback;
    }
}
