package com.onlinenewpapper.repository;

import com.onlinenewpapper.model.Role;
import com.onlinenewpapper.model.entities.User;
import com.onlinenewpapper.model.web.RegistrationRequest;
import lombok.NonNull;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by rbuga on 2/14/2018.
 */
@Repository
public class UserRepository {
    private static Map<String, User> users  = new HashMap<>();
    private static Map<String, User> admins  = new HashMap<>();

    public User createUser(RegistrationRequest request){

        User mapUser = users.get(request.getUsername());
        if(mapUser != null){
            throw new RuntimeException(String.format("User with username %s already exist", request.getUsername()));
        }

        List<Role> roles = new ArrayList<>();

        if(users.isEmpty()){
            roles.addAll(Arrays.stream(Role.values())
            .collect(Collectors.toList()));
        }else{
            roles.add(Role.ROLE_READER);
        }

        User user = User
                .builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .password(request.getPassword())
                .roles(roles)
                .username(request.getUsername())
                .id(users.size() + 1)
                .build();

                users.putIfAbsent(user.getUsername(), user);
                if(user.getRoles().contains(Role.ROLE_ADMIN)){
                    admins.putIfAbsent(user.getUsername(), user);
                }
                return user;
    }

    public User getByUsernameAndPassword(@NonNull String username, @NonNull String password) {
        User user = users.get(username);
        if (user == null) {
            return null;
        }

        if (user.getPassword().equals(password)) {
            return user;
        }

        return null;
    }

    public User getByUsername(@NonNull String username) {
        return users.get(username);
    }

    public User getByUserId(Integer userId){
        return users
                .values()
                .stream()
                .filter(x->x.getId().equals(userId))
                .findFirst()
                .orElse(null);
    }

    public List<User> getAll(){
        return new ArrayList<>(users.values());
    }

    public User userPromote(Integer id) {
        User user = getByUserId(id);
        if(user == null){
            throw new RuntimeException("User not found");
        }

        List<Role> userRoles = user.getRoles();
        if(userRoles.size() == Role.values().length){
            throw new RuntimeException("Promote maximum value");

        }
        userRoles.add(Role.getById(userRoles.size() + 1));
        user.setRoles(userRoles);
        if(user.getRoles().contains(Role.ROLE_ADMIN)){
            admins.putIfAbsent(user.getUsername(), user);
        }
        return user;
    }

    public User userDemote(Integer id) {

        User user = getByUserId(id);

        if(user.getRoles().contains(Role.ROLE_ADMIN)){

            if(admins.values().size() == 1) {

                throw new RuntimeException("Demote is forbidden");
            }

            admins.remove(user.getUsername(), user);
        }


        if(user == null){
            throw new RuntimeException("User not found");
        }
        List<Role> userRoles = user.getRoles();
        if(userRoles.size() == 1){
            throw new RuntimeException("Promote minimum value");
        }

        userRoles.remove(Role.getById(userRoles.size()));
        user.setRoles(userRoles);


        return user;
    }
}
