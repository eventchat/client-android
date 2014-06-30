package com.eventchat.webapi;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestFactory;
import org.apache.http.MethodNotSupportedException;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.DefaultHttpRequestFactory;
import org.apache.http.message.BasicNameValuePair;

import com.eventchat.util.Constant;

final class RequestBuilder {

    private static HttpRequestFactory sFactory = new DefaultHttpRequestFactory();

    public static HttpRequest buildGetUserRequest(String id) {
        String url = buildUrl(Constant.UserApi.GET_USER, id);
        if (url != null) {
            try {
                return sFactory.newHttpRequest(Constant.Http.HTTP_GET, url);
            } catch (MethodNotSupportedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static HttpRequest buildCreateUserRequest(String name, String email,
            String password, String info) {
        if (name != null && email != null && password != null) {
            String url = Constant.UserApi.CREATE_USER;
            HttpPost request = new HttpPost(url);
            List<NameValuePair> valuePairs = new ArrayList<NameValuePair>();
            valuePairs.add(new BasicNameValuePair(Constant.Common.NAME, name));
            valuePairs.add(new BasicNameValuePair(Constant.User.EMAIL, email));
            valuePairs.add(new BasicNameValuePair(Constant.Common.PASSWORD,
                    password));
            valuePairs.add(new BasicNameValuePair(Constant.User.INFO, info));
            try {
                request.setEntity(new UrlEncodedFormEntity(valuePairs));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return request;
        }
        return null;
    }

    public static HttpRequest buildGetPostRequest(String id) {
        String url = buildUrl(Constant.PostApi.GET_POST_BY_USER_ID, id);
        if (url != null) {
            try {
                return sFactory.newHttpRequest(Constant.Http.HTTP_GET, url);
            } catch (MethodNotSupportedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static HttpRequest buildCreatePostRequest(String title, String type,
            String body, String eventId) {
        if (title != null && type != null && body != null && eventId != null) {
            String url = buildUrl(Constant.PostApi.CREATE_POST);
            HttpPost request = new HttpPost(url);
            List<NameValuePair> valuePairs = new ArrayList<NameValuePair>();
            valuePairs.add(new BasicNameValuePair(Constant.Post.TITLE, title));
            valuePairs.add(new BasicNameValuePair(Constant.Post.TYPE, type));
            valuePairs.add(new BasicNameValuePair(Constant.Common.BODY, body));
            valuePairs.add(new BasicNameValuePair(Constant.Common.ID, eventId));
            try {
                request.setEntity(new UrlEncodedFormEntity(valuePairs));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return request;
        }
        return null;
    }

    public static HttpRequest buildDeletePostRequest(String id) {
        String url = buildUrl(Constant.PostApi.DELETE_POST_BY_POST_ID, id);
        if (url != null) {
            try {
                return sFactory.newHttpRequest(Constant.Http.HTTP_DELETE, url);
            } catch (MethodNotSupportedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static HttpRequest buildGetPostByUserRequest(String userId) {
        String url = buildUrl(Constant.PostApi.GET_POST_BY_USER_ID, userId);
        if (url != null) {
            try {
                return sFactory.newHttpRequest(Constant.Http.HTTP_GET, url);
            } catch (MethodNotSupportedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static HttpRequest buildGetPostBySearchRequest(double latitude,
            double longitude, int maxDistance) {
        String url = buildUrl(Constant.PostApi.GET_POST_BY_SEARCH, latitude,
                longitude, maxDistance);
        if (url != null) {
            HttpRequest request = null;
            try {
                request = sFactory.newHttpRequest(Constant.Http.HTTP_GET, url);
            } catch (MethodNotSupportedException e) {
                e.printStackTrace();
            }
            return request;
        }
        return null;
    }

    public static HttpRequest buildCreateCommentToPostRequest(String postId,
            String body) {
        String url = buildUrl(Constant.CommentApi.CREATE_COMMENT_TO_POST,
                postId);
        if (url != null && body != null) {
            HttpPost request = new HttpPost(url);
            List<NameValuePair> valuePairs = new ArrayList<NameValuePair>();
            valuePairs.add(new BasicNameValuePair(Constant.Common.BODY, body));
            try {
                request.setEntity(new UrlEncodedFormEntity(valuePairs));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return request;
        }
        return null;
    }

    public static HttpRequest buildGetEventRequest(String id) {
        String url = buildUrl(Constant.EventApi.GET_EVENT_BY_EVENT_ID, id);
        if (url != null) {
            try {
                return sFactory.newHttpRequest(Constant.Http.HTTP_GET, url);
            } catch (MethodNotSupportedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static HttpRequest buildCreateEventRequest(String name,
            double longitude, double latitude, String startTime,
            String endTime, String desc) {
        if (name != null) {
            HttpPost request = new HttpPost(
                    buildUrl(Constant.EventApi.CREATE_EVENT));
            List<NameValuePair> valuePairs = new ArrayList<NameValuePair>();
            valuePairs.add(new BasicNameValuePair(Constant.Common.NAME, name));
            valuePairs.add(new BasicNameValuePair(Constant.Event.LONGITUDE,
                    String.valueOf(longitude)));
            valuePairs.add(new BasicNameValuePair(Constant.Event.LATITUDE,
                    String.valueOf(latitude)));
            valuePairs.add(new BasicNameValuePair(Constant.Event.START_TIME,
                    startTime));
            valuePairs.add(new BasicNameValuePair(Constant.Event.END_TIME,
                    endTime));
            try {
                request.setEntity(new UrlEncodedFormEntity(valuePairs));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return request;
        }
        return null;
    }

    public static HttpRequest buildCreateUpdateEventRequest(String name,
            double longitude, double latitude, String startTime,
            String endTime, String desc) {
        if (name != null) {
            HttpPatch request = new HttpPatch(
                    buildUrl(Constant.EventApi.UPDATE_EVENT));
            List<NameValuePair> valuePairs = new ArrayList<NameValuePair>();
            valuePairs.add(new BasicNameValuePair(Constant.Common.NAME, name));
            valuePairs.add(new BasicNameValuePair(Constant.Event.LONGITUDE,
                    String.valueOf(longitude)));
            valuePairs.add(new BasicNameValuePair(Constant.Event.LATITUDE,
                    String.valueOf(latitude)));
            valuePairs.add(new BasicNameValuePair(Constant.Event.START_TIME,
                    startTime));
            valuePairs.add(new BasicNameValuePair(Constant.Event.END_TIME,
                    endTime));
            try {
                request.setEntity(new UrlEncodedFormEntity(valuePairs));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return request;
        }
        return null;
    }

    public static HttpRequest buildDeleteEventRequest(String id) {
        String url = buildUrl(Constant.EventApi.DELETE_EVENT, id);
        if (url != null) {
            try {
                return sFactory.newHttpRequest(Constant.Http.HTTP_DELETE, url);
            } catch (MethodNotSupportedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static HttpRequest buildGetMessageByEventRequest(String id) {
        String url = buildUrl(Constant.MessageApi.GET_MESSAGE_BY_EVENT_ID, id);
        if (url != null) {
            try {
                return sFactory.newHttpRequest(Constant.Http.HTTP_GET, url);
            } catch (MethodNotSupportedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static HttpRequest buildGetNotificationRequest() {
        String url = buildUrl(Constant.NotificationApi.GET_NOTIFICATION);
        HttpRequest request = null;
        try {
            request = sFactory.newHttpRequest(Constant.Http.HTTP_GET, url);
        } catch (MethodNotSupportedException e) {
            e.printStackTrace();
        }
        return request;
    }

    public static HttpRequest buildReadNotificationByIdRequest(String id) {
        String url = buildUrl(Constant.NotificationApi.READ_NOTIFICATION_BY_ID,
                id);
        if (url != null) {
            HttpRequest request = null;
            try {
                request = sFactory
                        .newHttpRequest(Constant.Http.HTTP_PATCH, url);
            } catch (MethodNotSupportedException e) {
                e.printStackTrace();
            }
            return request;
        }
        return null;
    }

    public static HttpRequest buildReadNotificationRequest() {
        String url = buildUrl(Constant.NotificationApi.READ_NOTIFICATION);
        HttpRequest request = null;
        try {
            request = sFactory.newHttpRequest(Constant.Http.HTTP_PATCH, url);
        } catch (MethodNotSupportedException e) {
            e.printStackTrace();
        }
        return request;
    }

    public static HttpRequest buildLoginRequest(String name, String password) {
        if (name != null && password != null) {
            String url = buildUrl(Constant.SessionApi.LOGIN);
            HttpPost post = new HttpPost(url);
            try {
                List<NameValuePair> valuePairs = new ArrayList<NameValuePair>();
                valuePairs.add(new BasicNameValuePair(Constant.Common.NAME,
                        name));
                valuePairs.add(new BasicNameValuePair(Constant.Common.PASSWORD,
                        password));
                post.setEntity(new UrlEncodedFormEntity(valuePairs));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return post;
        }
        return null;
    }

    public static HttpRequest buildLogoutRequest() {
        String url = buildUrl(Constant.SessionApi.LOGOUT);
        HttpRequest request = null;
        try {
            request = sFactory.newHttpRequest(Constant.Http.HTTP_DELETE, url);
        } catch (MethodNotSupportedException e) {
            e.printStackTrace();
        }
        return request;
    }

    public static HttpRequest buildCheckLoginRequest() {
        String url = buildUrl(Constant.SessionApi.LOGINT_STATUS);
        HttpRequest request = null;
        try {
            request = sFactory.newHttpRequest(Constant.Http.HTTP_GET, url);
        } catch (MethodNotSupportedException e) {
            e.printStackTrace();
        }
        return request;
    }

    private static String buildUrl(String format, String param) {
        if (format != null) {
            return Constant.Common.HOST + String.format(format, param);
        }
        return null;
    }

    private static String buildUrl(String url) {
        if (url != null) {
            return Constant.Common.HOST + url;
        }
        return null;
    }

    private static String buildUrl(String format, double latitude,
            double longitude, int maxDistance) {
        if (format != null) {
            return Constant.Common.HOST
                    + String.format(format, latitude, longitude, maxDistance);
        }
        return null;
    }
}
