package com.eventchat.util;

public interface Constant {

    // Http
    public interface Http {
        String HTTP_GET = "GET";
        String HTTP_POST = "POST";
        String HTTP_PATCH = "PATCH";
        String HTTP_DELETE = "DELETE";
    }

    // Common key
    public interface Common {
        String ID = "id";
        String NAME = "name";
        String BODY = "body";
        String CREATED_AT = "created_at";
        String AUTHOR = "author";
        String PASSWORD = "password";

        // API protocol
        // Host
        String HOST = "http://eventchat.herokuapp.com";
        int PORT = 80;
    }

    // Key for user
    public interface User {
        String EMAIL = "email";
        String INFO = "info";
        String AVATAR_URL = "avatar_url";
    }

    // Key for post
    public interface Post {
        String TITLE = "title";
        String TYPE = "type";
    }

    // Key for comment
    // The same as common keys
    public interface Comment {

    }

    // Key for event
    public interface Event {
        String LONGITUDE = "longitude";
        String LATITUDE = "latitude";
        String START_TIME = "start_time";
        String END_TIME = "end_time";
        String DESCRIPTION = "description";
        String ADDRESS = "address";
        String ORGANIZER = "organizer";
        String ATTENDEE = "attendees";
    }

    // Key for message
    // The same as common keys
    public interface Message {

    }

    // Key for notification
    public interface Notification {
        String IS_READ = "is_read";
    }

    // Key for session
    public interface Session {
        String LOGGED_IN = "logged_in";
    }

    // User API
    public interface UserApi {
        String GET_USER = "/users/%s";
        String CREATE_USER = "/users";
    }

    // Post API
    public interface PostApi {
        String GET_POST = "/posts/%s";
        String CREATE_POST = "/posts";
        String DELETE_POST_BY_POST_ID = "/posts/%s";
        String GET_POST_BY_USER_ID = "/users/%s/posts";
        String GET_POST_BY_SEARCH = "/posts/search?latitude=%f&longitude=%f&max_distance=%d";
    }

    // Comment API
    public interface CommentApi {
        String CREATE_COMMENT_TO_POST = "/posts/%s/comments";
    }

    // Event API
    public interface EventApi {
        String GET_EVENT_BY_EVENT_ID = "/events/%s";
        String CREATE_EVENT = "/events";
        String UPDATE_EVENT = "/events";
        String DELETE_EVENT = "/events/%s";
        String JOINT_EVENT = "/events/%s/attendees";
        String GET_ATTENDEE_LIST = "/events/%s/attendees";
    }

    // Message API
    public interface MessageApi {
        String GET_MESSAGE_BY_EVENT_ID = "/events/%s/messages";
    }

    // Notification API
    public interface NotificationApi {
        String GET_NOTIFICATION = "/notifications";
        String READ_NOTIFICATION_BY_ID = "/notifications/%s";
        String READ_NOTIFICATION = "/notifications";
    }

    // Session API
    public interface SessionApi {
        String LOGIN = "/session";
        String LOGOUT = "/session";
        String LOGINT_STATUS = "/session";
    }
}
