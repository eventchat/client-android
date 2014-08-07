package com.eventchat.entity;

import java.util.List;

public final class EntityFactory {

    private EntityFactory() {

    }

    public static Event createEvent(String name) {
        Event event = new Event();
        event.setName(name);
        return event;
    }

    public static Event createEvent(String id, String name, double longitude,
            double latitude, String address, String startTime, String endTime,
            String desc, String organizer) {
        return new Event(id, name, longitude, latitude, address, startTime,
                endTime, desc, organizer);
    }

    public static Session createSession(String name, String password) {
        return new Session(name, password);
    }

    public static User createUser(String id, String name, String email,
            String info, String url) {
        return new User(id, name, email, info, url);
    }

    public static Post createPost(String id, String title, String type,
            String body, String createdAt, User author,
            List<Comment> commentList) {
        return new Post(id, title, type, body, createdAt, author, commentList);
    }

    public static ChatMessage createChatMessage(User from, User to,
            String message, String createdAt) {
        return new ChatMessage(from, to, message, createdAt);
    }

    public static Comment createComment(String id, User author, String body,
            String createdAt) {
        return new Comment(id, author, body, createdAt);
    }
}
