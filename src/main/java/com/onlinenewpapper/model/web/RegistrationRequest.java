package com.onlinenewpapper.model.web;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by rbuga on 2/14/2018.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RegistrationRequest {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
}
