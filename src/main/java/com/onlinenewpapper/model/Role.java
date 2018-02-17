package com.onlinenewpapper.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Created by rbuga on 2/16/2018.
 */
@AllArgsConstructor
@NoArgsConstructor

public enum Role {
    ROLE_ADMIN(3),
    ROLE_READER(1),
    ROLE_WRITER(2);

    private Integer id;


    @JsonValue
    public Integer getId() {
        return id;
    }

    @JsonCreator
    public static Role getById(Integer id){
        if(id == null){
            return null;
        }
        for(Role role : values()){
            if(role.getId().equals(id)){
                return role;
            }
        }
        throw new IllegalArgumentException("Role not supported");
    }
}
