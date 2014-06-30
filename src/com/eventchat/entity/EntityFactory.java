package com.eventchat.entity;

public final class EntityFactory {

    private EntityFactory() {

    }

    public static Session createSession(String name, String password) {
        Session session = new Session();
        session.setUserName(name);
        session.setPassword(password);
        return session;
    }

    public static User createUser(String name, String email, String password,
            String info) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        return user;
    }

}
