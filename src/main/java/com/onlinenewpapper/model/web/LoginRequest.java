package com.onlinenewpapper.model.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by rbuga on 2/14/2018.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginRequest {
    private String username;
    private String password;

}
