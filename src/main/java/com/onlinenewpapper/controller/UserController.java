package com.onlinenewpapper.controller;

import com.onlinenewpapper.model.entities.User;
import com.onlinenewpapper.model.entities.UserSession;
import com.onlinenewpapper.model.web.LoginRequest;
import com.onlinenewpapper.model.web.RegistrationRequest;
import com.onlinenewpapper.repository.UserRepository;
import com.onlinenewpapper.repository.UserSessionRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Created by rbuga on 2/14/2018.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserSessionRepository userSessionRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public UserSession register(@RequestBody RegistrationRequest request){
        User user = userRepository.createUser(request);
        UserSession userSession = userSessionRepository.create(UUID.randomUUID().toString(), user);
        System.out.println(userSession.getSessionId());
        return userSession;
    }

    @PutMapping("/login")
    @SneakyThrows
    public UserSession login(@RequestBody LoginRequest request){
        User user =  userRepository.getByUsernameAndPassword(request.getUsername(),
                request.getPassword());
        if(user == null){
            throw new UsernameNotFoundException("Login is incorrect");
        }

        return userSessionRepository.create(UUID.randomUUID().toString(), user);
    }

    @PutMapping("/logout")
    public void logout(@RequestHeader("Authorization") String sessionId){
        userSessionRepository.invalidateSession(sessionId);
    }

    @GetMapping("/all")
    public List<User> getAll(){
        return userRepository.getAll();
    }

    @GetMapping("/user/info/{id}")
    public User userInfo(@PathVariable ("id") Integer id){
        return userRepository.getByUserId(id);
    }

    @GetMapping("/user/promote/{id}")
    public User userPromote(@PathVariable ("id") Integer id){
        return userRepository.userPromote(id);
    }

    @GetMapping("/user/demote/{id}")
    public User userDemote(@PathVariable ("id") Integer id){
        return userRepository.userDemote(id);
    }
}
