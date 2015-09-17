package if4031;

import com.server.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.thrift.TException;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Willy on 9/16/2015.
 */
public class UserHandler implements UserService.Iface {
    private final HashMap<String, User> session = new HashMap<>();

    @Override
    public String getToken() throws TException {
        String token = RandomStringUtils.random(20);
        session.put(token, new User());
        return token;
    }

    @Override
    public String getNickname(String token) throws TException {
        User user = session.get(token);
        return user.getNickname();
    }

    @Override
    public boolean setNickname(String token, String nickname) throws TException {
        User user = session.get(token);
        return user.setNickname(nickname);
    }

    @Override
    public void joinChannel(String token, String channelName) throws TException {
        User user = session.get(token);
        user.joinChannel(channelName);
    }

    @Override
    public void leaveChannel(String token, String channelName) throws TException {
        User user = session.get(token);
        user.leaveChannel(channelName);
    }

    @Override
    public void sendMessage(String token, String message) throws TException {
        User user = session.get(token);
        user.sendMessage(message);
    }

    @Override
    public void sendMessageToChannel(String token, String message, String channelName) throws TException {
        User user = session.get(token);
        user.sendMessageToChannel(message, channelName);
    }

    @Override
    public List<String> getMessage(String token) throws TException {
        User user = session.get(token);
        return user.getMessage();
    }

    @Override
    public void exit(String token) throws TException {
        User user = session.get(token);
        user.exit();
        session.remove(token);
    }
}
