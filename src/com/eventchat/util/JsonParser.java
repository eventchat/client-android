package com.eventchat.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.eventchat.entity.Comment;
import com.eventchat.entity.EntityFactory;
import com.eventchat.entity.Event;
import com.eventchat.entity.Post;
import com.eventchat.entity.ChatMessage;
import com.eventchat.entity.User;

public final class JsonParser {

    private JsonParser() {

    }

    public static Event parseEvent(String s) {
        Event event = null;
        try {
            JSONObject object = new JSONObject(s);
            event = EntityFactory.createEvent(object
                    .getString(Constant.Common.ID), object
                    .getString(Constant.Common.NAME), object
                    .getDouble(Constant.Event.LONGITUDE), object
                    .getDouble(Constant.Event.LATITUDE), object
                    .getString(Constant.Event.ADDRESS), object
                    .getString(Constant.Event.START_TIME), object
                    .getString(Constant.Event.END_TIME), object
                    .getString(Constant.Event.DESCRIPTION),
                    parseUser(object.getString(Constant.Event.ORGANIZER))
                            .getName());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return event;
    }

    public static List<Event> parseEventList(String s) {
        List<Event> eventList = new ArrayList<Event>();
        try {
            JSONArray objectArray = new JSONArray(s);
            for (int i = 0; i < objectArray.length(); ++i) {
                Event event = parseEvent(objectArray.getString(i));
                if (event != null) {
                    eventList.add(event);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return eventList;
    }

    public static List<User> parseUserList(String s) {
        List<User> userList = new ArrayList<User>();
        try {
            JSONArray objectArray = new JSONArray(s);
            for (int i = 0; i < objectArray.length(); ++i) {
                User user = parseUser(objectArray.getString(i));
                if (user != null) {
                    userList.add(user);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public static User parseUser(String s) {
        User user = null;
        try {
            JSONObject object = new JSONObject(s);
            user = EntityFactory.createUser(
                    object.getString(Constant.Common.ID),
                    object.getString(Constant.Common.NAME),
                    object.getString(Constant.User.EMAIL),
                    object.getString(Constant.User.INFO),
                    object.getString(Constant.User.AVATAR_URL));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static Post parsePost(String s) {
        Post post = null;
        try {
            JSONObject object = new JSONObject(s);
            post = EntityFactory.createPost(object
                    .getString(Constant.Common.ID), object
                    .getString(Constant.Post.TITLE), object
                    .getString(Constant.Post.TYPE), object
                    .getString(Constant.Common.BODY), object
                    .getString(Constant.Common.CREATED_AT), parseUser(object
                    .getString(Constant.Common.AUTHOR)),
                    parseCommentList(object
                            .getString(Constant.Comment.COMMENTS)));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return post;
    }

    public static List<Post> parsePostList(String s) {
        List<Post> posts = new ArrayList<Post>();
        JSONArray array = null;
        try {
            array = new JSONArray(s);
            for (int i = 0; i < array.length(); ++i) {
                Post post = parsePost(array.getString(i));
                if (post != null) {
                    posts.add(post);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return posts;
    }

    public static Comment parseComment(String s) {
        Comment comment = null;
        try {
            JSONObject object = new JSONObject(s);
            comment = EntityFactory.createComment(
                    object.getString(Constant.Common.ID),
                    parseUser(object.getString(Constant.Common.AUTHOR)),
                    object.getString(Constant.Common.BODY),
                    object.getString(Constant.Common.CREATED_AT));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return comment;
    }

    public static List<Comment> parseCommentList(String s) {
        List<Comment> comments = new ArrayList<Comment>();
        JSONArray array = null;
        try {
            array = new JSONArray(s);
            for (int i = 0; i < array.length(); ++i) {
                Comment comment = parseComment(array.getString(i));
                if (comment != null) {
                    comments.add(comment);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return comments;
    }

    public static List<ChatMessage> parseReceiveMessages(String s) {
        List<ChatMessage> messages = new ArrayList<ChatMessage>();
        JSONArray array = null;
        try {
            array = new JSONArray(s);
            for (int i = 0; i < array.length(); ++i) {
                ChatMessage message = parseReceiveMessage(array.getString(i));
                if (message != null) {
                    messages.add(message);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return messages;
    }

    private static ChatMessage parseReceiveMessage(String s) {
        ChatMessage message = null;
        JSONObject object = null;
        try {
            object = new JSONObject(s);
            message = EntityFactory.createChatMessage(
                    parseUser(object.getString(Constant.Chat.FROM)),
                    parseUser(object.getString(Constant.Chat.TO)),
                    object.getString(Constant.Chat.MESSAGE),
                    object.getString(Constant.Common.CREATED_AT));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return message;
    }

}
