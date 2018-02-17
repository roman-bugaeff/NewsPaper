package com.onlinenewpapper.repository;

import com.onlinenewpapper.model.entities.User;
import com.onlinenewpapper.model.entities.UserSession;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rbuga on 2/14/2018.
 */
@AllArgsConstructor

@Data
@Repository
public class UserSessionRepository {
    private static Map<String, UserSession> sessions = new HashMap<>();

    public UserSession create(String sessionId, User user) {
        UserSession userSession = UserSession
                .builder()
                .sessionId(sessionId)
                .user(user)
                .isValid(true)
                .build();
        sessions.putIfAbsent(sessionId, userSession);
        return userSession;
    }

    public UserSession getBySessionId(String sessionId) {
        UserSession userSession = sessions.get(sessionId);
        if (userSession == null || !userSession.getIsValid()) {
            return null;
        }
        return userSession;
    }


    public void invalidateSession(String sessionId) {
        UserSession userSession = sessions.get(sessionId);
        if (userSession == null) {
            return;
        }
        userSession.setIsValid(false);
        sessions.put(sessionId, userSession);
    }

}
