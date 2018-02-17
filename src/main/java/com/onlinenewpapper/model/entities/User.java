package com.onlinenewpapper.model.entities;

import com.onlinenewpapper.model.Role;
import lombok.*;

import java.util.List;

/**
 * Created by rbuga on 2/14/2018.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User {
    private Integer id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private List<Role> roles;

}
