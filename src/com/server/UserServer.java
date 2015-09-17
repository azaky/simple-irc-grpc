package com.server;

import org.apache.thrift.TException;

import java.util.HashMap;

/**
 * Created by Willy on 9/16/2015.
 */
public class UserServer {
    private static final UserServer m_userServer = new UserServer();

    private HashMap<String, User> m_nicknameUserMapper;

    private UserServer() {
        m_nicknameUserMapper = new HashMap<>();
    }

    public static UserServer getInstance() {
        return m_userServer;
    }

    public void addUser(String nickname, User user) {
        m_nicknameUserMapper.put(nickname, user);
    }

    public void changeNickname(String newNickname, User user) throws TException {
        m_nicknameUserMapper.remove(user.getNickname());
        m_nicknameUserMapper.put(newNickname, user);
    }

    public boolean checkNicknameAvailable(String nickname) {
        return !m_nicknameUserMapper.containsKey(nickname);
    }

    public void removeUser(User user) throws TException {
        m_nicknameUserMapper.remove(user.getNickname());
    }
}
