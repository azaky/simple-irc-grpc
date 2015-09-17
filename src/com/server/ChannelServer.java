package com.server;

import java.util.HashMap;

/**
 * Created by Willy on 9/14/2015.
 */
public class ChannelServer {
    private static final ChannelServer m_channelServer = new ChannelServer();

    private HashMap<String, Channel> m_channels;

    private ChannelServer() {
        m_channels = new HashMap<>();
    }

    public synchronized Channel getChannel(String channelName) {
        if (!m_channels.containsKey(channelName)) {
            m_channels.put(channelName, new Channel(channelName));
        }
        return m_channels.get(channelName);
    }

    public static ChannelServer getInstance() {
        return ChannelServer.m_channelServer;
    }

    public synchronized void removeChannel(String channelName) {
        m_channels.remove(channelName);
    }

    public synchronized boolean checkChannelAvailable(String channelName) {
        return !m_channels.containsKey(channelName);
    }
}
