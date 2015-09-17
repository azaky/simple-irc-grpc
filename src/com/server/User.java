package com.server;

import org.apache.thrift.TException;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Vector;

/**
 * User object
 */
public class User{
    private String m_nickname;

    private HashSet<Channel> m_channels; // list of channels that the user is joining

    private Vector<String> m_messages;

    public User() {
        m_channels = new HashSet<>();
        m_messages = new Vector<>();
        m_nickname = RandomStringUtils.random(10);
        UserServer userServer = UserServer.getInstance();
        userServer.addUser(m_nickname, this);
    }

    public String getNickname() throws TException {
        return m_nickname;
    }

    public boolean setNickname(String nickname) throws TException {
        UserServer userServer = UserServer.getInstance();
        if (!userServer.checkNicknameAvailable(nickname)) {
            return false;
        }
        userServer.changeNickname(nickname, this);
        m_nickname = nickname;
        return true;
    }

    public void joinChannel(String channelName) throws TException {
        ChannelServer channelServer = ChannelServer.getInstance();
        Channel channel = channelServer.getChannel(channelName);
        channel.addUser(this);

        m_channels.add(channel);
    }

    public void leaveChannel(String channelName) throws TException {
        ChannelServer channelServer = ChannelServer.getInstance();
        Channel channel = channelServer.getChannel(channelName);
        channel.removeUser(this);

        m_channels.remove(channel);
    }

    public void sendMessage(String message) throws TException {
        for (Channel channel : m_channels) {
            channel.sendMessage(m_nickname, message);
        }
    }

    public void sendMessageToChannel(String message, String channelName) throws TException {
        ChannelServer channelServer = ChannelServer.getInstance();
        if (channelServer.checkChannelAvailable(channelName))
            return;
        Channel channel = channelServer.getChannel(channelName);
        channel.sendMessage(m_nickname, message);
    }

    public List<String> getMessage() throws TException {
        Vector<String> messages = m_messages;
        m_messages = new Vector<>();
        return messages;
    }

    public void exit() throws TException {
        for (Channel channel : m_channels) {
            channel.removeUser(this);
        }
        UserServer userServer = UserServer.getInstance();
        userServer.removeUser(this);
    }

    public void saveMessage(String message) {
        System.out.println(message);
        m_messages.add(message);
    }
}
