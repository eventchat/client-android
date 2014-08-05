package com.eventchat.util;

public interface Constant {

    // Tab tag
    public interface Tag {
        static final String TAB_JOIN = "join";
        static final String TAB_EVENT = "event";
        static final String TAB_CHAT = "chat";
        static final String TAB_ME = "me";
    }

    // Http
    public interface Http {
        static final String HTTP_GET = "GET";
        static final String HTTP_POST = "POST";
        static final String HTTP_PATCH = "PATCH";
        static final String HTTP_DELETE = "DELETE";
    }

    // Common key
    public interface Common {
        static final String ID = "id";
        static final String NAME = "name";
        static final String BODY = "body";
        static final String CREATED_AT = "created_at";
        static final String AUTHOR = "author";
        static final String PASSWORD = "password";

        // API protocol
        // Host
        static final String HOST = "http://eventchat.herokuapp.com";
        static final int PORT = 80;
    }

    // Key for user
    public interface User {
        static final String EMAIL = "email";
        static final String INFO = "info";
        static final String AVATAR_URL = "avatar_url";
    }

    // Key for post
    public interface Post {
        static final String TITLE = "title";
        static final String TYPE = "type";
    }

    // Key for comment
    // The same as common keys
    public interface Comment {

    }

    // Key for event
    public interface Event {
        static final String LONGITUDE = "longitude";
        static final String LATITUDE = "latitude";
        static final String START_TIME = "start_time";
        static final String END_TIME = "end_time";
        static final String DESCRIPTION = "description";
        static final String ADDRESS = "address";
        static final String ORGANIZER = "organizer";
        static final String ATTENDEE = "attendees";
    }

    // Key for message
    // The same as common keys
    public interface Message {

    }

    // Key for notification
    public interface Notification {
        static final String IS_READ = "is_read";
    }

    // Key for session
    public interface Session {
        static final String LOGGED_IN = "logged_in";
    }

    // Key for chat
    public interface Chat {
        static final String TO = "to";
        static final String MESSAGE = "message";
    }

    // User API
    public interface UserApi {
        static final String GET_USER = "/users/%s";
        static final String CREATE_USER = "/users";
    }

    // Post API
    public interface PostApi {
        static final String GET_POST = "/posts/%s";
        static final String CREATE_POST = "/posts";
        static final String DELETE_POST_BY_POST_ID = "/posts/%s";
        static final String GET_POST_BY_USER_ID = "/users/%s/posts";
        static final String GET_POST_BY_SEARCH = "/posts/search?latitude=%f&longitude=%f&max_distance=%d";
    }

    // Comment API
    public interface CommentApi {
        static final String CREATE_COMMENT_TO_POST = "/posts/%s/comments";
    }

    // Event API
    public interface EventApi {
        static final String GET_EVENT_BY_EVENT_ID = "/events/%s";
        static final String CREATE_EVENT = "/events";
        static final String UPDATE_EVENT = "/events";
        static final String DELETE_EVENT = "/events/%s";
        static final String JOINT_EVENT = "/events/%s/attendees";
        static final String GET_ATTENDEE_LIST = "/events/%s/attendees";
    }

    // Message API
    public interface MessageApi {
        static final String GET_MESSAGE_BY_EVENT_ID = "/events/%s/messages";
    }

    // Notification API
    public interface NotificationApi {
        static final String GET_NOTIFICATION = "/notifications";
        static final String READ_NOTIFICATION_BY_ID = "/notifications/%s";
        static final String READ_NOTIFICATION = "/notifications";
    }

    // Session API
    public interface SessionApi {
        static final String LOGIN = "/session";
        static final String LOGOUT = "/session";
        static final String LOGINT_STATUS = "/session";
    }

    public interface ChatApi {
        static final String SEND_CHAT = "/chat";
        static final String GET_CHAT = "/chat";
    }

    // UI
    public interface UI {
        static final int UPDATE_EVENT_INFO = 0;
        static final int UPDATE_EVENT_ATTENDEE = 1;
        static final int UPDATE_CHAT_MESSAGE = 2;
    }
}
