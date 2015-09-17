package com.server;

import java.util.HashSet;

/**
 * Created by Willy on 9/14/2015.
 */
public class Channel {
    private String m_channelName;

    private HashSet<User> m_users;

    public Channel(String channelName) {
        m_channelName = channelName;
        m_users = new HashSet<>();
    }

    public synchronized void addUser(User user) {
        m_users.add(user);
    }

    public synchronized void removeUser(User user) {
        m_users.remove(user);
        if (m_users.isEmpty()) {
            ChannelServer channelServer = ChannelServer.getInstance();
            channelServer.removeChannel(m_channelName);
        }
    }

    public void sendMessage(String senderNickname, String message) {
        for (User user : m_users) {
            user.saveMessage(String.format("[%s] (%s) %s", m_channelName, senderNickname, message));
        }
    }
}
