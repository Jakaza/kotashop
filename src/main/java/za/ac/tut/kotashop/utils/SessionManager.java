package za.ac.tut.kotashop.utils;

import org.springframework.stereotype.Component;
import za.ac.tut.kotashop.dto.UserDto;
import za.ac.tut.kotashop.entity.User;

import java.util.HashMap;
import java.util.Map;

@Component
public class SessionManager {
    private Map<String, User> sessionMap = new HashMap<>();

    public void createSession(String sessionId, User user) {
        sessionMap.put(sessionId, user);
    }

    public User getUserFromSession(String sessionId) {
        return sessionMap.get(sessionId);
    }

    public void removeSession(String sessionId) {
        sessionMap.remove(sessionId);
    }
}
