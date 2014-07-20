package com.eventchat.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.http.HttpResponse;

public final class WebApiUtil {

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
}
