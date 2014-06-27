package com.eventchat.util;

import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestFactory;
import org.apache.http.MethodNotSupportedException;
import org.apache.http.impl.DefaultHttpRequestFactory;

import com.eventchat.entity.Comment;
import com.eventchat.entity.Event;
import com.eventchat.entity.Post;
import com.eventchat.entity.User;

public final class RequestBuilder {

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

    public static HttpRequest buildCreateUserRequest(User user) {
        if (user != null) {
            String url = Constant.UserApi.CREATE_USER;
            HttpRequest request = null;
            try {
                request = sFactory.newHttpRequest(Constant.Http.HTTP_POST, url);
            } catch (MethodNotSupportedException e) {
                e.printStackTrace();
            }
            request.setParams(user.toParams());
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

    public static HttpRequest buildCreatePostRequest(Post post) {
        if (post != null) {
            String url = Constant.PostApi.CREATE_POST;
            HttpRequest request = null;
            try {
                request = sFactory.newHttpRequest(Constant.Http.HTTP_POST, url);
            } catch (MethodNotSupportedException e) {
                e.printStackTrace();
            }
            request.setParams(post.toParams());
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
        // TODO
        return null;
    }

    public static HttpRequest buildCreateCommentToPostRequest(String postId,
            Comment comment) {
        String url = buildUrl(Constant.CommentApi.CREATE_COMMENT_TO_POST,
                postId);
        if (url != null && comment != null) {
            HttpRequest request = null;
            try {
                request = sFactory.newHttpRequest(Constant.Http.HTTP_POST, url);
            } catch (MethodNotSupportedException e) {
                e.printStackTrace();
            }
            request.setParams(comment.toParams());
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

    public static HttpRequest buildCreateEventRequest(Event event) {
        String url = Constant.EventApi.CREATE_EVENT;
        HttpRequest request = null;
        try {
            request = sFactory.newHttpRequest(Constant.Http.HTTP_POST, url);
        } catch (MethodNotSupportedException e) {
            e.printStackTrace();
        }
        request.setParams(event.toParams());
        return request;
    }

    public static HttpRequest buildCreateUpdateEventRequest(Event event) {
        String url = Constant.EventApi.UPDATE_EVENT;
        HttpRequest request = null;
        try {
            request = sFactory.newHttpRequest(Constant.Http.HTTP_PATCH, url);
        } catch (MethodNotSupportedException e) {
            e.printStackTrace();
        }
        request.setParams(event.toParams());
        return request;
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
        String url = Constant.NotificationApi.GET_NOTIFICATION;
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
        String url = Constant.NotificationApi.READ_NOTIFICATION;
        HttpRequest request = null;
        try {
            request = sFactory.newHttpRequest(Constant.Http.HTTP_PATCH, url);
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
}
