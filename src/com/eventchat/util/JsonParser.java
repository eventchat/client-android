package com.eventchat.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.eventchat.entity.Chat;
import com.eventchat.entity.EntityFactory;
import com.eventchat.entity.Event;
import com.eventchat.entity.Post;
import com.eventchat.entity.User;

public final class JsonParser {

    private JsonParser() {

    }

    public static Event parseEvent(String s) {
        Event event = null;
        try {
            JSONObject object = new JSONObject(s);
            event = EntityFactory.createEvent(
                    object.getString(Constant.Common.ID),
                    object.getString(Constant.Common.NAME),
                    object.getDouble(Constant.Event.LONGITUDE),
                    object.getDouble(Constant.Event.LATITUDE),
                    object.getString(Constant.Event.ADDRESS),
                    object.getString(Constant.Event.START_TIME),
                    object.getString(Constant.Event.END_TIME),
                    object.getString(Constant.Event.DESCRIPTION), "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return event;
    }

    public static List<User> parseUserList(String s) {
        List<User> userList = new ArrayList<User>();
        try {
            JSONObject object = new JSONObject(s);

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
            post = EntityFactory.createPost(
                    object.getString(Constant.Common.ID),
                    object.getString(Constant.Common.NAME),
                    object.getString(Constant.Post.TYPE),
                    object.getString(Constant.Common.BODY),
                    object.getString(Constant.Common.CREATED_AT), null, null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return post;
    }

    public static Chat parseChat(String s) {
        Chat chat = null;
        try {
            JSONObject object = new JSONObject(s);
            chat = EntityFactory.createChat(
                    object.getString(Constant.Common.ID),
                    object.getString(Constant.Common.ID));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return chat;
    }
}
